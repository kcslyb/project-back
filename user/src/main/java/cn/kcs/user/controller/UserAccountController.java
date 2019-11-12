package cn.kcs.user.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.kcs.common.fileutil.DeleteFileUtil;
import cn.kcs.common.fileutil.DownloadFileUtil;
import cn.kcs.common.util.CommonUtil;
import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.UserAccount;
import cn.kcs.user.service.UserAccountService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * (UserAccount)表控制层
 *
 * @author kcs
 * @date 2019-03-21 14:46:59
 */
@RestController
@RequestMapping("user/account")
public class UserAccountController {
    private Logger LOGGER = LoggerFactory.getLogger(FileController.class);
    /**
     * 服务对象
     */
    @Resource
    private UserAccountService userAccountService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping()
    public UserAccount selectOne(String id) {
        return this.userAccountService.queryById(id);
    }

    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity selectById(@PathVariable String id) {
        return new ResponseEntity<>(this.userAccountService.queryById(id), HttpStatus.OK);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param userAccount 实例对象
     * @return 对象列表
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "通过实体查询所有数据")
    @GetMapping("query")
    public List<UserAccount> queryAll(UserAccount userAccount) {
        return this.userAccountService.queryAll(userAccount);
    }

    /**
     * 添加数据
     *
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "添加数据")
    @PostMapping
    public ResponseEntity addAccount(@RequestBody UserAccount account) {
        boolean insert = this.userAccountService.insert(account);
        if (insert) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    /**
     * 添加数据
     *
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "注册")
    @PostMapping(value = {"/add"})
    public ResponseEntity registerAccount(@RequestBody UserAccount account) {
        if (StringUtils.isBlank(account.getSessionCode())) {
            return new ResponseEntity<>("注册失败,验证码为空", HttpStatus.BAD_REQUEST);
        }
        if (!CommonUtil.isEmail(account.getUserEmail())) {
            return new ResponseEntity<>("注册失败,请请输入正确的邮箱", HttpStatus.BAD_REQUEST);
        }
        if (!CommonUtil.isPhone(account.getUserPhone())) {
            return new ResponseEntity<>("注册失败,请请输入正确的手机号", HttpStatus.BAD_REQUEST);
        }
        Session session = SecurityUtils.getSubject().getSession();
        Object code = session.getAttribute(account.getUserEmail());
        if (code != null && code.toString().equals(account.getSessionCode())) {
            return this.userAccountService.registerAccount(account);
        }
        session.removeAttribute(account.getUserEmail());
        return new ResponseEntity<>("注册失败, 验证码错误", HttpStatus.BAD_REQUEST);
    }

    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @RequiresRoles("SuperAdmin")
    @ApiOperation(value = "删除单条数据")
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return this.userAccountService.deleteById(id);
    }

    /**
     * 修改单条数据
     *
     * @param account
     * @return
     */
    @Encrypt
    @Decrypt
    @RequiresRoles("SuperAdmin")
    @ApiOperation(value = "修改单条数据")
    @PutMapping()
    public UserAccount editAccount(@RequestBody UserAccount account) {
        return this.userAccountService.update(account);
    }

    /**
     * 查询所以数据
     *
     * @return List数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询所以数据")
    @GetMapping("query/pager")
    public ResponseEntity query(UserAccount account, PageRequest pageRequest) {
        pageRequest = pageRequest.initStart(pageRequest);
        int size = userAccountService.queryAll(account).size();
        List<UserAccount> accounts = this.userAccountService.queryAllByLimit(account, pageRequest);
        ResponseDto<UserAccount> userAccountResponseDto = new ResponseDto<>(accounts, size, pageRequest.getSize(), pageRequest.getStart());
        return new ResponseEntity<>(userAccountResponseDto, HttpStatus.OK);
    }

    /**
     * 查询所以数据
     *
     * @return List数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "导出user数据")
    @GetMapping("query/export")
    public void export(HttpServletResponse response) {
        String folder = System.getProperty("java.io.tmpdir");
        File saveFile = new File(folder);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        List<UserAccount> userAccounts = userAccountService.queryAll(new UserAccount());
        String fileName = System.currentTimeMillis() + ".xls";
        String filePath = folder + File.separator + fileName;
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), UserAccount.class, userAccounts);
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
            LOGGER.info("临时文件[{}]生成完成,路径为[{}]", fileName, filePath);
            DownloadFileUtil.downloadFile(response, fileName, filePath);
            DeleteFileUtil.deleteFile(filePath);
            LOGGER.info("下载文件成功[{}]", fileName);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

}