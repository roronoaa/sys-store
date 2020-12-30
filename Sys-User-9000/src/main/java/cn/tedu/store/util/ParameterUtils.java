package cn.tedu.store.util;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.tedu.store.controller.ex.EmptyFileException;
import cn.tedu.store.controller.ex.FileExternDisMatchException;
import cn.tedu.store.controller.ex.FileOutOfMaxSizeException;
import cn.tedu.store.controller.ex.ParameterErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ParameterUtils {

    public static void isEmpty(String str, String msg) {
        if (StrUtil.isEmpty(str)) throw new ParameterErrorException(msg);
//        return true;
    }

    public static void isEmpty(Number number, String msg) {
        if (ObjectUtil.isEmpty(number) || number.doubleValue() <= 0.0) throw new ParameterErrorException(msg);
//        return false;
    }

    public static void isNull(Object object, String msg) {
        if (ObjectUtil.isNull(object)) throw new ParameterErrorException(msg);
//        return false;
    }

    public static void isNullOrUndifine(Object object, String msg) {
        if (ObjectUtil.isNull(object)) throw new ParameterErrorException(msg);
        if ((object.toString()).equals("undefined")) throw new ParameterErrorException(msg);
//        return false;
    }

    public static void checkFileUpload(MultipartFile file, Double fileSize, List<String> fileExts) {
        if (file == null || file.isEmpty() || file.getSize() == 0)
            throw new EmptyFileException("文件上传错误: 文件为空,请勿上传空文件!");
        if (file.getSize() > fileSize * 1000 * 1024)
            throw new FileOutOfMaxSizeException("文件上传错误: 文件大小超过限制,最大" + fileSize + "MB!");
        String fileName = file.getOriginalFilename();
        String fileExt = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        if (!fileExts.contains(fileExt)) throw new FileExternDisMatchException("文件上传错误: 文件类型不匹配!");
//        return true;
    }

    public static void checkRate(Double number, String msg) {
        if (ObjectUtil.isNull(number)) throw new ParameterErrorException(msg);
        if (number < 0.0 || number > 1.0) throw new ParameterErrorException(msg);
//        return true;
    }

    public static void checkScore(Double number, String msg) {
        if (ObjectUtil.isNull(number)) throw new ParameterErrorException(msg);
        if (number < 00.0 || number > 100.0) throw new ParameterErrorException(msg);
//        return true;
    }

}
