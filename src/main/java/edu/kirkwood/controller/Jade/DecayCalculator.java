package edu.kirkwood.controller.Jade;

import edu.kirkwood.model.Jade.DecayCalc;

import static edu.kirkwood.view.Messages.*;
import static edu.kirkwood.view.UIUtility.*;
import static edu.kirkwood.view.UserInput.getDouble;
import static edu.kirkwood.view.UserInput.getInt;

/**
 * Manages the exponential decay calculator menu, user input, and displayed results.
 * Uses DecayCalc to perform the requested calculations.
 *
 * AI Assistance: I used ChatGPT to help write and organize Javadoc
 * comments, clean up code formatting, and troubleshoot errors.
 */
public class DecayCalculator {

    /**
     * Displays the calculator menu and processes the user's selection.
     * Collects the required inputs, performs calculations, and displays results
     * until the user chooses to return to the main menu.
     */
    public static void start() {
        decayGreet();

        String[] menuItems = {
                "Calculate Remaining Amount",
                "Calculate Initial Amount",
                "Calculate Decay Constant",
                "Calculate Elapsed Time",
                "About Exponential Decay",
                "Return to Main Menu"
        };

        while (true) {
            printMenu("Exponential Decay Calculator", menuItems);
            int choice = getInt("Choose an option", true, 1, menuItems.length);

            if (choice == 6) {
                break;
            }

            try {
                switch (choice) {
                    case 1:

                        double initialAmount = getDouble(
                                "Enter the initial amount (must be greater than 0)",
                                true,
                                Double.MIN_VALUE,
                                Double.MAX_VALUE
                        );

                        System.out.println(
                                "\nThe decay constant (k) controls how quickly the amount decreases."
                        );
                        System.out.println(
                                "Enter a decay constant greater than 0, using the same time unit as elapsed time."
                        );
                        System.out.println(
                                "Larger numbers mean faster decay."
                        );

                        double decayConstant = getDouble(
                                "Enter the decay constant (k) (must be greater than 0)",
                                true,
                                Double.MIN_VALUE,
                                Double.MAX_VALUE
                        );

                        double elapsedTime = getDouble(
                                "Enter the elapsed time (must be greater than 0)",
                                true,
                                Double.MIN_VALUE,
                                Double.MAX_VALUE
                        );

                        DecayCalc problem = new DecayCalc(
                                initialAmount,
                                1.0,
                                decayConstant,
                                elapsedTime
                        );

                        double result = DecayCalc.calculateRemainingAmount(problem);

                        if (result == 0.0) {
                            throw new ArithmeticException(
                                    "The calculated remaining amount is too small for program to represent accurately. " +
                                            "Try a smaller decay constant or a shorter elapsed time."
                            );
                        }

                        System.out.println("Remaining amount: " + formatResult(result));

                        System.out.printf(
                                "The original amount was approximately %s, which decreased to %s over %s time units.%n",
                                formatResult(initialAmount),
                                formatResult(result),
                                formatResult(elapsedTime)
                        );
                        pressEnterToContinue();
                        break;

                    case 2:

                        double remainingAmount = getDouble(
                                "Enter the remaining amount (must be greater than 0)",
                                true,
                                Double.MIN_VALUE,
                                Double.MAX_VALUE
                        );

                        System.out.println(
                                "\nThe decay constant (k) controls how quickly the amount decreases."
                        );
                        System.out.println(
                                "Enter a decay constant greater than 0, using the same time unit as elapsed time."
                        );
                        System.out.println(
                                "Larger numbers mean faster decay."
                        );

                        double initialDecayConstant = getDouble(
                                "Enter the decay constant (k) (must be greater than 0)",
                                true,
                                Double.MIN_VALUE,
                                Double.MAX_VALUE
                        );

                        double initialElapsedTime = getDouble(
                                "Enter the elapsed time (must be greater than 0)",
                                true,
                                Double.MIN_VALUE,
                                Double.MAX_VALUE
                        );

                        DecayCalc initialProblem = new DecayCalc(
                                1.0,
                                remainingAmount,
                                initialDecayConstant,
                                initialElapsedTime
                        );

                        double initialResult = DecayCalc.calculateInitialAmount(initialProblem);

                        if (!Double.isFinite(initialResult)) {
                            throw new ArithmeticException(
                                    "The calculated initial amount is too large for program to represent accurately. " +
                                            "Try a smaller decay constant or a shorter elapsed time."
                            );
                        }

                        System.out.println("Initial amount: " + formatResult(initialResult));

                        System.out.printf(
                                "The original amount was approximately %s, which decreased to %s over %s time units.%n",
                                formatResult(initialResult),
                                formatResult(remainingAmount),
                                formatResult(initialElapsedTime)
                        );
                        pressEnterToContinue();
                        break;

                    case 3:

                        double decayInitialAmount = getDouble(
                                "Enter the initial amount (must be greater than 0)",
                                true,
                                Double.MIN_VALUE,
                                Double.MAX_VALUE
                        );

                        double decayRemainingAmount;

                        while (true) {
                            decayRemainingAmount = getDouble(
                                    "Enter the remaining amount (must be greater than 0 and less than the initial amount)",
                                    true,
                                    Double.MIN_VALUE,
                                    Double.MAX_VALUE
                            );

                            try {
                                DecayCalc.validateAmounts(decayInitialAmount, decayRemainingAmount);
                                break;
                            } catch (IllegalArgumentException e) {
                                displayError(e.getMessage());
                            }
                        }

                        double decayElapsedTime = getDouble(
                                "Enter the elapsed time (must be greater than 0)",
                                true,
                                Double.MIN_VALUE,
                                Double.MAX_VALUE
                        );

                        DecayCalc decayProblem = new DecayCalc(
                                decayInitialAmount,
                                decayRemainingAmount,
                                0.1,
                                decayElapsedTime
                        );

                        double decayResult = DecayCalc.calculateDecayConstant(decayProblem);

                        System.out.println("Decay constant: " + formatResult(decayResult));

                        System.out.printf(
                                "The amount decreased from %s to %s over %s time units, giving a decay constant of %s per time unit.%n",
                                formatResult(decayInitialAmount),
                                formatResult(decayRemainingAmount),
                                formatResult(decayElapsedTime),
                                formatResult(decayResult)
                        );
                        pressEnterToContinue();
                        break;

                    case 4:

                        double timeInitialAmount = getDouble(
                                "Enter the initial amount (must be greater than 0)",
                                true,
                                Double.MIN_VALUE,
                                Double.MAX_VALUE
                        );


                        double timeRemainingAmount;

                        while (true) {
                            timeRemainingAmount = getDouble(
                                    "Enter the remaining amount (must be greater than 0 and less than the initial amount)",
                                    true,
                                    Double.MIN_VALUE,
                                    Double.MAX_VALUE
                            );

                            try {
                                DecayCalc.validateAmounts(timeInitialAmount, timeRemainingAmount);
                                break;
                            } catch (IllegalArgumentException e) {
                                displayError(e.getMessage());
                            }
                        }

                        System.out.println(
                                "\nThe decay constant (k) controls how quickly the amount decreases."
                        );
                        System.out.println(
                                "Enter a decay constant greater than 0, using the same time unit as elapsed time."
                        );
                        System.out.println(
                                "Larger numbers mean faster decay."
                        );

                        double timeDecayConstant = getDouble(
                                "Enter the decay constant (k) (must be greater than 0)",
                                true,
                                Double.MIN_VALUE,
                                Double.MAX_VALUE
                        );

                        DecayCalc timeProblem = new DecayCalc(
                                timeInitialAmount,
                                timeRemainingAmount,
                                timeDecayConstant,
                                1.0
                        );
                        double timeResult = DecayCalc.calculateElapsedTime(timeProblem);

                        System.out.println("Elapsed time: " + formatResult(timeResult) + " time units");

                        System.out.printf(
                                "It takes approximately %s time units for the amount to decrease from %s to %s.%n",
                                formatResult(timeResult),
                                formatResult(timeInitialAmount),
                                formatResult(timeRemainingAmount)
                        );
                        pressEnterToContinue();
                        break;

                    case 5:
                        decayHelp();
                        pressEnterToContinue();
                        break;
                }
            } catch (Exception e) {
                displayError(e.getMessage());
                pressEnterToContinue();
            }
        }
        decayGoodbye();
        pressEnterToContinue();
    }

    /**
     * Formats all calculation results consistently.
     * Values smaller than 0.0001 in magnitude (except zero) use
     * scientific notation. All other values use up to five decimal
     * places and commas for thousands separators.
     *
     * AI Assistance: I used ChatGPT to help implement scientific notation
     * so very small numbers can be displayed accurately without rounding to zero.
     *
     * @param value the numerical value to format
     * @return the formatted result
     */
    public static String formatResult(double value) {
        java.text.DecimalFormatSymbols symbols =
                java.text.DecimalFormatSymbols.getInstance(java.util.Locale.US);

        if (value != 0.0 && Math.abs(value) < 0.0001) {
            java.text.DecimalFormat scientific =
                    new java.text.DecimalFormat("0.####E0", symbols);
            return scientific.format(value);
        }

        java.text.DecimalFormat decimal =
                new java.text.DecimalFormat("#,##0.#####", symbols);
        return decimal.format(value);
    }
}

