package com.learn.test.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UnitTest {

	Test2 test;
	
	@BeforeEach
	public void setUp() throws Exception {
		test = new Test2();	
	}
	
	@Test
	@DisplayName("Expect BTLTP1")
	public void BTLTP1() {
//		assertEquals("BTLTP1",test.BTLSx("Daily", 111, 108) );
	}

	@Test
	@DisplayName("Expect BTLTP2")
	public void BTLTP2() {
//		assertEquals("Expect BTLTP2","BTLTP2",test.BTLSx("Daily", 111, 108) );
	}

	@Test
	@DisplayName("Expect BTLTP3")
	public void BTLTP3() {
//		assertEquals("BTLTP3",test.BTLSx("Weekly", 1, 99) );
	}
	
	@Test
	@DisplayName("Expect BTLTP4")
	public void BTLTP4() {
//		assertEquals("Expect BTLTP4","BTLTP4",test.BTLSx("Weekly", 1, 108));
	}
	
	@Test
	@DisplayName("Expect BTLTP5")
	public void BTLTP5() {
//		assertEquals("Expect BTLTP5","BTLTP5",test.BTLSx("Weekly", 4, 108));
//		assertEquals("Expect BTLTP5","BTLTP5",test.BTLSx("Weekly", 26, 120) );
	}
	
	@Test
	@DisplayName("Expect BTLTP6")
	public void BTLTP6() {
//		assertEquals("Expect BTLTP6","BTLTP6",test.BTLSx("Daily", 111, 108) );
	}
	
	@Test
	@DisplayName("Expect BTLTP7")
	public void BTLTP7() {
		assertEquals("Expect BTLTP7","BTLTP7",test.BTLSx("Weekly", 4, 220) );
	}
	
	
	@Test
	@DisplayName("Expect BTLTP8")
	public void BTLTP8() {
//		assertEquals("Expect BTLTP8","BTLTP8",test.BTLSx("Weekly", 111, 300) );
	}
	
}
