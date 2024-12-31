package com.nofirst.zhihu.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The type Md 5.
 */
public class MD5 {
    private static final char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * Instantiates a new Md 5.
     */
    public MD5() {
    }

    /**
     * Md 5 string.
     *
     * @param input the input
     * @return the string
     */
    public static String md5(String input) {
        if (input == null) {
            return null;
        } else {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                byte[] inputByteArray = input.getBytes();
                messageDigest.update(inputByteArray);
                byte[] resultByteArray = messageDigest.digest();
                return byteArrayToHex(resultByteArray);
            } catch (NoSuchAlgorithmException var4) {
                return null;
            }
        }
    }

    /**
     * Md 5 string.
     *
     * @param file the file
     * @return the string
     */
    public static String md5(File file) {
        try {
            if (!file.isFile()) {
                System.err.println("文件" + file.getAbsolutePath() + "不存在或者不是文件");
                return null;
            }

            FileInputStream in = new FileInputStream(file);
            String result = md5((InputStream) in);
            in.close();
            return result;
        } catch (FileNotFoundException var3) {
            FileNotFoundException e = var3;
            e.printStackTrace();
        } catch (IOException var4) {
            IOException e = var4;
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Md 5 string.
     *
     * @param in the in
     * @return the string
     */
    public static String md5(InputStream in) {
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];

            int read;
            while ((read = in.read(buffer)) != -1) {
                messagedigest.update(buffer, 0, read);
            }

            in.close();
            String result = byteArrayToHex(messagedigest.digest());
            return result;
        } catch (NoSuchAlgorithmException var5) {
            NoSuchAlgorithmException e = var5;
            e.printStackTrace();
        } catch (FileNotFoundException var6) {
            FileNotFoundException e = var6;
            e.printStackTrace();
        } catch (IOException var7) {
            IOException e = var7;
            e.printStackTrace();
        }

        return null;
    }

    private static String byteArrayToHex(byte[] byteArray) {
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        byte[] var6 = byteArray;
        int var5 = byteArray.length;

        for (int var4 = 0; var4 < var5; ++var4) {
            byte b = var6[var4];
            resultCharArray[index++] = hexDigits[b >>> 4 & 15];
            resultCharArray[index++] = hexDigits[b & 15];
        }

        return new String(resultCharArray);
    }
}
