package edu.kirkwood.controller.zach;

import edu.kirkwood.model.zach.CSVReader;
import edu.kirkwood.model.zach.Card;
import edu.kirkwood.model.zach.CardSet;
import edu.kirkwood.view.Messages;
import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;

import java.util.List;


/**
 * Controller class for the booster pack calculator.
 * Handles menu navigation, user input, and the logic for calculating booster pack contents.
 */
public class BoosterPackCalculator {

    /**
     * Starts the booster pack calculator, displaying the menu and handling user input.
     */
    public static void start() {
        Messages.packGreet();

        String[] packOptions = {
                "Destined Rivals", // DRI
                "Black Bolt", // BLK
                "Prismatic Evolutions", // PRE
                "Journey Together", // JTG
                "Return to Main Menu"
        };

        boolean isRunning = true;

        while (isRunning) {
            UIUtility.printMenu("Booster Packs", packOptions);
            int choice = UserInput.getInt("Select a pack", true, 1, packOptions.length);

            if(choice == 5) {
                Messages.packGoodbye();
                isRunning = false;
                continue;
            }

            try {
                String filePath = getFilePath(choice);
                CardSet currentSet = CSVReader.loadSet(filePath);

                if (currentSet != null && currentSet.getSetSize() > 0) {
                    runCalculatorLoop(currentSet);
                }else {
                    UIUtility.displayError("Failed to load set data.");
                }
            } catch (IllegalArgumentException e) {
                UIUtility.displayError(e.getMessage());
            }
        }
    }

    /**
     * Returns the file path for the selected booster pack based on the user's choice.
     * @param choice the user's choice of booster pack
     * @return the file path corresponding to the selected booster pack
     * @throws IllegalArgumentException if the choice is invalid
     */
    public static String getFilePath(int choice) throws IllegalArgumentException {
        return switch (choice) {
            case 1 -> "data/DRI-Pokemon-Destined-Rivals.csv";
            case 2 -> "data/BLK-Pokemon-Black-Bolt.csv";
            case 3 -> "data/PRE-Pokemon-Prismatic-Evolutions.csv";
            case 4 -> "data/JTG-Pokemon-Journey-Together.csv";
            default -> throw new IllegalArgumentException("Invalid choice for booster pack: " + choice);
        };
    }

    /**
     * Runs the main loop of the booster pack calculator, allowing the user to search for a Pokémon,
     * calculate odds, and simulate opening packs.
     * @param currentSet the current CardSet being used for calculations
     */
    private static void runCalculatorLoop(CardSet currentSet) {
        System.out.println("\n--- Featured Chase Cards (Top Set Rarities) ---");

        List<Card> allCards = currentSet.getCards();
        if (allCards != null && !allCards.isEmpty()) {
            int startIndex = Math.max(0, allCards.size() - 5);
            List<Card> chaseCards = allCards.subList(startIndex, allCards.size());

            for (Card card : chaseCards) {
                double odds = currentSet.baseChancePercentage(card);
                System.out.printf("%s, Base Chance: %.4f%%\n", card.toString(), odds);
            }
        } else {
            System.out.println("No cards found in this set.");
        }

        UIUtility.printLine();
        UIUtility.pressEnterToContinue();
        System.out.println();

        String favoritePokemon = UserInput.getString("Which Pokemon are you hoping to pull? (or type 'exit' to return to the main menu): ", true);
        if (favoritePokemon.equalsIgnoreCase("exit")) {
            return;
        }

        List<Card> foundCards = currentSet.searchPokemon(favoritePokemon);

        if(foundCards.isEmpty()) {
            UIUtility.displayError(favoritePokemon + " is not available in this set.");
            return;
        }
        UIUtility.displaySuccess("Found " + foundCards.size() + " " + favoritePokemon + " matching card(s)!");

        // Find the rarest card among the found cards ie the chase card
        Card targetCard = foundCards.get(0);
        double lowestOdds = currentSet.baseChancePercentage(targetCard);

        for (Card card : foundCards) {
            double currentOdds = currentSet.baseChancePercentage(card);
            if (currentOdds < lowestOdds) {
                targetCard = card;
                lowestOdds = currentOdds;
            }
        }

        System.out.printf("Chase card selected: %s, Base Chance: %.4f%%\n", targetCard.toString(), lowestOdds);

        int packsToOpen = UserInput.getInt("How many packs do you want to open?", true, 1, Integer.MAX_VALUE);
        double cumulativeOdds = currentSet.calculateCumulativeOdds(lowestOdds, packsToOpen);
        System.out.printf("Your cumulative chance to pull this card after opening %d packs: %.2f%%\n\n", packsToOpen, cumulativeOdds);

        int totalChasePulls = 0;

        for (int i = 1; i <= packsToOpen; i++) {
            System.out.println("\n--- Tearing open the wrapper... ---");
            List<Card> drawnPack = currentSet.openPack(10);
            boolean gotFavoriteInThisPack = false;

            for (Card card : drawnPack) {
                double pulledCardOdds = currentSet.baseChancePercentage(card);
                System.out.println("You got: " + card.toString() + " (Odds: " + String.format("%.4f", pulledCardOdds) + "%)");

                if (card.equals(targetCard) || card.getName().equalsIgnoreCase(favoritePokemon)) {
                    gotFavoriteInThisPack = true;
                    totalChasePulls++;
                }
            }

            UIUtility.printLine();
            if (gotFavoriteInThisPack) {
                UIUtility.displaySuccess("CONGRATULATIONS! You pulled your chase card: " + targetCard.getName() + "!");
            } else {
                UIUtility.displayMessage("Sorry, you didn't pull your chase card: " + targetCard.getName() + ". Better luck next pack!");
            }
        }

        UIUtility.printLine();
        if (totalChasePulls > 0) {
            UIUtility.displaySuccess("Simulation Complete! You pulled your chase card " + totalChasePulls + " time(s)!");
        } else {
            UIUtility.displayMessage("Simulation Complete. At least this pack opening was free!");
        }

        System.out.println();
        UIUtility.pressEnterToContinue();
    }
}
