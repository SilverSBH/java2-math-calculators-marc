package edu.kirkwood.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static edu.kirkwood.view.Helpers.isValidString;
import static edu.kirkwood.view.UIUtility.displayError;

public class UserInput {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Get an integer from the user
     * @param prompt The question to ask the user
     * @param required Does the user have to answer it
     * @param min The smallest value possible
     * @param max The largest value possible.
     * @return the integer from the user
     */
    public static int getInt(String prompt, boolean required, int min, int max) {
        int value = 0;
        String minMax = "";
        // Min set, Max not set
        if(min != Integer.MIN_VALUE && max == Integer.MAX_VALUE) {
            minMax = String.format(" [minimum %d]", min);
        }
        // Min and max
        if(min != Integer.MIN_VALUE && max != Integer.MAX_VALUE) {
            minMax = String.format(" [between %d and %d]", min, max);
        }

        while(true) {
            System.out.print(prompt + minMax + (required ? " (*)" : "") + ": ");
            String valueStr = scanner.nextLine();
            try {
                value = Integer.parseInt(valueStr);
            } catch(NumberFormatException e) {
                if(!required) {
                    return Integer.MIN_VALUE;
                } else {
                    displayError("Invalid integer");
                    continue;
                }
            }
            if(value < min) {
                displayError("Value too low");
            } else if (value > max) {
                displayError("Value too high");
            } else {
                break;
            }
        }
        return value;
    }

    /**
     * Get an integer from the user
     * @param prompt The question to ask the user
     * @param required Does the user have to answer it
     * @param min The smallest value possible
     * @return the integer from the user
     */
    public static int getInt(String prompt, boolean required, int min) {
        return getInt(prompt, required, min, Integer.MAX_VALUE);
    }

    /**
     * Get an integer from the user
     * @param prompt The question to ask the user
     * @param required Does the user have to answer it
     * @return the integer from the user
     */
    public static int getInt(String prompt, boolean required) {
        return getInt(prompt, required, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Get an integer from the user
     * @param prompt The question to ask the user
     * @return the integer from the user
     */
    public static int getInt(String prompt) {
        return getInt(prompt, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Prompts the user for a required nonblank string.
     *
     * @param prompt text displayed before accepting input
     * @return the trimmed string entered by the user
     */
    public static String getString(String prompt) {
        return getString(prompt, true);
    }

    /**
     * Prompts the user for a string.
     *
     * @param prompt text displayed before accepting input
     * @param required whether a nonblank string must be supplied
     * @return the trimmed string entered by the user
     */
    public static String getString(String prompt, boolean required) {
        String value = "";
        while(true) {
            System.out.print(prompt + (required ? " (*)" : "") + ": ");
            value = scanner.nextLine().trim();
            if(required && !isValidString(value)) {
                displayError("Input required");
            } else {
                break;
            }
        }
        return value;
    }

    /**
     * Prompts the user for a required yes-or-no response.
     *
     * @param prompt text displayed before accepting input
     * @return {@code true} for {@code y} or {@code yes}; otherwise {@code false}
     */
    public static boolean getBoolean(String prompt) {
        return getBoolean(prompt, true);
    }

    /**
     * Prompts the user for a yes-or-no response.
     *
     * @param prompt text displayed before accepting input
     * @param required whether a valid response must be supplied
     * @return {@code true} for {@code y} or {@code yes}; otherwise {@code false}
     */
    public static boolean getBoolean(String prompt, boolean required) {
        boolean value = true;
        while(true) {
            String valueStr = getString(prompt + " [y/n]", required);
            if(required && !(valueStr.equalsIgnoreCase("y") ||
                    valueStr.equalsIgnoreCase("n") ||
                    valueStr.equalsIgnoreCase("yes") ||
                    valueStr.equalsIgnoreCase("no"))
            ) {
                displayError("Invalid input");
            } else {
                value = valueStr.equalsIgnoreCase("y") || valueStr.equalsIgnoreCase("yes");
                break;
            }
        }
        return value;
    }

    /**
     * Prompts the user for a required double without range restrictions.
     *
     * @param prompt text displayed before accepting input
     * @return the entered double
     */
    public static double getDouble(String prompt) {
        return getDouble(prompt, true, -Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /**
     * Prompts the user for a double without range restrictions.
     *
     * @param prompt text displayed before accepting input
     * @param required whether a number must be supplied
     * @return the entered double, or {@code -Double.MAX_VALUE} when optional input is invalid
     */
    public static double getDouble(String prompt, boolean required) {
        return getDouble(prompt, required, -Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /**
     * Prompts the user for a double that meets a minimum value.
     *
     * @param prompt text displayed before accepting input
     * @param required whether a number must be supplied
     * @param min smallest accepted value
     * @return the entered double, or {@code -Double.MAX_VALUE} when optional input is invalid
     */
    public static double getDouble(String prompt, boolean required, int min) {
        return getDouble(prompt, required, min, Double.MAX_VALUE);
    }

    /**
     * Prompts the user for a double within an inclusive range.
     *
     * @param prompt text displayed before accepting input
     * @param required whether a number must be supplied
     * @param min smallest accepted value
     * @param max largest accepted value
     * @return the entered double, or {@code -Double.MAX_VALUE} when optional input is invalid
     */
    public static double getDouble(String prompt, boolean required, double min, double max) {
        double value = 0;

        String minMax = "";
        // if min is set and max is not set
        if(min != -Double.MAX_VALUE && max == Double.MAX_VALUE) {
            minMax = String.format(" [minimum %.1f]", min);
        }
        // if min and max are both set
        if(min != -Double.MAX_VALUE && max != Double.MAX_VALUE) {
            minMax = String.format(" [between %.1f and %.1f]", min, max);
        }

        while(true) {
            System.out.print(prompt + minMax + (required ? " (*)" : "") + ": ");
            String valueStr = scanner.nextLine();
            try {
                value = Double.parseDouble(valueStr);
            } catch (NumberFormatException e) {
                if(!required) {
                    return -Double.MAX_VALUE;
                } else {
                    displayError("Invalid number");
                    continue;
                }
            }

            if(value < min) {
                displayError("Value too low");
            } else if(value > max) {
                displayError("Value too high");
            } else {
                break;
            }
        }
        return value;
    }

    /**
     * Prompts the user for a required date in {@code M/d/yyyy} format.
     *
     * @param prompt text displayed before accepting input
     * @return the parsed date
     */
    public static LocalDate getDate(String prompt) {
        return getDate(prompt, true);
    }

    /**
     * Prompts the user for a date in {@code M/d/yyyy} format.
     *
     * @param prompt text displayed before accepting input
     * @param required whether a valid date must be supplied
     * @return the parsed date, or {@link LocalDate#MIN} when optional input is invalid
     */
    public static LocalDate getDate(String prompt, boolean required) {
        LocalDate date = null;
        while(true) {
            String dateStr = getString(prompt + " [MM/DD/YYYY]", required);
            try {
                DateTimeFormatter dateFormatInput = DateTimeFormatter.ofPattern("M/d/yyyy");
                date = LocalDate.parse(dateStr, dateFormatInput);
                break;
            } catch(DateTimeParseException e) {
                if(!required) {
                    return LocalDate.MIN;
                } else {
                    displayError("Invalid date");
                }
            }
        }
        return date;
    }

}
