package edu.kirkwood.controller.bailey;

import edu.kirkwood.model.bailey.AstroOEV;

import static edu.kirkwood.view.Messages.astroGoodbye;
import static edu.kirkwood.view.Messages.astroGreet;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.*;

public class AstroOEVCalculator {

    public static void start() {

        astroGreet();
        boolean running = true;

        // get five inputs
        double subExposureTime = 0;
        int frameCount = 0;
        double fStop = 0;
        String sensorSize = "";
        int bortle = 0;

        while (running){

            // subExposureTime
            while (true) {
                double value = getDouble("Enter the exposure time for each light frame, in seconds [Any positive number].",
                        true, -1,10000000);

                if (value == -1){
                    running = false;
                    break;
                }
                if (value <= 0){
                    displayError("Exposure time must be greater than 0.");
                    continue;
                }
                subExposureTime = value;
                break;
            }

            if(!running){break;}

            // frameCount
            while (true) {
                int value = getInt("Enter the amount of light frames used in the stack [Atleast 2].",
                        true, -1, 1000000);

                if (value == -1) {
                    running = false;
                    break;
                }
                if (value < 2) {
                    displayError("There must be at least 2 frames.");
                    continue;
                }
                frameCount = value;
                break;
            }

            if(!running){break;}

            // fStop
            while (true) {
                double value = getDouble("Enter the F-stop of the light frames [Decimal greater than 0].",
                        true, -1, 1000);

                if (value == -1){
                    running = false;
                    break;
                }
                if (value <= 0){
                    displayError("F-stop must be greater than 0.");
                    continue;
                }
                fStop = value;
                break;
            }

            if(!running){break;}

            // sensorSize
            while (true) {
                String value = getString("Enter the cameras sensor-size as one of the following:\n" +
                        "'micro', 'apsc', 'full', or 'medium'.");

                if (value.equals("-1")){
                    running = false;
                    break;
                }
                if (!value.equalsIgnoreCase("micro") &&
                    !value.equalsIgnoreCase("apsc") &&
                    !value.equalsIgnoreCase("aps-c") &&
                    !value.equalsIgnoreCase("full") &&
                    !value.equalsIgnoreCase("medium")){

                    displayError("Unsupported sensor size. Valid sensors include: 'micro', 'apsc', 'full', and 'medium'.");
                    continue;
                }
                sensorSize = value;
                break;
            }

            if(!running){break;}

            // bortle
            while (true) {
                int value = getInt("Enter the Bortle scale of the imaging location [Between 1 and 9].",
                        true, -1, 9);
                if (value == -1) {
                    running = false;
                    break;
                }
                if (value < -1 || 9 < value){
                    displayError("Invalid Bortle value. Enter an integer from 1 to 9.");
                    continue;
                }
                bortle = value;
                break;
            }

            if(!running){break;}

            // create AstroOEV & do calculations
            AstroOEV astroOEV = new AstroOEV(subExposureTime, fStop, frameCount, sensorSize, bortle);

            // display calculations
            System.out.print("\n__________________________________________________\n");
            System.out.print(
                    subExposureTime + " second sub exposures\n" +
                    frameCount + " frames\n" +
                    "f/" + fStop + " aperture\n" +
                    AstroOEV.sensorDisplayName(sensorSize) + " sensor\n" +
                    "Bortle " + bortle);
            System.out.print("\n-  -  -  -  -  -  -  -  -  -  -  -  -\n");
            System.out.print("Integration Time: " + astroOEV.calcIntegrationTime() + " seconds\n");
            System.out.print("Overall Exposure Value: " + Math.round(astroOEV.calcOEV()) + "\n");
            System.out.print("Image Quality Rating: " + astroOEV.calcImageQuality() + "\n");
            System.out.print("__________________________________________________\n\n");

            String again = getString("Press 'n' to perform a new AstroOEV calculation, or press 'q' to quit to Main Menu...");
                if (again.equalsIgnoreCase("q") || again.equalsIgnoreCase("quit")) {
                    running = false;
                    break;
                }
        }
        // goodbye
        astroGoodbye();
        pressEnterToContinue();
    }

    public static void showAstroMenu(){

    }


    public static double parseDouble(String value){
        return Double.parseDouble(value);
    }


    public static int parseInt(String value) {
        return Integer.parseInt(value);
    }
}