package com.ruoyi.outbound.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CheckFileTypeUtil {
        private static final Logger logger = LoggerFactory.getLogger(CheckFileTypeUtil.class);

        private CheckFileTypeUtil() {

        }

        /**
         * 缓存文件头信息-文件头信息
         */
        private static final HashMap<String, String> FILE_TYPE_MAP = new HashMap<>();

        static {
            // JPEG, jpg
            FILE_TYPE_MAP.put("FFD8FF", "jpg");
            FILE_TYPE_MAP.put("89504E", "png");
            FILE_TYPE_MAP.put("47494638", "gif");
            // Matroska stream file (mkv)
            FILE_TYPE_MAP.put("1A45DFA3934282886D6174726F736B61", "mkv");
            // MPEG-4 video files (mp4)
            FILE_TYPE_MAP.put("000000", "mp4");
            FILE_TYPE_MAP.put("255044", "pdf");
            // MS Compound Document v1 or Lotus Approach APR file
            // doc, dot, xls, xlt, xla, ppt, apr, ppa, pps, pot, msi, sdw, db
            FILE_TYPE_MAP.put("D0CF11E0", "doc");
            FILE_TYPE_MAP.put("504B0304", "docx");
            FILE_TYPE_MAP.put("D0CF11E0", "xls");
            FILE_TYPE_MAP.put("504B0304", "xlsx");
            FILE_TYPE_MAP.put("D0CF11E0", "ppt");
            FILE_TYPE_MAP.put("504b0304", "pptx");

            // TIFF, tif
            FILE_TYPE_MAP.put("49492A", "tif");
            // 16色位图 (bmp)
            FILE_TYPE_MAP.put("424d22", "bmp");
            // 24位位图 (bmp)
            FILE_TYPE_MAP.put("424d82", "bmp");
            // 256色位图 (bmp)
            FILE_TYPE_MAP.put("424d8e", "bmp");
            FILE_TYPE_MAP.put("414331", "dwg");
            FILE_TYPE_MAP.put("3C2144", "htm");
            // HyperText Markup Language 3 (html)
            FILE_TYPE_MAP.put("3C21444F4354", "html");
            FILE_TYPE_MAP.put("48544d", "css");
            FILE_TYPE_MAP.put("696b2e", "js");
            // Rich Text Format (rtf)
            FILE_TYPE_MAP.put("7B5C72", "rtf");
            // Photoshop (psd)
            FILE_TYPE_MAP.put("384250", "psd");
            // Icon File (ico)
            FILE_TYPE_MAP.put("00000100", "ico");
            // Email (eml)
            FILE_TYPE_MAP.put("44656C69766572792D646174653A", "eml");
            // MS Access (mdb, mda, mde, mdt)
            FILE_TYPE_MAP.put("5374616E64617264204A", "mdb");
            // Postscript (ps, eps)
            FILE_TYPE_MAP.put("252150532D41646F6265", "ps");
            // Adobe EPS File (eps)
            FILE_TYPE_MAP.put("25215053", "eps");
            // Real Media streaming media file (rm, rmvb)
            FILE_TYPE_MAP.put("2E524D46", "rmvb");
            FILE_TYPE_MAP.put("2E524D", "rm");
            // Flash video file (flv, f4v)
            FILE_TYPE_MAP.put("464C5601", "flv");
            // MPEG-1 Audio Layer 3 (MP3) audio file (mp3)
            FILE_TYPE_MAP.put("494433", "mp3");
            // MPEG (mpg)
            FILE_TYPE_MAP.put("000001BA", "mpg");
            // MPEG Movie (mpg, mpeg)
            FILE_TYPE_MAP.put("000001B3", "mpg");
            FILE_TYPE_MAP.put("3026b2", "wmv");
            // Windows Media (asf)
            FILE_TYPE_MAP.put("3026B2758E66CF11", "asf");
            // Wave (wav)
            FILE_TYPE_MAP.put("57415645", "wav");
            // Audio Video Interleave (avi)
            FILE_TYPE_MAP.put("41564920", "avi");
            // Musical Instrument Digital Interface (MIDI) sound file (MID, MIDI)
            FILE_TYPE_MAP.put("4D546864", "mid");
            // ZIP Archive (zip, jar, zipx)
            FILE_TYPE_MAP.put("504B3030504B0304", "zip");
            // RAR Archive File (rar)
            FILE_TYPE_MAP.put("52617221", "rar");
            FILE_TYPE_MAP.put("235468", "ini");
            // EXE, DLL, OCX, OLB, IMM, IME
            FILE_TYPE_MAP.put("4d5a90", "exe");
            FILE_TYPE_MAP.put("3c2540", "jsp");
            FILE_TYPE_MAP.put("4d616e", "mf");
            // XML Document (xml)
            FILE_TYPE_MAP.put("3C3F786D6C", "xml");
            FILE_TYPE_MAP.put("494e53", "sql");
            FILE_TYPE_MAP.put("706163", "java");
            FILE_TYPE_MAP.put("406563", "bat");
            // Gzip Archive File (gz, tar, tgz)
            FILE_TYPE_MAP.put("1f8b", "gz");
            FILE_TYPE_MAP.put("6c6f67", "properties");
            FILE_TYPE_MAP.put("cafeba", "class");
            // Microsoft Compiled HTML Help File (chm)
            FILE_TYPE_MAP.put("49545346", "chm");
            FILE_TYPE_MAP.put("040000", "mxp");
            FILE_TYPE_MAP.put("643130", "torrent");
            // Quicktime (mov)
            FILE_TYPE_MAP.put("6D6F6F76", "mov");
            // WordPerfect (wpd)
            FILE_TYPE_MAP.put("FF575043", "wpd");
            // Outlook Express (dbx)
            FILE_TYPE_MAP.put("CFAD12FEC5FD746F", "dbx");
            // Microsoft Outlook Personal Folder file (pst)
            FILE_TYPE_MAP.put("2142444E", "pst");
            // Quicken (qdf)
            FILE_TYPE_MAP.put("AC9EBD8F", "qdf");
            // Windows Password (pwl)
            FILE_TYPE_MAP.put("E3828596", "pwl");
            // Real Audio File (ra, ram)
            FILE_TYPE_MAP.put("2E7261FD", "ram");
        }

        /**
         * 根据文件路径获取文件原始格式
         *
         * @param filePath 文件路径
         * @return 文件头信息
         */
        public static String getFileType(String filePath) {

            logger.info("文件路径: {}", filePath);

            // 根据文件路径获取文件头信息
            String header = getFileHeader(filePath);

            String fileType = "";

            if (StringUtils.isNotBlank(header)) {
                List<String> lFileTypes = FILE_TYPE_MAP.keySet().stream().collect(Collectors.toList());

                for (int i = 0; i < lFileTypes.size(); i++) {
                    if (header.contains(lFileTypes.get(i).toUpperCase()) || lFileTypes.get(i).toUpperCase().contains(header)) {
                        fileType = FILE_TYPE_MAP.get(lFileTypes.get(i));
                        break;
                    }
                }
            }
            return fileType;
        }

        public static String getFileType(MultipartFile importFile) {
            // 根据文件路径获取文件头信息
            String header = getFileHeader(importFile);
            String fileType = "";

            if (StringUtils.isNotBlank(header)) {
                List<String> lFileTypes = FILE_TYPE_MAP.keySet().stream().collect(Collectors.toList());

                for (int i = 0; i < lFileTypes.size(); i++) {
                    if (header.contains(lFileTypes.get(i).toUpperCase()) || lFileTypes.get(i).toUpperCase().contains(header)) {
                        fileType = FILE_TYPE_MAP.get(lFileTypes.get(i));
                        break;
                    }
                }
            }
            return fileType;
        }


        public static String getFileHeader(MultipartFile importFile) {
            String value = null;
            try{
                byte[] b = new byte[4];
                /*
                 * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length
                 * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
                 * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
                 */
                importFile.getInputStream().read(b, 0, b.length);
                String jsonString = JSON.toJSONString(b);
                logger.info("从输入流中将数据读入至 byte 数组中: {}", jsonString);

                // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
                value = bytesToHexString(b);
            }catch (IOException exception){
                exception.printStackTrace();
            }
            return value;
        }
        /**
         * 根据文件路径获取文件头信息
         *
         * @param filePath 文件路径
         * @return 文件头信息
         */
        public static String getFileHeader(String filePath) {

            String value = null;
            try (FileInputStream is = new FileInputStream(filePath)) {
                byte[] b = new byte[4];
                /*
                 * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length
                 * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
                 * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
                 */
                is.read(b, 0, b.length);
                String jsonString = JSON.toJSONString(b);
                logger.info("从输入流中将数据读入至 byte 数组中: {}", jsonString);

                // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
                value = bytesToHexString(b);

            } catch (IOException e) {
                logger.info("获取文件头信息失败");
            }
            return value;
        }

        /**
         * 将要读取文件头信息的文件的byte数组转换成string类型表示
         *
         * @param src 要读取文件头信息的文件的byte数组
         * @return 文件头信息
         */
        private static String bytesToHexString(byte[] src) {

            StringBuilder builder = new StringBuilder();
            if (src == null || src.length <= 0) {
                return null;
            }
            String hv;
            for (int i = 0; i < src.length; i++) {
                // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
                hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
                if (hv.length() < 2) {
                    builder.append(0);
                }
                builder.append(hv);
            }
            logger.info("以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写: {}", builder);
            return builder.toString();
        }

        /**
         * 测试
         *
         * @param args
         */
        public static void main(String[] args) {

            String fileType = getFileType("D:\\picture.jpg");
            System.out.println("fileType = " + fileType);
        }
}
