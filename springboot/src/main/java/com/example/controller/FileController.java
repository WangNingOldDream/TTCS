package com.example.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.Result;
import com.example.entity.AgainstForm;
import com.example.service.impl.AgainstFormServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import static com.example.common.enums.ResultCodeEnum.SUCCESS;

/**
 * 文件接口
 */
@RestController
@RequestMapping("/files")
public class FileController {

    // 文件上传存储路径
    private static final String filePath = System.getProperty("user.dir") + "/files/";

    @Value("${server.port:9090}")
    private String port;

    @Value("${ip:localhost}")
    private String ip;
    @Resource
    private AgainstFormServiceImpl againstFormService;

    /**
     * 文件上传
     */
    @PostMapping("/upload/{againstId}")
    public Result upload(MultipartFile file,@PathVariable Integer againstId) {
        String flag;
        synchronized (FileController.class) {
            flag = System.currentTimeMillis() + "";
            ThreadUtil.sleep(1L);
        }
        String fileName = file.getOriginalFilename();
        try {
            if (!FileUtil.isDirectory(filePath)) {
                FileUtil.mkdir(filePath);
            }
            // 文件存储形式：时间戳-文件名
            FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);  // ***/manager/files/1697438073596-avatar.png
            System.out.println(fileName + "--上传成功");

        } catch (Exception e) {
            System.err.println(fileName + "--文件上传失败");
        }
        String http = "http://" + ip + ":" + port + "/files/";
        String url=http + flag + "-" + fileName;  //  http://localhost:9090/files/1697438073596-avatar.

        //更新数据库
        AgainstForm againstForm=againstFormService.getById(againstId);
        againstForm.setRecord(url);
        againstFormService.updateById(againstForm);

        HashMap<String,Object> data=new HashMap<>();
        data.put("flag",true);
        data.put("url",url);
        Result res =new Result();
        res.setCode(SUCCESS.code);
        res.setMsg("文件上传成功");
        res.setData(data);
        return Result.success(data);
    }


    /**
     * 获取文件
     *
     * @param flag
     * @param response
     */
    @GetMapping("/{flag}")   //  1697438073596-avatar.png
    public void avatarPath(@PathVariable String flag, HttpServletResponse response) {
        OutputStream os;
        try {
            if (StrUtil.isNotEmpty(flag)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(flag, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + flag);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }

    /**
     * 获取文件
     * @param againstId
     * @param response
     */
    @GetMapping("/getFile/{againstId}")   //  1697438073596-avatar.png
    public void avatarPath(@PathVariable Integer againstId, HttpServletResponse response) {
        OutputStream os;
        try {
            if (againstId!=null) {
                String url=againstFormService.getById(againstId).getRecord();
                if(url!=null) {
                    response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(url, "UTF-8"));
                    response.setContentType("application/octet-stream");
                    byte[] bytes = FileUtil.readBytes(url);
                    os = response.getOutputStream();
                    os.write(bytes);
                    os.flush();
                    os.close();
                }
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }


    /**
     * 删除文件
     *
     * @param flag
     */
    @DeleteMapping("/{flag}")
    public void delFile(@PathVariable String flag) {
        FileUtil.del(filePath + flag);
        System.out.println("删除文件" + flag + "成功");
    }


}
