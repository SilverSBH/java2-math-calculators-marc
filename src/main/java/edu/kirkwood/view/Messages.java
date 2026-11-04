package edu.kirkwood.view;

import static edu.kirkwood.view.UIUtility.displayMessage;

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
}
