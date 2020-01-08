package com.fh.utils;

import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CopyFile {


    public static String copyFile(MultipartFile image, String path){
        String imageName = image.getOriginalFilename();
        imageName = imageName.substring(imageName.lastIndexOf("."));
        long l = System.currentTimeMillis();
        imageName = l+imageName;

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        String realPath = request.getServletContext().getRealPath("/");
        realPath = realPath+"/"+"commons/"+path;
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        try {
            image.transferTo(new File(realPath+"/"+imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }



        return "/commons/"+path+"/"+imageName;
    }
    public static String uploadFile(MultipartFile multipartFile, String docName) {
        //首先处理 文件名
        String originalFilename = multipartFile.getOriginalFilename();
        String finalFileName = System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));
        String realPath = null;
        try {
            //获取static文件路径  //F:\workspace\shop_admin\target\classes\static
            realPath = ResourceUtils.getFile("classpath:static").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File fileTemp = new File(realPath+"/"+docName);
        //判断是否存在文件夹.
        if(!fileTemp.exists()){
            //创建文件夹
            fileTemp.mkdirs();
        }
//    上传文件 用的mvc自带的上传方法
        try {
            multipartFile.transferTo(new File(realPath+"/"+docName +"/"+ finalFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/static"+"/"+docName+"/"+finalFileName;
    }


}
