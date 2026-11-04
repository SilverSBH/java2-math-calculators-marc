package edu.kirkwood.view.xavian;

import edu.kirkwood.controller.xavian.HalfLifeCalculator;
import edu.kirkwood.model.xavian.HalfLifeSample;
import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;

public class ReturnsSampleMethodMenu {
    /**
     * Displays the menu to choose which method to use to find the half life sample.
     * @return the half life sample generated
     */
    public static HalfLifeSample show() {
        // Remember: only want one valid loop
        String[] menuItems = {
                "Calculate Initial Value",
                "Calculate Remaining Value",
                "Calculate Time Elapsed",
                "Calculate Half Life Length",
                "Return to Sample Count Menu"
        };
        HalfLifeSample result;
        UIUtility.printMenu("Select Calculation Method", menuItems);
        // already a while loop in here, we don't need to repeat beyond that:
        int choice = UserInput.getInt("Choose an option", true, 1, menuItems.length);
        switch (choice) {
            case 1:
                // Calculate Initial Value
                result = HalfLifeCalculator.calculateInitialValue();
                break;
            case 2:
                // Calculate Remaining Value
                result = HalfLifeCalculator.calculateRemainingValue();
                break;
            case 3:
                // Calculate Time Elapsed
                result = HalfLifeCalculator.calculateTimeElapsed();
                break;
            case 4:
                // Calculate Half Life Length
                result = HalfLifeCalculator.calculateHalfLifeLength();
                break;
            default:
                // return an unreliable sample
                result = new HalfLifeSample();
                result.setIsReliable(false);
                return result;
        }
        UIUtility.pressEnterToContinue();
        return result;
    }
}
