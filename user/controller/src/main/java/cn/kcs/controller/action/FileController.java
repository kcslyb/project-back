package cn.kcs.controller.action;

import cn.kcs.common.util.DataUtil;
import cn.kcs.common.util.constants.Constants;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.service.inter.FileService;
import cn.kcs.service.inter.dto.FileDto;
import cn.kcs.service.inter.dto.UserDto;
import cn.kcs.service.inter.util.PageRequest;
import cn.kcs.service.inter.util.PageResponse;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @description:
 * @author: kcs
 * @create: 2018-10-25 15:16
 **/
@RequestMapping(value = "file")
@Api(value = "文件", description = "文件api")
@RestController
public class FileController {
    private Logger logger = LoggerFactory.getLogger(UserPrintController.class);

    @Autowired
    private FileService fileService;

    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询文件", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "无信息", response = Void.class),
            @ApiResponse(code = 200, message = "查询成功", response = FileDto.class)})
    @GetMapping("list")
    public ResponseEntity queryPager(FileDto fileDto, Integer pageNo, Integer pageSize, String flag) {
        if (pageSize == null || pageSize < 0) {
            pageSize = 10;
        }
        PageRequest pageRequest = new PageRequest(pageNo, pageSize);
        PageResponse pageResponse = fileService.queryPager(pageRequest.getStart(), pageSize, fileDto);
        return new ResponseEntity(pageResponse, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询当前用户文件", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "无信息", response = Void.class),
            @ApiResponse(code = 200, message = "查询成功", response = FileDto.class)})
    @GetMapping("currentList")
    public ResponseEntity queryCurrentPager(FileDto fileDto, Integer pageNo, Integer pageSize) {
        if (pageSize == null || pageSize < 0) {
            pageSize = 10;
        }
        PageRequest pageRequest = new PageRequest(pageNo, pageSize);
        PageResponse pageResponse = fileService.queryCurrentPager(pageRequest.getStart(), pageSize, fileDto);
        return new ResponseEntity(pageResponse, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "下载文件", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "下载失败", response = String.class),
            @ApiResponse(code = 200, message = "下载成功", response = String.class)})
    @GetMapping(value = "{key}")
    public ResponseEntity download(@PathVariable String key, HttpServletResponse response) {
        FileDto fileDto = fileService.getByKey(key);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileDto.getFilePath(), "UTF-8"));
//            workbook.write(response.getOutputStream());
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
        return new ResponseEntity(HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "上传文件")
    @PostMapping("/singlefile")
    public String singleFileUpload(MultipartFile file, HttpServletRequest request) {
        String UPLOAD_FOLDER = "/Users/kcs/WebstormProjects/demo/static/";
//        logger.debug("传入的文件参数：{}", JSONUtils.toJSONString(file));
        if (Objects.isNull(file) || file.isEmpty()) {
            logger.error("文件为空");
        }
        FileDto fileDto = new FileDto();
        try {
            //如果没有files文件夹，则创建
            if (!Files.isWritable(Paths.get(UPLOAD_FOLDER))) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER));
            }
            byte[] bytes = file.getBytes();
            String timeStamp = Long.toString(DataUtil.currentTimeStamp());
            String filename = file.getOriginalFilename();
            String[] str = filename.split("\\.");
            filename = "";
            int lastIndex = str.length - 1;
            filename = str[0] + "-" + timeStamp + "." + str[lastIndex];
            Path path = Paths.get(UPLOAD_FOLDER + filename);
            fileDto.setFileTime(DataUtil.stringToDate(DataUtil.currentFormatDate(DataUtil.DATE_TO_STRING_DETAIAL_PATTERN), DataUtil.DATE_TO_STRING_DETAIAL_PATTERN));
            fileDto.setFileName("文件名：" + filename);
            Session session = SecurityUtils.getSubject().getSession();
            if (session != null && session.getAttribute(Constants.SESSION_USER_INFO) != null) {
                JSONObject jsonUser = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
                fileDto.setFileDescription("上传者：" + jsonUser.getString("userName"));
            }
            fileDto.setFilePath(path.toString());
            fileService.add(fileDto);
            //文件写入指定路径
            Files.write(path, bytes);
            logger.debug("文件写入成功...");
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("服务器错误...");
        }
        return fileDto.getFileName();
    }
}
