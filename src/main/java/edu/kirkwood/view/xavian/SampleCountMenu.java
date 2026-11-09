package edu.kirkwood.view.xavian;

import edu.kirkwood.model.xavian.HalfLifeSample;
import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;

public class SampleCountMenu {
    /**
     * Displays the menu in which you choose how many samples to test
     */
    public static void show() {
        String[] menuItems = {
                "Run a calculation for one sample",
                "Run a calculation for two samples",
                "About this calculator",
                // Should be written for someone entirely unfamiliar with half-lives
                "Return to Kirkwood Calculators"
        };
        while (true) {
            UIUtility.printMenu("Sample Count Menu", menuItems);
            int choice = UserInput.getInt("Choose an option", true, 1, menuItems.length);
            switch (choice) {
                case 1:
                    // run calculation for one sample
                    // does not need returned sample
                    MethodMenu.show();
                    // results already displayed
                    break;
                case 2:
                    // run calculation for two samples
                    // does need to return samples (one each time)
                    System.out.println();
                    UIUtility.displayMessage("Calculating Sample 1: ");
                    HalfLifeSample sample1 = ReturnsSampleMethodMenu.show();
                    System.out.println();
                    HalfLifeSample comparisonSample = new HalfLifeSample();
                    comparisonSample.setIsReliable(false);
                    HalfLifeSample sample2;
                    if (sample1.equals(comparisonSample)) {
                        sample2 = comparisonSample;
                    } else {
                        UIUtility.displayMessage("Calculating Sample 2: ");
                        sample2 = ReturnsSampleMethodMenu.show();
                    }
                    // then do string compareTo (contains int compareTo)
                    System.out.println();
                    try {
                        String comparison = sample1.stringCompareTo(sample2);
                        // then display the results
                        UIUtility.displayMessage(comparison);
                        // once displayed, no need to keep samples. Let the switch closing kill them.
                    } catch (Exception e) {
                        UIUtility.displayError(e.getMessage());
                    }
                    UIUtility.pressEnterToContinue();
                    break;
                case 3:
                    // connect to about menu
                    AboutMenu.show();
                    break;
                default:
                    return;
            }
        }
    }
}
