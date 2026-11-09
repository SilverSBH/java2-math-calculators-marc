package edu.kirkwood.model.Michael;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class PolynomialTermTest {

	private PolynomialTerm ptDefault, ptConst0, ptConst1, ptConstVar, ptVarXOrder1, ptVarXOrder2, ptVarY, ptChain;
	
	@BeforeEach
	void setUp() throws Exception {
		// var, exp, coe
		ptDefault = new PolynomialTerm();
		ptConst0 = new PolynomialTerm(0, "x", 0);
		ptConst1 = new PolynomialTerm(1, "x", 0);
		ptConstVar = new PolynomialTerm(6, "x", 0);
		ptVarXOrder1 = new PolynomialTerm(3, "x", 1);
		ptVarXOrder2 = new PolynomialTerm(5, "x", 2);
		ptVarY = new PolynomialTerm(5, "y", 2);
		ptChain = new PolynomialTerm(ptVarXOrder2, 3);
	}

	@Test
	@DisplayName("Testing functionality of default and three-param constructors.")
	void testConstructors1() {
		assertEquals(ptDefault, ptConst1);
	}

	@Test
	@DisplayName("Testing functionality of the 'chain' constructor.")
	void testConstructors2() {
		PolynomialTerm ptTest = new PolynomialTerm(3, "x", 1);
		assertEquals(ptTest, ptChain);
	}

	@Test
	@DisplayName("Test fetching variable component.")
	void testGetVar() {
		String expected = "x";
		String actual = ptVarXOrder1.getVar();
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Test fetching exponent component.")
	void testGetExp() {
		int expected = 2;
		assertEquals(expected, ptVarXOrder2.getExp());
	}

	@Test
	@DisplayName("Test fetching coefficient component.")
	void testGetCoeff() {
		int expected = 5;
		int actual = ptVarXOrder2.getCoeff();
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Testing differentiate on the constant 0.")
	void testDifferentiateZero() {
		PolynomialTerm ptTest = new PolynomialTerm(0, "x", 0);
		ptConst0.differentiate();
		assertEquals(ptTest, ptConst0, "Expected: " + ptTest + " Actual: " + ptConst0);
	}
	
	@Test
	@DisplayName("Testing differentiate on a constant term.")
	void testDifferentiateConst() {
		PolynomialTerm ptTest = new PolynomialTerm(0, "x", 0);
		ptConstVar.differentiate();
		assertEquals(ptTest, ptConstVar);
	}

	@Test
	@DisplayName("Testing differentiate on a term of order 1.")
	void testDifferentiateOrder1() {
		PolynomialTerm ptTest = new PolynomialTerm(3, "x", 0);
		ptVarXOrder1.differentiate();
		assertEquals(ptTest, ptVarXOrder1);
	}
	
	@Test
	@DisplayName("Testing differentiate on a term of order 2 or greater.")
	void testDifferentiateHigherOrder() {
		PolynomialTerm ptTest = new PolynomialTerm(10, "x", 1);
		ptVarXOrder2.differentiate();
		assertEquals(ptTest, ptVarXOrder2);
	}
	
	@Test
	@DisplayName("Testing toString method.")
	void testToString() {
		String expected = "5x^2";
		String actual = ptVarXOrder2.toString();
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Testing the .equals method, expect True.")
	void testEquals() {
		assertEquals(ptDefault, ptConst1);
	}
	
	@Test
	@DisplayName("Test .equals with different variables.")
	void testEqualsVar()
	{
		assertFalse(ptVarXOrder2.equals(ptVarY));
	}
	
	@Test
	@DisplayName("Test .equals with different exponents.")
	void testEqualsExp()
	{
		PolynomialTerm testExp = new PolynomialTerm(5, "x", 3);
		assertFalse(ptVarXOrder2.equals(testExp));
	}
	
	@Test
	@DisplayName("Test .equals with different coefficients.")
	void testEqualsCoeff()
	{
		PolynomialTerm testCoeff = new PolynomialTerm(3, "x", 2);
		assertFalse(ptVarXOrder2.equals(testCoeff));
	}

}
