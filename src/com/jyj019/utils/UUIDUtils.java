package com.jyj019.utils;

import java.util.UUID;

/**
 * 
 * @author jyj019
 *
 * @param <T>
 */


public class UUIDUtils {
	/**
	 * 随机生成id
	 * @return
	 */
	public static String getId(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	
}
