package edu.kirkwood.controller.xavian;

import edu.kirkwood.model.xavian.HalfLifeSample;
import edu.kirkwood.view.Messages;
import edu.kirkwood.view.xavian.SampleCountMenu;
import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;


public class HalfLifeCalculator {
    /**
     * Starts the HalfLifeCalculator. In logic to help differentiate from other potential calculators of the same name
     */
    public static void start() {
        Messages.halfLifeHello();
        SampleCountMenu.show();
        Messages.halfLifeGoodbye();
    }

    /**
     * Calculates a sample based on the remaining value, time elapsed, and half life length provided by user input.
     * @return the sample calculated
     */
    public static HalfLifeSample calculateInitialValue() {
        // Ask the user for remaining value
        double remaining = UserInput.getDouble("How much of the radiological sample is remaining?", true, Double.MIN_VALUE);
        // Ask the user for time elapsed
        double timeElapsed = UserInput.getDouble("How much time has elapsed since sample formation?", true, Double.MIN_VALUE);
        // Ask the user for half life length
        double halfLifeLength = UserInput.getDouble("How long is one half life?", true, Double.MIN_VALUE);
        // calculate
        HalfLifeSample result = HalfLifeSample.sampleFindInitial(remaining, timeElapsed, halfLifeLength);
        // display the results
        System.out.println();
        System.out.println(result);
        // return the resulting sample
        return result;
    }

    /**
     * Calculates a sample based on the initial value, time elapsed, and half life length provided by user input.
     * @return the sample calculated
     */
    public static HalfLifeSample calculateRemainingValue() {
        // Ask the user for initial value (must be greater than zero)
        double initialValue = UserInput.getDouble("What was the initial quantity of radiological sample?", true, Double.MIN_VALUE);
        // Ask the user for time elapsed (must be greater or equal to zero)
        double timeElapsed = UserInput.getDouble("How much time has elapsed since sample formation?", true, Double.MIN_VALUE);
        // Ask the user for the length of a half life (must be greater than zero)
        double halfLifeLength = UserInput.getDouble("How long is the length of one half life?", true, Double.MIN_VALUE);
        // calculate
        HalfLifeSample result = HalfLifeSample.sampleFindRemaining(initialValue, timeElapsed, halfLifeLength);
        // display the results
        System.out.println();
        System.out.println(result);
        // return the resulting sample
        return result;
    }

    /**
     * Calculates a sample based on the initial value, remaining value, and half life length provided by user input.
     * @return the sample calculated
     */
    public static HalfLifeSample calculateTimeElapsed() {
        // Ask the user for initial value (must be greater than zero)
        double initialValue = UserInput.getDouble("What was the initial quantity of the radiological sample?", true, Double.MIN_VALUE);
        // Ask the user for remaining value (must be greater than zero AND less than or equal to initial value)
        double remainingValue = UserInput.getDouble("What was the remaining quantity of the radiological sample?", true, Double.MIN_VALUE, initialValue - 0.0001);
        // Ask the user for the length of a half life (must be greater than zero)
        double halfLifeLength = UserInput.getDouble("What is the length of one half life?", true, Double.MIN_VALUE);
        // calculate
        HalfLifeSample result = HalfLifeSample.sampleFindTimeElapsed(initialValue, remainingValue, halfLifeLength);
        // display the results
        System.out.println();
        System.out.println(result);
        // return the resulting sample
        return result;
    }

    /**
     * Calculates a sample based on the initial value, remaining value, and time elapsed provided by user input.
     * @return the sample calculated
     */
    public static HalfLifeSample calculateHalfLifeLength() {
        while (true) {
            /*
            Another option: get a string then try to read quit OR parse to double, will involve try/catch
            But that would mean I would need to do my own validation, and I'd rather not if I can avoid it.
            */
            // Ask the user for initial value (must be greater than zero)
            double initialValue = UserInput.getDouble("What was the initial quantity of the radiological sample?", true, Double.MIN_VALUE);
            // Ask the user for remaining value (must be greater than zero AND be less than initial value)
            double remainingValue = UserInput.getDouble("What was the remaining quantity of the radiological sample?", true, Double.MIN_VALUE, initialValue - 0.0001);
            // Ask the user for the time elapsed (must be greater than zero)
            double timeElapsed = UserInput.getDouble("How much time has elapsed between sample formation and measurement?", true, Double.MIN_VALUE);
            // calculate
            try {
                HalfLifeSample result = HalfLifeSample.sampleFindHalfLifeLength(initialValue, remainingValue, timeElapsed);
                // display the results
                System.out.println();
                System.out.println(result);
                // return the resulting sample
                return result;
            } catch (ArithmeticException e){
                UIUtility.displayError(e.getMessage());
            }
        }
    }
}
