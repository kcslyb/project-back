package cn.kcs.note.controller;

import cn.kcs.common.loginInfo.LoginInfo;
import cn.kcs.common.util.CommonUtil;
import cn.kcs.common.util.ListUtil;
import cn.kcs.common.util.constants.ErrorEnum;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.note.entity.TFile;
import cn.kcs.note.service.TFileService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    private Logger logger = LoggerFactory.getLogger(TFile.class);
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
            OutputStream out = response.getOutputStream();   //创建页面返回方式为输出流，会自动弹出下载框
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
    @ApiOperation(value = "上传文件")
    @PostMapping("/upload")
    public JSONObject fileUpload(MultipartFile file) {
        if (Objects.isNull(file) || file.isEmpty()) {
            logger.error("文件为空");
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
            if (ListUtil.notNullAndEmpty(tFiles)) {
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
            logger.debug("文件写入成功...");
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("服务器错误...");
        }
        return CommonUtil.successJson(resultFile);
    }
}