package com.zelin.utils;

import java.util.UUID;

public class CommUtils {


	//判断字符串是否为空
	public static boolean isEmpty(String str){
		return null == str || str.equals("") ;
	}


	//处理请求的url路径，得到最后一个/后？前的所有路径部分
	public static String getPath(String fullPath){
		String path = "";
		//判断当前的路径中是否有？
		//http://localhost:8080/Shiro-prj01/index.do?username=aaa&password=e4ewe
		String pre = fullPath;
		if(fullPath.contains("?")){
			//得到？前面部分的字符串
			pre = fullPath.split("?")[0];
			//得到最后一个/的索引
//			int lastIndex = fullPath.lastIndexOf("/");
//			path = pre.substring(lastIndex+1);
		}
		int lastIndex = fullPath.lastIndexOf("/");
		path = pre.substring(lastIndex+1);
		return path;
	}
	//生成一个32位的UUID字符串
	public static String createUUID() {
		return UUID.randomUUID().toString();
	}
}
