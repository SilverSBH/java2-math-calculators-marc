package edu.kirkwood.view.xavian;

import edu.kirkwood.controller.xavian.HalfLifeCalculator;
import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;

public class MethodMenu {
    /**
     * Displays the menu in which to choose what method to use to find a half life sample
     */
    public static void show() {
        String[] menuItems = {
                "Calculate Initial Value",
                "Calculate Remaining Value",
                "Calculate Time Elapsed",
                "Calculate Half Life Length",
                "Return to Sample Count Menu"
        };
        while (true) {
            UIUtility.printMenu("Select Calculation Method", menuItems);
            int choice = UserInput.getInt("Choose an option", true, 1, menuItems.length);
            switch (choice) {
                case 1:
                    // Calculate Initial Value
                    HalfLifeCalculator.calculateInitialValue();
                    break;
                case 2:
                    // Calculate Remaining Value
                    HalfLifeCalculator.calculateRemainingValue();
                    break;
                case 3:
                    // Calculate Time Elapsed
                    HalfLifeCalculator.calculateTimeElapsed();
                    break;
                case 4:
                    // Calculate Half Life Length
                    HalfLifeCalculator.calculateHalfLifeLength();
                    break;
                default:
                    return;
            }
            UIUtility.pressEnterToContinue();
        }
    }
}
