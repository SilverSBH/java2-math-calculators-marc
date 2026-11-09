package edu.kirkwood.controller.oleksiy;

import edu.kirkwood.model.oleksiy.QuadraticEquation;
import edu.kirkwood.view.Messages;

import java.util.Arrays;

import static edu.kirkwood.view.Messages.quadraticEquationGoodbye;
import static edu.kirkwood.view.UIUtility.*;
import static edu.kirkwood.view.UserInput.getInt;
import static edu.kirkwood.view.UserInput.getString;

public class QuadraticEquationCalculator {
    public static final String INVALID_INPUT = "Invalid Input. Please make sure you are using whole numbers or fractions and renter the input.";
    public static void start(){
        String result = null;
        double sectionAValueAsDouble = 0;
        double sectionBValueAsDouble = 0;
        double sectionCValueAsDouble = 0;

        Messages.quadraticEquationsGreet();

        things: while(true){
            // Get section A from user
            String sectionAValueAsString = getString("Input section A here. Hit q to quit.");
            if(sectionAValueAsString.equalsIgnoreCase("q")){
                break;
            }

            try {
                sectionAValueAsDouble = convertFractionToDecimal(sectionAValueAsString);
            }
            catch (Exception e){
                displayError(e.getMessage());
                continue;
            }

            // Get section B from user
            String sectionBValueAsString = getString("Input section B here.");
            if(sectionBValueAsString.equalsIgnoreCase("q")){
                break;
            }

            try {
                sectionBValueAsDouble = convertFractionToDecimal(sectionBValueAsString);
            }
            catch (Exception e){
                displayError(e.getMessage());
                continue;
            }

            // Get section C from user
            String sectionCValueAsString = getString("Input section C here.");
            if(sectionCValueAsString.equalsIgnoreCase("q")){
                break;
            }

            try {
                sectionCValueAsDouble = convertFractionToDecimal(sectionCValueAsString);
            }
            catch (Exception e){
                displayError(e.getMessage());
                continue;
            }

            // If we've made it this far all sections are in a form that is usable. Create a QuadraticEquation object with them.
            QuadraticEquation quadraticEquation = null;

            try {
                //quadraticEquation = validateSections(sectionAValueAsDouble, sectionBValueAsDouble, sectionCValueAsDouble);
                quadraticEquation = new QuadraticEquation(sectionAValueAsDouble, sectionBValueAsDouble, sectionCValueAsDouble);
            }
            catch (Exception e){
                displayError(e.getMessage());
                continue;
            }

            String root1 = "";
            String root2 = "";

            String[] menuItems = {"Find the Y-Intercept", "Find the Vertex", "Convert to Vertex Form", "Find the roots", "Convert to Intercept Form", "QUIT"};
            printMenu("Choose what you wish to do with your Quadratic Equation.", menuItems);
            int choice = getInt("Choose an option", true, 1, menuItems.length);
            switch(choice){
                case 1:
                    result = quadraticEquation.findYIntercept();
                    break;
                case 2:
                    result = Arrays.toString(quadraticEquation.findVertex());
                    break;
                case 3:
                    result = quadraticEquation.convertToVertexForm(quadraticEquation.findVertex());
                    break;
                case 4:
                    try {
                        root1 = "(" + quadraticEquation.findRoots()[0] + ", 0)";
                        root2 = "(" + quadraticEquation.findRoots()[1] + ", 0)";
                        //result = Arrays.toString(quadraticEquation.findRoots()); // This may need to be reformatted
                        result = root1 + ", " + root2;
                    }
                    catch (IllegalArgumentException e){
                        displayError(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        result = quadraticEquation.convertToSlopeInterceptForm(quadraticEquation.findRoots(), quadraticEquation.getSectionA());
                    }
                    catch (IllegalArgumentException e){
                        displayError(e.getMessage());
                    }
                    break;
                default:
                    break things;
            }

            if(result != null){
                System.out.println(result);
            }
            pressEnterToContinue();
        }
        quadraticEquationGoodbye();
    }

    /**
     * Converts any fractions inputted as a String into a double decimal.
     *
     * @param fraction is what needs to be converted into a decimal.
     * @return resulting decimal as a double
     */
    public static double convertFractionToDecimal(String fraction){
        double result = 0;
        double[] parts = {0,0};
        fraction = fraction.trim();

        if(!fraction.contains("/")){
            try {
                result = Double.parseDouble(fraction);
            }
            catch (NumberFormatException e){
                throw new NumberFormatException(INVALID_INPUT); // Additional symbols or letters in the given whole number
            }
        }
        else {
            parts[0] = Double.parseDouble(fraction.substring(0, fraction.indexOf("/")));
            try {
                parts[1] = Double.parseDouble(fraction.substring(fraction.indexOf("/")+1));
            }
            catch (NumberFormatException e){
                throw new NumberFormatException(INVALID_INPUT); // Additional "/" in part[1]
            }

            if(parts[1] == 0){
                throw new ArithmeticException("Your fraction's denominator cannot be zero!");
            }

            result = parts[0]/parts[1]; // fun fact of the day, doubles stores "infinity" as "1.0/0.0" which is stupid and I hate it.

        }

        return result;
    }
}
