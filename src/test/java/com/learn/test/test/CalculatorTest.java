package com.learn.test.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorTest {

	private Calculator calculator;

	@BeforeEach
	public void setUp() throws Exception {
		calculator = new Calculator();
//		System.out.println("1");
	}

	@Test
	@DisplayName("Simple multiplication should work")
	public void testMultiply() {
		assertEquals("Regular multiplication should work", 20, calculator.multiply(4, 5));
	}

	@RepeatedTest(5)
	@DisplayName("Ensure correct handling of zero")
	public void testMultiplyWithZero() {
		assertEquals("Multiple with zero should be zero", 1, calculator.multiply(1, 1));
	}
}
