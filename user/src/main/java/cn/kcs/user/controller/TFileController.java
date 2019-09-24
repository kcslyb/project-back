package cn.kcs.user.controller;

import cn.kcs.common.fileutil.DeleteFileUtil;
import cn.kcs.common.fileutil.DownloadFileUtil;
import cn.kcs.common.logininfo.LoginInfo;
import cn.kcs.common.util.CommonUtil;
import cn.kcs.common.util.QrCodeUtil;
import cn.kcs.common.util.constants.ErrorEnum;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.TFile;
import cn.kcs.user.service.TFileService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * (TFile)表控制层
 *
 * @author makejava
 * @since 2019-04-19 15:17:08
 */
@Api(value = "file", description = "文件 API")
@RestController
@RequestMapping("file")
public class TFileController {

    private Logger LOGGER = LoggerFactory.getLogger(TFile.class);
    @Value("${upload_folder_path}")
    private String UPLOAD_FOLDER_PATH;
    /**
     * 服务对象
     */
    @Resource
    private TFileService tFileService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "查询单个文件", response = TFile.class)
    @GetMapping("")
    public TFile selectOne(String id) {
        return this.tFileService.queryById(id);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询文件", response = TFile.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "无信息", response = Void.class),
            @ApiResponse(code = 200, message = "查询成功", response = TFile.class)})
    @GetMapping("query/pager")
    public JSONObject queryPager(TFile fileDto, Integer offset, Integer limit) {
        if (limit == null || limit < 0) {
            limit = 10;
        }
        if (offset == null || offset < 0) {
            offset = 0;
        } else {
            offset = (offset - 1) * limit;
        }
        List<TFile> list = tFileService.queryAllByLimit(fileDto, offset, limit);
        int size = tFileService.queryAll(fileDto).size();
        return CommonUtil.successPage(list, size);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询当前用户文件", response = TFile.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "无信息", response = Void.class),
            @ApiResponse(code = 200, message = "查询成功", response = TFile.class)})
    @GetMapping("currentList")
    public JSONObject queryCurrentPager(TFile fileDto, Integer offset, Integer limit) {
        if (limit == null || limit < 0) {
            limit = 10;
        }
        if (offset == null || offset < 0) {
            offset = 0;
        } else {
            offset = (offset - 1) * limit;
        }
        fileDto.setFileOwner(LoginInfo.getUserId());
        fileDto.setFileOwnerName(LoginInfo.getUserName());
        List<TFile> tFiles = tFileService.queryAllByLimit(fileDto, offset, limit);
        int size = tFileService.queryAll(fileDto).size();
        return CommonUtil.successPage(tFiles, size);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "下载文件", response = TFile.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "下载失败", response = String.class),
            @ApiResponse(code = 200, message = "下载成功", response = String.class)})
    @GetMapping(value = "{key}")
    public JSONObject download(@PathVariable String key, HttpServletResponse response) {
        if (StringUtils.isEmpty(key)) {
            return CommonUtil.errorJson(ErrorEnum.E_403);
        }
        TFile fileDto = tFileService.queryById(key);
        fileDto.setFileDownloadNumber(Integer.toString(Integer.parseInt(fileDto.getFileDownloadNumber()) + 1));
        tFileService.update(fileDto);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileDto.getFileName(), "UTF-8"));
            // 创建页面返回方式为输出流，会自动弹出下载框
            OutputStream out = response.getOutputStream();
            File file = new File(fileDto.getFilePath());
            FileInputStream inputStream = new FileInputStream(file);
            int b = 0;
            byte[] buffer = new byte[512];
            while (b != -1) {
                b = inputStream.read(buffer);
                out.write(buffer, 0, b);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return CommonUtil.successJson();
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "下载文件", response = TFile.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "下载失败", response = String.class),
            @ApiResponse(code = 200, message = "下载成功", response = String.class)})
    @GetMapping(value = "info")
    public ResponseEntity qrDownload(String text, String fileName, HttpServletResponse response) {
        if (StringUtils.isEmpty(text)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isEmpty(fileName)) {
            fileName = ShortUUID.generate();
        }
        fileName = fileName + ".jpg";
        String folder = System.getProperty("java.io.tmpdir");
        String filepath = folder + File.separator + fileName;
        try {
            LOGGER.info("开始生成二维码文件，预生成目录：[{}]", filepath);
            QrCodeUtil.encode(text, filepath);
            LOGGER.info("已生成二维码文件，文件目录：[{}]", filepath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadFileUtil.downloadFile(response, fileName, filepath);
        DeleteFileUtil.deleteFile(filepath);
        LOGGER.info("已删除二维码文件，文件目录：[{}]", filepath);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "上传文件")
    @PostMapping("/upload")
    public JSONObject fileUpload(MultipartFile file) {
        if (Objects.isNull(file) || file.isEmpty()) {
            LOGGER.error("文件为空");
            return CommonUtil.errorJson("文件为空", ErrorEnum.E_403);
        }
        TFile resultFile = new TFile();
        try {
            TFile tFile = new TFile();
            String contentType = file.getContentType();
            if (!contentType.isEmpty()) {
                String[] str = contentType.split("/");
                if (str.length > 1) {
                    contentType = str[0] + str[1];
                }
                contentType += "/";
            }
            //如果没有files文件夹，则创建
            if (!Files.isWritable(Paths.get(UPLOAD_FOLDER_PATH + contentType))) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER_PATH + contentType));
            }
            String filename = file.getOriginalFilename();
            TFile temp = new TFile();
            String[] str = filename.split("\\.");
            temp.setFileName(str[0].substring(0, str[0].length() - 2));
            List<TFile> tFiles = tFileService.queryAll(temp);
            if (!CollectionUtils.isEmpty(tFiles)) {
                filename = str[0] + tFiles.size() + "." + str[1];
            }
            Path path = Paths.get(UPLOAD_FOLDER_PATH + contentType + filename);
            tFile.setFilePath(path.toString());
            tFile.setFileName(filename);
            tFile.setFileSize(Long.toString(file.getSize()) + "kb");
            tFile.setFileType(file.getContentType());
            resultFile = tFileService.insert(tFile);
            //文件写入指定路径
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);
            LOGGER.debug("文件写入成功...");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.debug("服务器错误...");
        }
        return CommonUtil.successJson(resultFile);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "删除文件", response = TFile.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "删除失败", response = String.class),
            @ApiResponse(code = 200, message = "删除成功", response = String.class)})
    @DeleteMapping(value = "remove")
    @RequiresRoles("SuperAdmin")
    public ResponseEntity<String> remove(String path) {
        String regex = "^+" + UPLOAD_FOLDER_PATH + "?*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(path);
        boolean matches = matcher.matches();
        if (matches) {
            boolean b = DeleteFileUtil.deleteFile(path);
            if (!b) {
                return new ResponseEntity<>("删除失败：不存在该文件路径", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("参数错误,不存在该文件路径", HttpStatus.BAD_REQUEST);
        }
    }
}