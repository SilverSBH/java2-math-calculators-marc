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
     * Welcomes the user and briefly explains exponential decay.
     */
    public static void decayGreet() {
        displayMessage("Welcome to Jade's Exponential Decay Calculator");

        System.out.println("Exponential decay describes how an amount decreases");
        System.out.println("over time at a rate proportional to what remains.");
        System.out.println("For example, it can describe radioactive decay");
        System.out.println("or how certain medications leave the body.\n");

        System.out.println("Choose what you want to calculate from the menu.");
        System.out.println("Enter the known values when prompted.");
        System.out.println("Select 'About Exponential Decay' for more information.\n");
    }

    /**
     * Explains exponential decay and the values used in calculations.
     */
    public static void decayHelp() {
        displayMessage("About Exponential Decay");

        System.out.println("Exponential decay occurs when an amount decreases");
        System.out.println("at a rate proportional to the amount remaining.\n");

        System.out.println("The formula is A = A0 * e^(-kt)\n");

        System.out.println("A0 = Initial amount (the starting quantity)");
        System.out.println("A  = Remaining amount (what is left after decay)");
        System.out.println("k  = Decay constant (the rate of decay per time unit)");
        System.out.println("t  = Elapsed time (how long the decay has occurred)\n");

        System.out.println("The decay constant (k) tells you how quickly something decays");
        System.out.println("during a specific amount of time. The elapsed time (t) tells");
        System.out.println("you how long the decay has been happening.");
        System.out.println("Both values must use the same time unit. For example,");
        System.out.println("if k is 0.1 per hour, entering 5 for elapsed time means");
        System.out.println("5 hours. The calculator does not convert time units.\n");

        System.out.println("All entered values must be greater than zero.");
        System.out.println("For decay constant and elapsed time calculations,");
        System.out.println("the remaining amount must be less than the initial amount.\n");

        System.out.println("Example:");
        System.out.println("Starting with 100 units and a decay constant of");
        System.out.println("0.1 per hour, about 36.79 units remain after 10 hours.");
    }

    /**
     * Displays a goodbye message for the exponential decay calculator.
     */
    public static void decayGoodbye() {
        displayMessage("Thank you for using Jade's Exponential Decay Calculator");
    }
}
