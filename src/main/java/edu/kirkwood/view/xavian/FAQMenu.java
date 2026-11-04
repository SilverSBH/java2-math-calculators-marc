package edu.kirkwood.view.xavian;

import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;

public class FAQMenu {
    /**
     * Shows the menu for Frequently Asked Questions
     */
    public static void show() {
        String[] menuItems = {
                "What units am I meant to use?",
                "Why might I want to calculate an initial quantity?",
                "Why might I want to calculate a remaining quantity?",
                "Why might I want to calculate a time elapsed?",
                "Why might I want to calculate a half life?",
                "Why can I only compare two samples? [Easter Egg]",
                "Return to About Menu"
        };
        // half life of cobalt-60: 5.27 years
        String unitsAnswer = "\n\nYou can use any units you like, as long as they remain consistent between inputs.\n" +
                "For example, say I want to calculate the half life of a sample of cobalt-60. \n" +
                "I have 5.000 grams to begin with, and after 10 months I have 4384 milligrams left.\n" +
                "If I wanted to know the half life in years, I would need to: \n" +
                "A. Keep the initial and remaining quantities in the same units (let's use milligrams here)\n" +
                "B. Convert 10 months into 5/6 of a year (0.8333 years)\n" +
                "C. Perform the calculation WITHOUT typing the units into the calculator.\n" +
                "I should get a result back of a half life length of 5.27 years.\n\n";
        String initialQuantityReason = "\n\nAn initial quantity is useful to calculate if you want to know how much \n" +
                "radiological material was in a sample upon formation. One application is to test how much \n" +
                "radiological material escaped containment in a nuclear incident by measuring the radiation \n" +
                "in the surrounding area and calculating based on the half life of material and the time since\n" +
                "the incident.\n\n";
        String remainingQuantityReason = "\n\nA remaining quantity is useful to calculate if you want to know how much\n" +
                "radiological material will be in a sample after a certain period of time. One application is to \n" +
                "calculate how long after a nuclear incident an area will be safe, IE after how long will the\n" +
                "remaining quantity of radiological material be low enough to count as safe.\n\n";
        String timeElapsedReason = "\n\nA time elapsed is useful to calculate if you want to know how much time has \n" +
                "passed between a sample's formation and it's measurement. One application is to test how old the \n" +
                "Earth is by comparing the relative quantities of Uranium-238 and Lead-206 in ancient zirconium \n" +
                "crystals compared to modern ones.\n\n";
        String halfLifeReason = "\n\nA half life length is useful to calculate if you want to know how much time needs \n" +
                "to pass in order for half of a sample to decay. This is useful as a way to find the half life in \n" +
                "order to perform other calculations that may be more useful. This task will often be performed in \n" +
                "a research laboratory.\n\n";
        String whyOnlyTwo = "\n\nAs a half-life calculator, in order to give a nod to the incredible work at Valve, we \n" +
                "choose not to count to three. Instead, we will count One, Two, Alyx.\n\n";
        while(true) {
            UIUtility.printMenu("Frequently Asked Questions", menuItems);
            int choice = UserInput.getInt("Choose an option", true, 1, menuItems.length);
            switch (choice) {
                case 1:
                    UIUtility.displayMessage(unitsAnswer);
                    break;
                case 2:
                    UIUtility.displayMessage(initialQuantityReason);
                    break;
                case 3:
                    UIUtility.displayMessage(remainingQuantityReason);
                    break;
                case 4:
                    UIUtility.displayMessage(timeElapsedReason);
                    break;
                case 5:
                    UIUtility.displayMessage(halfLifeReason);
                    break;
                case 6:
                    UIUtility.displayMessage(whyOnlyTwo);
                    break;
                default:
                    return;
            }
            UIUtility.pressEnterToContinue();
        }

    }
}
