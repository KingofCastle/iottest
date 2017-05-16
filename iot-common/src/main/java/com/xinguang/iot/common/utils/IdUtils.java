/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.xinguang.iot.common.utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * @author Song
 * @version 2014-10-01
 */
public class IdUtils {

	private static SecureRandom random = new SecureRandom();

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid2() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long. 
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

    /**
     * yyyyMMddHHmmssSSS + UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuidWithTime() {
        String time = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        return time + uuid2();
    }
    
    public static void main(String[] args) {
    	System.out.println(uuid2());
    }
}
