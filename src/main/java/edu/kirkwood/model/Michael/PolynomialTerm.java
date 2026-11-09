package edu.kirkwood.model.Michael;

/**
 * A class for defining terms of a Polynomial object.
 * Each one has a Variable, Coefficient, and Exponent component.
 */
public class PolynomialTerm {
	
	/**
	 * Instance variable describing the Variable component.
	 */
	private String variable;
	/**
	 * Instance variable describing the Exponent component.
	 */
	private int exponent;
	/**
	 * Instance variable describing the Coefficient component.
	 */
	private int coefficient;
	
	/**
	 * A default PolynomialTerm constructor that creates the constant 1.
	 */
	public PolynomialTerm()
	{
		setCoeff(1);
		setVar("x");
		setExp(0);
	}
	/**
	 * A constructor for creating a general constant term.
	 * Effectively identical to calling PolynomialTerm(coe, "x", 0).
	 * @param coe The value of the constant term.
	 */
	public PolynomialTerm(int coe)
	{
		setCoeff(coe);
		setVar("x");
		setExp(0);
	}
	/**
	 * A specialized constructor for creating a linear term with no coefficient.
	 * Effectively the same as calling PolynomialTerm(1, var, 1).
	 * @param var The variable to use.
	 */
	public PolynomialTerm(String var)
	{
		setCoeff(1);
		setVar(var);
		setExp(1);
	}
	/**
	 * A constructor for creating a general linear term.
	 * Functionally identical to calling PolynomialTerm(coe, var, 1).
	 * @param coe The coefficient of the term.
	 * @param var The variable to use.
	 */
	public PolynomialTerm(int coe, String var)
	{
		setCoeff(coe);
		setVar(var);
		setExp(1);
	}
	/**
	 * Constructor for creating a term of order 2+ with no coefficient.
	 * Functionally identical to calling PolynomialTerm(1, var, exp).
	 * @param var The variable to use.
	 * @param exp The exponent of the term.
	 */
	public PolynomialTerm(String var, int exp)
	{
		setCoeff(1);
		setVar(var);
		setExp(exp);
	}
	/**
	 * Constructor for creating a general order 2+ term.
	 * @param coeff The coefficient value.
	 * @param var The variable of the term.
	 * @param exp The exponent value.
	 */
	public PolynomialTerm(int coeff, String var, int exp)
	{
		setCoeff(coeff);
		setVar(var);
		setExp(exp);
	}
	/**
	 * @deprecated
	 * This constructor is intended to be used when building a full Polynomial
	 * out of PolynomialTerm objects. It assigns the same variable to the new object,
	 * and automatically decrements the exponent instance variable from the existing term's.
	 * @param existingTerm An existing PolynomialTerm that will be used to define this one's variable and exponent.
	 * @param coeff The value to set to this PolynomialTerm's coefficient instance variable.
	 */
	public PolynomialTerm(PolynomialTerm existingTerm, int coeff)
	{
		setVar(existingTerm.getVar());
		setExp(existingTerm.getExp() - 1);
		setCoeff(coeff);
	}
	
	/**
	 * Mutator method for instance variable 'variable'
	 * @param newValue The value to which to set 'variable'
	 */
	private void setVar(String newValue)
	{
		this.variable = newValue;
	}
	/**
	 * Accessor method for instance variable 'variable'
	 * @return The current value of 'variable'
	 */
	public String getVar()
	{
		return this.variable;
	}
	
	/**
	 * Mutator method for instance variable 'exponent'
	 * @param newValue The value to which to set 'exponent'
	 */
	private void setExp(int newValue)
	{
		this.exponent = newValue;
	}
	/**
	 * Accessor method for instance variable 'exponent'
	 * @return The current value of 'exponent'
	 */
	public int getExp()
	{
		return this.exponent;
	}
	
	/**
	 * Mutator method for instance variable 'coefficient'
	 * @param newValue The value to which to set 'coefficient'
	 */
	private void setCoeff(int newValue)
	{
		this.coefficient = newValue;
	}
	/**
	 * Accessor method for instance variable 'coefficient'
	 * @return The current value of 'coefficient'
	 */
	public int getCoeff()
	{
		return this.coefficient;
	}
	
	/**
	 * A method to determine the sign of a term.
	 * @return A String representing the sign of the term.
	 */
	public String sign()
	{
		String result = "+";
		if (this.getCoeff() < 0)
		{
			result = "-";
		}
		return result;
	}
	
	/**
	 * This method will take the current PolynomialTerm and apply differential calculus to it.
	 */
	public void differentiate()
	{
		if (exponent == 0)
		{
			setCoeff(0);
		}
		else if (exponent == 1)
		{
			setExp(0);
		}
		else
		{
			// Need to set the coefficient first, as changing exponent value will change the math.
			setCoeff(coefficient * exponent);
			setExp(exponent - 1);
		}
	}
	/**
	 * A method to copy the current term, passing by value.
	 */
	@Override
	public PolynomialTerm clone()
	{
		return new PolynomialTerm(coefficient, variable, exponent);
	}
	
	/**
	 * An override for the standard toString method.
	 * Returns the PolynomialTerm in the form 'coefficient''variable'^'exponent'
	 */
	@Override
	public String toString()
	{
		String result = "";
		if (coefficient == 0)
		{
			result += "0";
		}
		else if (exponent == 0)
		{
			result += coefficient;
		}
		else if (exponent == 1)
		{
			result += coefficient + variable;
		}
		else
		{
			result += coefficient + variable + "^" + exponent;
		}
		return result;
	}
		
	/**
	 * A custom override for the standard equals method.
	 * Determines if one PolynomialTerm is functionally equivalent to another.
	 */
	@Override
	public boolean equals(Object other)
	{
		boolean result = false;
		
		if (this != null && other != null)
		{
			if (this instanceof PolynomialTerm && other instanceof PolynomialTerm)
			{
				if (this.getCoeff() == 0 && ((PolynomialTerm) other).getCoeff() == 0)
				{
					result = true;
				}
				else if ((this.getVar()).equals(((PolynomialTerm) other).getVar()))
				{
					if ((this.getExp() == ((PolynomialTerm) other).getExp()) && 
							this.getCoeff() == ((PolynomialTerm) other).getCoeff())
					{
						result = true;
					}
				}
			}
		}
		else
		{
			result = true;
		}
		
		return result;
	}
}
