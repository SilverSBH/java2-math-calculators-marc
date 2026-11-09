package edu.kirkwood.controller.leesonj;

import edu.kirkwood.model.leesonj.DiePool;
import edu.kirkwood.model.marc.Fraction;
import edu.kirkwood.view.Helpers;
import edu.kirkwood.view.UIUtility;

import static edu.kirkwood.view.Messages.probabilityGoodbye;
import static edu.kirkwood.view.Messages.probabilityGreet;
import static edu.kirkwood.view.UIUtility.*;
import static edu.kirkwood.view.UserInput.getInt;
import static edu.kirkwood.view.UserInput.getString;

public class ProbabilityCalculator {
    public static final String NO_DICE_MESSAGE = "A die pool must have dice.";
    public static final String NO_SIDES_MESSAGE = "Can't have dice without sides.";
    public static final String BAD_DICE_VALUE_MESSAGE = "Unable to parse a valid number of dice, please enter a positive integer.";
    public static final String BAD_SIDES_VALUE_MESSAGE = "Unable to parse a valid number of sides, please enter a positive integer.";
    public static final String TOO_MANY_DICE_MESSAGE = "Too many dice, please enter a smaller number (less than 100).";
    public static final String TOO_MANY_SIDES_MESSAGE = "Too many sides, please enter a smaller number (max 100000000).";
    public static final String NO_DIE_POOL_MESSAGE = "Unable to find the 'd' separator.";
    public static final String TOO_MANY_PARTS_MESSAGE = "Found too many 'd' separators.";

    public static void start() {
        // Welcome
        probabilityGreet();

        // Get the Die Pool
        while (true) {
            System.out.println("\nA die pool should be represented in the form of {dice}d{sides}");
            System.out.println("Example: '3d6' for 3 six-sided dice.");
            String diePoolString = getString("Enter your die pool (or 'q' to quit)");
            if (diePoolString.equalsIgnoreCase("q") || diePoolString.equalsIgnoreCase("quit")) {
                break;
            }

            // Parse the Die Pool
            DiePool pool;
            try {
                pool = parseDiePool(diePoolString);
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
                continue;
            }

            // Get Target Number
            int targetNumber = 0;
            while (true) {
                targetNumber = getInt("What target number do you want to roll?");
                if (targetNumber >= pool.getDice() && targetNumber <= pool.getDice() * pool.getSides()) {
                    break;
                } else {
                    displayError("That isn't possible to roll. Please try again.");
                }
            }

            // Get the Operation
            String[] operation = {"Calculate", "Evaluate", "Simulate"};
            while (true) {
                printMenu("Operation Type", operation);
                String opChoice = getString("Which operation do you wish to use?");
                if (opChoice.equals("1")) {
                    double result = pool.calculateTargetOdds(targetNumber);
                    UIUtility.printLine(80);
                    System.out.printf("The chance to roll %d on %s is %s%%.\n", targetNumber, diePoolString, Helpers.round(result * 100.0,2));
                    UIUtility.printLine(80);
                    break;
                } else if (opChoice.equals("2")) {
                    Fraction result = pool.evaluateTargetOdds(targetNumber);
                    UIUtility.printLine(80);
                    System.out.printf("A %d will be rolled on %d of %d different combinations.\n", targetNumber, result.getNumerator(), result.getDenominator());
                    System.out.printf("This would mean there is a %s%% chance of rolling a %d.\n",
                            Helpers.round((double)result.getNumerator()/(double)result.getDenominator() * 100.0, 2), targetNumber);
                    UIUtility.printLine(80);
                    break;
                } else if (opChoice.equals("3")) {
                    // Get the number of rolls to simulate
                    int numRolls = 0;
                    while (true) {
                        numRolls = getInt("How many rolls do you want to simulate?");
                        if (numRolls > 0 && numRolls <= DiePool.MAX_COMBINATIONS) {
                            break;
                        } else if (numRolls <= 0) {
                            displayError("The number of rolls must be a positive integer.");
                        } else {
                            displayError("That is too many rolls.  We don't want to wait too long for a result.");
                        }
                    }
                    UIUtility.printLine(80);
                    System.out.printf("Rolling %s %d times:\n", diePoolString, numRolls);
                    Fraction result = pool.simulateTargetOdds(targetNumber, numRolls);
                    System.out.printf("Rolled a %d on %d of %d tries.\n", targetNumber, result.getNumerator(), result.getDenominator());
                    System.out.printf("That equated to %s%% of all rolls.\n",
                            Helpers.round((double)result.getNumerator()/(double)result.getDenominator() * 100.0, 2), targetNumber);
                    UIUtility.printLine(80);
                    break;
                } else {
                    displayError("Choose one of the items on the menu.");
                }

            }
            UIUtility.pressEnterToContinue();
        }

        // Farewell
        probabilityGoodbye();
        pressEnterToContinue();
    }

    /**
     * Parses the definition of a Die Pool from user input.  Die pool is defined by {dice}d{sides}.
     * @param str The text to parse.
     * @return the DiePool object
     * @throws IllegalArgumentException If unable to parse a valid Die Pool
     */
    public static DiePool parseDiePool(String str) throws IllegalArgumentException {
        String lowerStr = str.toLowerCase();
        int dice = 0;
        int sides = 0;
        if (lowerStr.contains("d")) {
            String[] parts = lowerStr.split("d");
            if (parts.length == 2) {
                try {
                    dice = Integer.parseInt(parts[0]);
                } catch(NumberFormatException e) {
                    throw new IllegalArgumentException(BAD_DICE_VALUE_MESSAGE + " (You entered: " + parts[0] + ")");
                }
                if (dice > 99) {
                    throw new IllegalArgumentException(TOO_MANY_DICE_MESSAGE);
                } else if (dice == 0) {
                    throw new IllegalArgumentException(NO_DICE_MESSAGE);
                } else if (dice < 0) {
                    throw new IllegalArgumentException(BAD_DICE_VALUE_MESSAGE + " (You entered: " + parts[0] + ")");
                }
                try {
                    sides = Integer.parseInt(parts[1]);
                } catch(NumberFormatException e) {
                    throw new IllegalArgumentException(BAD_SIDES_VALUE_MESSAGE + " (You entered: " + parts[1] + ")");
                }
                if (sides > 100000000) {
                    throw new IllegalArgumentException(TOO_MANY_SIDES_MESSAGE);
                } else if (sides == 0) {
                    throw new IllegalArgumentException(NO_SIDES_MESSAGE);
                } else if (sides < 0) {
                    throw new IllegalArgumentException(BAD_SIDES_VALUE_MESSAGE + " (You entered: " + parts[1] + ")");
                }
            } else if (parts.length > 2) {
                throw new IllegalArgumentException(TOO_MANY_PARTS_MESSAGE);
            } else {
                throw new IllegalArgumentException(BAD_SIDES_VALUE_MESSAGE);
            }

        } else {
            throw new IllegalArgumentException(NO_DIE_POOL_MESSAGE);
        }
        DiePool result = new DiePool(dice, sides);
        return result;
    }
}

