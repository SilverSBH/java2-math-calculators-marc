package edu.kirkwood.view.xavian;

import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;

public class AboutMenu {
    /**
     * Shows the menu for the About This Calculator selections
     */
    public static void show() {
        String[] menuItems = {
                "Display General Description",
                "Frequently Asked Questions",
                "Return to Sample Count Selection Menu"
        };
        String generalDescription = "\n\nA half life calculator is used to calculate the fourth parameter from three of the following: \n" +
                "A. The initial quantiy of radioactive material present in a sample upon the formation of said sample\n" +
                "B. The remaining quantity of radioactive material present in a sample upon measurement of said sample some time after its formation\n" +
                "C. The time elapsed between the sample formation (when the initial quanity was present)\n" +
                "   and sample measurement (when the remaining quantity was present)\n" +
                "D. The length of time in which half of the material of the radiological sample will decay\n\n" +
                "From this, you can optionally compare another sample to see where they would line up on a timeline.\n" +
                "All times elapsed will be assumed to extend to the past for this comparison.\n\n";

        while(true) {
            UIUtility.printMenu("About Menu", menuItems);
            int choice = UserInput.getInt("Choose an option", true, 1, menuItems.length);
            switch (choice) {
                case 1:
                    // Display General Description
                    UIUtility.displayMessage(generalDescription);
                    UIUtility.pressEnterToContinue();
                    break;
                case 2:
                    // Frequently Asked Questions Menu
                    FAQMenu.show();
                    break;
                default:
                    return;
            }
        }

    }
}
