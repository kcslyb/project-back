package cn.kcs.common.fileUtil.vcfAndCsv;

/**
 * @description: vcf util
 * @author: kcs
 * @create: 2019-01-04 11:24
 **/

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.SystemException;
import org.w3c.dom.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 */
public class VcfUtil {

    /**
     * 导入联系人
     *
     * @param in
     * @return
     * @throws SystemException
     */
    public static List<Concat> importVCFFileContact(InputStream in)
            throws SystemException {
        List<Concat> list = new ArrayList<Concat>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            Document document = new DocumentImpl();
            BufferedWriter writer = null;

            String line;
            StringBuffer bu = new StringBuffer();
            while ((line = nextLine(reader)) != null) {
                bu.append(line + "\r\n");
            }
            Pattern p = Pattern.compile("BEGIN:VCARD(\\r\\n)([\\s\\S\\r\\n\\.]*?)END:VCARD");//分组，
            Matcher m = p.matcher(bu.toString());
            while (m.find()) {
                Concat c = new Concat();
//                c.setUserId(FrameworkContextUtils.getCurrentUserId());
//                c.setStatus(Contact.STATUS_WAIT_INVITE);
                String str = m.group(0);

                //姓名
                String name = "";
                Pattern p1 = Pattern.compile("FN:.*");//分组，
                Matcher m1 = p1.matcher(str);
                while (m1.find()) {
                    name = m1.group(0).substring(m1.group(0).indexOf("FN:") + "FN:".length());
                }
                c.setTrueName(name);

                //email
                String email = "";

                if (StringUtils.isBlank(email)) {
                    Pattern p3 = Pattern.compile("EMAIL:.*");//分组，
                    Matcher m3 = p3.matcher(str);
                    while (m3.find()) {
                        email = m3.group(0).substring(m3.group(0).indexOf("EMAIL:") + "EMAIL:".length());
                    }
                }
                if (StringUtils.isBlank(email)) {
                    Pattern p4 = Pattern.compile("EMAIL;TYPE=HOME,INTERNET,pref:.*");//分组，
                    Matcher m4 = p4.matcher(str);
                    while (m4.find()) {
                        email = m4.group(0).substring(m4.group(0).indexOf("EMAIL;TYPE=HOME,INTERNET,pref:") + "EMAIL;TYPE=HOME,INTERNET,pref:".length());
                    }
                }
                c.setEmail(email);
                //tel
                String tel = "";

                if (StringUtils.isBlank(tel)) {
                    Pattern p3 = Pattern.compile("TEL:.*");//分组，
                    Matcher m3 = p3.matcher(str);
                    while (m3.find()) {
                        tel = m3.group(0).substring(m3.group(0).indexOf("TEL:") + "CELL:".length());
                    }
                }
                if (StringUtils.isBlank(tel)) {
                    Pattern p4 = Pattern.compile("TEL;TYPE=PREF,CELL:.*");//分组，
                    Matcher m4 = p4.matcher(str);
                    while (m4.find()) {
                        tel = m4.group(0).substring(m4.group(0).indexOf("TEL;TYPE=PREF,CELL:") + "TEL;TYPE=PREF,CELL:".length());
                    }
                }
                c.setTelePhone(tel);

                list.add(c);
            }
            reader.close();
        } catch (Exception e) {
        }
        return list;
    }

    public static String nextLine(BufferedReader reader) throws IOException {
        String line;
        String nextLine;
        do {
            line = reader.readLine();
            if (line == null) {
                return null;
            }
        } while (line.length() == 0);
        while (line.endsWith("=")) {
            line = line.substring(0, line.length() - 1);
            line += reader.readLine();
        }
        reader.mark(1000);
        nextLine = reader.readLine();
        if ((nextLine != null) && (nextLine.length() > 0) && ((nextLine.charAt(0) == 0x20) || (nextLine.charAt(0) == 0x09))) {
            line += nextLine.substring(1);
        } else {
            reader.reset();
        }
        line = line.trim();
        return line;
    }

    /*
     * 解码
     */
    public static String qpDecoding(String str) {
        if (str == null) {
            return "";
        }
        try {
            str = str.replaceAll("=\n", "");
            byte[] bytes = str.getBytes("US-ASCII");
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                if (b != 95) {
                    bytes[i] = b;
                } else {
                    bytes[i] = 32;
                }
            }
            if (bytes == null) {
                return "";
            }
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            for (int i = 0; i < bytes.length; i++) {
                int b = bytes[i];
                if (b == '=') {
                    try {
                        int u = Character.digit((char) bytes[++i], 16);
                        int l = Character.digit((char) bytes[++i], 16);
                        if (u == -1 || l == -1) {
                            continue;
                        }
                        buffer.write((char) ((u << 4) + l));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                } else {
                    buffer.write(b);
                }
            }
            return new String(buffer.toByteArray(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String qpEncodeing(String str) {
        char[] encode = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < encode.length; i++) {
            if ((encode[i] >= '!') && (encode[i] <= '~') && (encode[i] != '=') && (encode[i] != '\n')) {
                sb.append(encode[i]);
            } else if (encode[i] == '=') {
                sb.append("=3D");
            } else if (encode[i] == '\n') {
                sb.append("\n");
            } else {
                StringBuffer sbother = new StringBuffer();
                sbother.append(encode[i]);
                String ss = sbother.toString();
                byte[] buf = null;
                try {
                    buf = ss.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (buf.length == 3) {
                    for (int j = 0; j < 3; j++) {
                        String s16 = String.valueOf(Integer.toHexString(buf[j]));
                        // 抽取中文字符16进制字节的后两位,也就是=E8等号后面的两位,
                        // 三个代表一个中文字符
                        char c16_6;
                        char c16_7;
                        if (s16.charAt(6) >= 97 && s16.charAt(6) <= 122) {
                            c16_6 = (char) (s16.charAt(6) - 32);
                        } else {
                            c16_6 = s16.charAt(6);
                        }
                        if (s16.charAt(7) >= 97 && s16.charAt(7) <= 122) {
                            c16_7 = (char) (s16.charAt(7) - 32);
                        } else {
                            c16_7 = s16.charAt(7);
                        }
                        sb.append("=" + c16_6 + c16_7);
                    }
                }
            }
        }
        return sb.toString();
    }

    public List<Concat> importVcf(String filsepath) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File("contact.vcf"));
            List<Concat> addressBeans = this.importVCFFileContact(fis);
            System.out.println(addressBeans.size());
            if (addressBeans.size() > 0) {
                for (Concat addressBean : addressBeans) {
                    System.out.println("tureName : " + addressBean.getTrueName());
                    System.out.println("mobile : " + addressBean.getMobile());
                    System.out.println("workMobile : " + addressBean.getWorkMobile());
                    System.out.println("Email : " + addressBean.getEmail());
                    System.out.println("--------------------------------");
                }
                return addressBeans;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 编码
     */

    public void exportVcf() {
        try {
            List<Concat> addressBeans = new ArrayList<Concat>();
            Concat addressBean = new Concat();
            addressBean.setTrueName("zhangjie");
            addressBean.setMobile("18255963695");
            addressBeans.add(addressBean);

            addressBean = new Concat();
            addressBean.setTrueName("张三");
            addressBean.setMobile("15255963695");
            addressBeans.add(addressBean);

            File file = new File("src/export_contacts.vcf");
            if (file.exists()) {
                file.createNewFile();
            }
            BufferedWriter reader = new BufferedWriter(new PrintWriter(file));
            for (Concat bean : addressBeans) {
                reader.write("BEGIN:VCARD");
                reader.write("\r\n");
                reader.write("VERSION:2.1");
                reader.write("\r\n");
                reader.write("N;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:" + this.qpEncodeing(bean.getTrueName()) + ";");
                reader.write("\r\n");
                if ("" != bean.getMobile() && bean.getMobile() != null) {
                    reader.write("TEL;CELL:" + bean.getMobile() + "");
                    reader.write("\r\n");
                }
                if ("" != bean.getWorkMobile() && bean.getWorkMobile() != null) {
                    reader.write("TEL;WORK:" + bean.getWorkMobile() + "");
                    reader.write("\r\n");
                }

                if ("" != bean.getTelePhone() && bean.getTelePhone() != null) {
                    reader.write("TEL;HOME:" + bean.getTelePhone() + "");
                    reader.write("\r\n");
                }
                if ("" != bean.getEmail() && bean.getEmail() != null) {
                    reader.write("EMAIL:" + bean.getEmail() + "");
                    reader.write("\r\n");
                }
                reader.write("END:VCARD");
                reader.write("\r\n");
            }
            reader.flush();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
