package edu.kirkwood.view;

import edu.kirkwood.controller.colby.TriangleCalculator;
import edu.kirkwood.controller.marc.FractionCalculator;
import edu.kirkwood.controller.Michael.PolyDifferentialCalculator;
import edu.kirkwood.controller.oleksiy.QuadraticEquationCalculator;
import edu.kirkwood.controller.zach.BoosterPackCalculator;

import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getInt;

public class MainMenu {
    /**
     * Displays the main menu and process selections until the user quits.
     */
    public static void show() {
        String[] menuItems = {
                "Marc's Fraction Calculator",
                "Michael's Polynomial Differentiation Calculator",
                "Colby's Triangle Calculator",
                "OFed's Quadratic Equation Calculator",
                "Zach's Pokemon Booster Pack Calculator",
                "Quit"
        };
        while(true) {
            printMenu("Main Menu", menuItems);
            int choice = getInt("Choose an option", true, 1, menuItems.length);
            switch(choice) {
                case 1:
                    FractionCalculator.start();
                    break;
                case 2:
                    PolyDifferentialCalculator.start();
                    break;
                case 3:
                    TriangleCalculator.start();
                    break;
                case 4:
                    QuadraticEquationCalculator.start();
                    break;
                case 5:
                    BoosterPackCalculator.start();
                    break;
                default:
                    return;
            }
        }
    }
}
