package com.msg.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
* @Description:    http请求辅助类HttpUtil
* @Author:         luoyhong
* @CreateDate:     2019/6/24 17:37
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/24 17:37
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class HttpUtil {
    private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String httpRequest(String requestUrl,String requestMethod,String output){
        try{
            URL url = new URL(requestUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            if(null != output){
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(output.getBytes("utf-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null){
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            connection.disconnect();
            return buffer.toString();
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.toString());
            return "";
        }
    }

    public static JSONObject getServerInfo(HttpServletRequest request){
        JSONObject resultJson = new JSONObject();
        boolean flag = false;
        List<String> ip4List = new ArrayList<>();
        List<String> ip6List = new ArrayList<>();
        resultJson.put("flag",flag);
        try {
            if(request!=null){
                resultJson.put("port",request.getLocalPort());//获取服务器端口号
            }
            //根据主机名返回其可能的所有InetAddress对象
            String hostName = InetAddress.getLocalHost().getHostName();
            resultJson.put("hostName",hostName);
            InetAddress[] addresses = InetAddress.getAllByName(hostName);
            for (InetAddress addr : addresses) {
               /* System.out.println(addr.getHostName());//主机名
                System.out.println(addr.getCanonicalHostName());//主机别名*/
                String serverIp = addr.getHostAddress();//获取IP地址
                if(ObjectUtil.isStringNoBlank(serverIp)){
                    if(serverIp.length()<16){//ip4长度小于16
                        ip4List.add(serverIp);
                    }else{//ip6长度大于于16
                        ip6List.add(serverIp);
                    }
                }
            }
            flag = true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            flag = false;
        }
        resultJson.put("ip4List",ip4List);
        resultJson.put("ip6List",ip6List);
        resultJson.put("flag",flag);
        return resultJson;
    }
}
