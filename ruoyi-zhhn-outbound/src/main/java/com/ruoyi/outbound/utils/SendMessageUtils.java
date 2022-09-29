package com.ruoyi.outbound.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SendMessageUtils {


    public static String getMD5Str(String str) {
        String result = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            System.out.println("MD5(" + str + ",32小写) = " + result);
        }catch (NoSuchAlgorithmException exception){
            exception.printStackTrace();
        }
        return result;
    }



    public static String getMessageId(){
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            val.append(String.valueOf(random.nextInt(10)));
        }
        return val.toString();
    }
}
