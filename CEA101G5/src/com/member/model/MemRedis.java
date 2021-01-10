package com.member.model;

import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class MemRedis {
	public String setAuthCode(String memPhone) {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");

		String code = getAuthCode();
		System.out.println("Auth code is: " + code);

		jedis.set(memPhone, code);
		jedis.expire(memPhone, 180);

		Scanner sc = new Scanner(System.in);
		System.out.println("請輸入驗證碼：");
		String str = sc.next();

		// 假設會員點擊驗證信
		String testAuth = jedis.get(memPhone);
		if (testAuth == null) {
			System.out.println("連結信已逾時，請重新申請");
		} else if (str.equals(testAuth)) {
			System.out.println("驗證成功!");
		} else {
			System.out.println("驗證有誤，請重新申請");
		}

		sc.close();
		jedis.close();
		return code;
	}

	public String getAuthCode() {
		StringBuffer code = new StringBuffer();
		String elements = "01234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < 8; i++)
			code.append(elements.charAt((int) (Math.random() * elements.length())));
		return code.toString();
	}
}
