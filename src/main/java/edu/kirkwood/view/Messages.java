package edu.kirkwood.view;

import static edu.kirkwood.view.UIUtility.displayMessage;

public class Messages {

    /**
     * Displays the application's welcome message.
     */
    public static void hello() {
        displayMessage("Welcome to the Kirkwood Calculators Application");
    }

    /**
     * Displays the application's farewell message.
     */
    public static void goodbye() {
        displayMessage("Thank you for using the Kirkwood Calculator. Goodbye!");
    }

    /**
     * Displays instructions for using the fraction calculator.
     */
    public static void fractionGreet() {
        displayMessage("Welcome to Marc's Fraction Calculator");
        System.out.println("Enter calculations in the format: [fraction] [operator] [fraction]");
        System.out.println("Example: 1 1/2 + 3/4\n");
    }

    /**
     * Displays the fraction calculator's farewell message.
     */
    public static void fractionGoodbye() {
        displayMessage("Thank you for using Marc's Fraction Calculator");
    }
    public static void PolyDiffHello()
    {
        displayMessage("Greetings, and welcome to Michael's Polynomial Differentiation Calculator.");
        System.out.println("Please enter a single-variable polynomial. The format for each term is: \n"
                + "[coefficient, as an integer value][variable, as a letter]^[exponent, as an integer value]\n"
                + "Between each term, place either a \'+\' or \'-\' separated by a space on each side.");
        System.out.println("Example: \'2x^3 + 3x^2 - 5x + 3\'\n");
    }
    public static void PolyDiffFarewell()
    {
        displayMessage("Thank you for using Michael's Polynomial Differentiation Calculator.\n"
                + "We hope it was useful!");
    }

    /**
     * Displays instructions for using Bailey's Astrophotography OED calculator.
     */
    public static void astroGreet() {
        displayMessage("Welcome to Bailey's Astrophotography OEV calculator!");
        System.out.println("Astrophotography is the practice of photographing objects deep in space, such as galaxies, nebulae, and star clusters.\n" +
                "This calculator accounts for your exposure settings, information about your camera and telescope, and\n" +
                "local light pollution, to produce an Overall-Exposure-Value.\n" +
                "'Overall-Exposure-Value' is a general, numeric indicator of how much light entered\n" +
                "the camera sensor throughout the entire imaging process.\n" +
                "Generally, a longer exposure time means a less-noisy, and more detailed image.\n" +
                "It also gives the standard exposure time, and a general 'quality rating' indicating how finished your final image is.\n" +
                "'Good' is a solid goal for most personal-images, and 'Excellent' is what you should aim for if you plan to print the image or sell it." +
                "\nWhat do each of these settings mean?\n" +
                "> Exposure Time: The amount of seconds that your camera shutter was open while taking each image.\n" +
                "       The final exposure time is calculated by multiplying the single-frame exposure time by the total amount of frames you captured.\n" +
                "> Light Frames: A light frame is a single image of the sky, usually around 15-60 seconds, or more. after a long night of imaging,\n" +
                "       hundreds of these are stacked together using special software, to create a final, blended image of extremely faint deep-sky-objects.\n" +
                "       Stacking the images cancelles out the random 'grain' or 'noise' in the images, but maintains the real pixel-data that's perfectly consistant across each image.\n" +
                "> F-stop: Also known as 'Aperture' - this is the value given to how wide the aperture is in your lens or telescope.\n" +
                "       The value is normally written as a fraction (ex: f/5.6), but this calculator just takes the denominator, in decimal form (ex: 5.6).\n" +
                "> Sensor Size: This is the size of your camera's sensor. A larger sensor has more surface area for light to hit it, and generally produces cleaner images.\n" +
                "       Options include 'micro' for Micro four-thirds, 'apsc' for APS-C, 'full' for Full-Frame, and 'medium' for Medium Format.\n" +
                "> Bortle Scale: The bortle scale is a scale that measures local light pollution in the sky. It's a number between 1 and 9, where\n" +
                "       9 is 'Middle of the city', and 1 is 'middle of the dessert at 2am'."
        );
        System.out.println("\nEnter each of the required values in the prompts below, in the specified format.");
        System.out.println("Enter '-1' at any time to Exit to Main Menu.\n");
    }

    /**
     * Displays instructions for using Bailey's Astrophotography OED calculator.
     */
    public static void astroGoodbye() {
        displayMessage("Thanks for using Bailey's Astro-Photo OEV Calculator!");
    }
}
