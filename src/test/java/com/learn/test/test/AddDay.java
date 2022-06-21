package com.learn.test.test;

public class AddDay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		long oneDay = 86400000L;
		long dayLong = System.currentTimeMillis();
		System.out.println(dayLong + (oneDay * (365 * 3)));
	}

}
