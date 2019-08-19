package com.lsd.client.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;



public class CPUUtil {
    /**
     * 获取当前操作系统名称. return 操作系统名称 例如:windows xp,linux 等.
     */
    public static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    /**
     * 获取CPU序列号
     *
     * @return
     */
    public static String getCPUID_Windows() {
        String result = "";
        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_Processor\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.ProcessorId \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            // + "    exit for  \r\n" + "Next";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
            file.delete();
        } catch (Exception e) {

        }
        return result.trim();
    }

//
//    public static String getCPUId() throws InterruptedException {
//        String os = getOSName();
//        String cpuId = "";
//        if (os.startsWith("windows")) {
//            cpuId = CPUUtil.getCPUID_Windows();
//        } else if (os.startsWith("linux")) {
//            cpuId = CPUUtil.getCPUID_linux();
//        }
//        if(!StringUtil.isNotNullOrBlank(cpuId)){
//            cpuId="null";
//        }
//        return cpuId;
//    }


    public static String getIdentifierByWindows() {
        try{
            String result = "";
            Process process = Runtime.getRuntime().exec("cmd /c dir C:");
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));

            String line;
            while ((line = br.readLine()) != null) {
                if (line.indexOf("卷的序列号是 ") != -1) {
                    result = line.substring(line.indexOf("卷的序列号是 ") + "卷的序列号是 ".length(), line.length());
                    break;
                }
            }
            //log.info("Windows Identifier is: {}", result);
            br.close();
            return result;
        }catch (Exception e ){
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println(getIdentifierByWindows());
        System.out.println(getCPUID_Windows());
    }

}