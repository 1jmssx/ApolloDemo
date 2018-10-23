package com.tl.demo.springboot.utils;

import com.ctrip.framework.apollo.core.utils.StringUtils;
import com.tl.demo.springboot.enums.JasyptAlgorithmEnum;
import org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * simple usage
 */
public class JasyptUtil {
    /**
     *
     * @param password 需要加密的密码
     * @param secertKey 秘钥
     * @param jasyptAlgorithmEnum 加密算法
     * @return 加密后的结果
     */
    public static String encrypt(String password, String secertKey, JasyptAlgorithmEnum jasyptAlgorithmEnum) {

        //输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        PrintStream cacheStream = new PrintStream(byteArrayOutputStream);

        //更换数据输出位置
        System.setOut(cacheStream);

        //加密参数组装
        JasyptPBEStringEncryptionCLI.main(
                new String[]{
                        "input=" + password,
                        "password=" + secertKey,
                        "algorithm=" + jasyptAlgorithmEnum.toString()});

        //执行加密后的输出
        String message = byteArrayOutputStream.toString();
        System.err.println(message);
        String str = replaceBlank(message);
        String encrypted = str.substring(str.lastIndexOf("-") + 1);

        return encrypted;
    }

    /**
     * 替换制表符、空格、换行符
     * @param str 待替换内容
     * @return
     */
    private static String replaceBlank(String str) {
        String dest = "";
        if (!StringUtils.isEmpty(str)) {
            Matcher matcher = Pattern.compile("\\s*|\t|\r|\n").matcher(str);
            dest = matcher.replaceAll("");
        }
        return dest;
    }

    public static void main(String... args){
        String encrypt = JasyptUtil.encrypt("sqlPwd", "key", JasyptAlgorithmEnum.PBEWithMD5AndDES);
        System.err.println(encrypt);
    }
}
