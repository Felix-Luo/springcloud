package com.msg.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
* @Description:    图片相关操作辅助类ImageUtil
* @Author:         luoyhong
* @CreateDate:     2019/6/25 11:13
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/25 11:13
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class ImageUtil {

	/**
	 * @function    将图片64位编码转为图片文件
	 * @author      luoyhong
	 * @param imgStr
	 * @param path
	 * @return      boolean
	 * @exception
	 * @date        2019/6/25 11:14
	 */
	public static boolean transImgBase64StrToImg(String imgStr, String path) {

		if (imgStr == null || "".equals(imgStr))
			return false;
		if (path == null || "".equals(path))
			return false;
		try {
			File file = new File(path);
			if(!file.exists()){
				file.createNewFile();
			}
			BASE64Decoder decoder = new BASE64Decoder();
			// 解密
			byte[] b = decoder.decodeBuffer(imgStr);
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out;
			out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * @function    将图片文件转为64编码
	 * @author      luoyhong
	 * @param filePath
	 * @return      java.lang.String
	 * @exception
	 * @date        2019/6/25 11:15
	 */
	public static String getImageBase64Str(String filePath) {

	    InputStream inputStream = null;
	    byte[] data = null;
	    try {
	        inputStream = new FileInputStream(filePath);
	        data = new byte[inputStream.available()];
	        inputStream.read(data);
	        inputStream.close();
			// 加密
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(data);
	    } catch (IOException e) {
	        e.printStackTrace();
			return null;
	    }

	}
	public static boolean checkDir(String rootPath,String imgDir,String dateDir,String jylshDir){
		try{
			File tempFolder = new File(rootPath);
			if(!tempFolder.exists()){
				return false;
			}
			rootPath = rootPath+"/"+imgDir;
			tempFolder = new File(rootPath);
			if(!tempFolder.exists()){
				tempFolder.mkdir();
			}
			rootPath = rootPath+"/"+dateDir;
			tempFolder = new File(rootPath);
			if(!tempFolder.exists()){
				tempFolder.mkdir();
			}
			rootPath = rootPath+"/"+jylshDir;
			tempFolder = new File(rootPath);
			if(!tempFolder.exists()){
				tempFolder.mkdir();
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return false;
		}
	}
	public static Image getImageFile(String filePath) {
		Image image = null;
		try {
			image = ImageIO.read(new File(filePath));
			return image;
		} catch (Exception e) {
			e.printStackTrace();
			return image;
		}
	}
	public static void transImageFile(String sourceFilePath,String targetFilePath,String formatName) {
		try {
			//要想保存这个对象的话把image声明为BufferedImage 类型
			BufferedImage bufferedImagemage = ImageIO.read(new File(sourceFilePath));
			ImageIO.write(bufferedImagemage, formatName, new File(targetFilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
