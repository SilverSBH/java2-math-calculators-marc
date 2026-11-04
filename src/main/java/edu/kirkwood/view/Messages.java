package edu.kirkwood.view;

import static edu.kirkwood.view.UIUtility.displayMessage;
import static edu.kirkwood.view.UIUtility.printLine;

public class Messages {

    /**
     * Displays the application's welcome message.
     */
    public static void hello() {
        displayMessage("Welcome to the Kirkwood Calculators Application");
    }

    /**
     * Displays the application's farewell message.
     */
    public static void goodbye() {
        displayMessage("Thank you for using the Kirkwood Calculator. Goodbye!");
    }

    /**
     * Displays instructions for using the fraction calculator.
     */
    public static void fractionGreet() {
        displayMessage("Welcome to Marc's Fraction Calculator");
        System.out.println("Enter calculations in the format: [fraction] [operator] [fraction]");
        System.out.println("Example: 1 1/2 + 3/4\n");
    }

    /**
     * Displays the fraction calculator's farewell message.
     */
    public static void fractionGoodbye() {
        displayMessage("Thank you for using Marc's Fraction Calculator");
    }
    public static void PolyDiffHello()
    {
        displayMessage("Greetings, and welcome to Michael's Polynomial Differentiation Calculator.");
        System.out.println("Please enter a single-variable polynomial. The format for each term is: \n"
                + "[coefficient, as an integer value][variable, as a letter]^[exponent, as an integer value]\n"
                + "Between each term, place either a \'+\' or \'-\' separated by a space on each side.");
        System.out.println("Example: \'2x^3 + 3x^2 - 5x + 3\'\n");
    }
    public static void PolyDiffFarewell()
    {
        displayMessage("Thank you for using Michael's Polynomial Differentiation Calculator.\n"
                + "We hope it was useful!");
    }
    /**
     * Displays instructions for using the triangle calculator.
     */
    public static void triangleGreet(){
        displayMessage("Welcome to Colby's Triangle Calculator");
        System.out.println("Please enter the known values of the sides and/or angles of your triangle.");
        System.out.println("Press [Enter] to skip any unknown value.");
        System.out.println("Requirements:");
        System.out.println(" - Exactly 3 values must be provided.");
        System.out.println(" - At least 1 value must be a side length.");
    }

    /**
     * Displays the triangle calculator's farewell message.
     */
    public static void triangleGoodbye(){
        displayMessage("Thank you for using Colby's Triangle Calculator");
    }
  
    /**
     * Displays initial instructions for using the Quadratic Equation calculator.
     */
    public static void quadraticEquationsGreet(){
        displayMessage("Welcome to 0Fed's Quadratic Equation Calculator");
        System.out.println("Please input your quadratic equation in standard form, starting with section a.");
        System.out.println("eg. The part within brackets -> [a]x^2+bx+c");
        System.out.println("Press q at anytime to quit.");
        printLine();
    }

    /**
     * Displays the Quadratic Equation calculator's farewell message.
     */
    public static void quadraticEquationGoodbye(){
        displayMessage("Thank you for trying out OFed's Quadratic Equation formatter");
    }

    /**
     * Displays the booster pack calculator's welcome message and instructions.
     */
    public static void packGreet() {
        displayMessage("Welcome to Zach's Booster Pack Calculator");
        System.out.println("Please select a booster pack to open.");
        System.out.println("Enter your chase Pokemon and the number of packs you want to open.");
        System.out.println("The calculator will return the cumulative mathematical odds of pulling your chase Pokemon from the packs you opened.\n");
    }

    /**
     * Displays the booster pack calculator's farewell message.
     */
    public static void packGoodbye() {
        displayMessage("Thank you for using Zach's Booster Pack Calculator");
    }
}
