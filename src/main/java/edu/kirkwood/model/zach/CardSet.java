package edu.kirkwood.model.zach;

import edu.kirkwood.model.zach.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a set of Pokémon cards, containing a list of Card objects.
 * This class provides methods to manage and retrieve cards within the set,
 * simulating the pack opening portion of the program.
 */
public class CardSet {

    private ArrayList<Card> cards;
    private final Random random;

    /**
     * Constructs a new CardSet and initializes the list of cards.
     */
    public CardSet() {
        cards = new ArrayList<>();
        random = new Random();
    }

    /**
     * Adds a Card object to the CardSet.
     * @param card the Card object to be added to the set
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Searches for Pokémon cards in the CardSet that match the specified name.
     * @param searchName the name of the Pokémon to search for
     * @return a list of Card objects that match the specified name
     */
    public List<Card> searchPokemon(String searchName) {
        ArrayList<Card> matchingCards = new ArrayList<>();

        // Remove everything besides letters numbers and spaces
        String cleanName = searchName.toLowerCase().replaceAll("[^a-z0-9 ]", "");
        String [] searchTerms = cleanName.split("\\s+ ");
         for (Card card : cards) {
             String cleanCardName = card.getName().toLowerCase().replaceAll("[^a-z0-9 ]", "");

             boolean matchesAllTerms = true;
                for (String term : searchTerms) {
                    if (!cleanCardName.contains(term)) {
                        matchesAllTerms = false;
                        break;
                    }
                }
                if (matchesAllTerms) {
                    matchingCards.add(card);
                }
         }
        return matchingCards;
    }

    /**
     * Simulates opening a booster pack of Pokémon cards.
     * The draw should be accurate to the percentage chance assigned to each card
     * @param packSize the number of cards to draw from the set
     * @return a list of Card objects drawn from the pack
     */
    public List<Card> openPack(int packSize) {
        // Create a list to hold the drawn cards from the pack
        ArrayList<Card> drawnFromPack = new ArrayList<>();
        //  Create a loop that runs 'packSize' times
        for (int i = 0; i <packSize; i++) {
            // Generate a random weight between 1 and 10000
            int roll = random.nextInt(10000) + 1;
            String targetRarity;

            // Map the rolled integer to a targeted rarity string
            if(roll <= 6000) {
                targetRarity = "Common";
            } else if (roll <= 8500) {
                targetRarity = "Uncommon";
            } else if (roll <= 9500) {
                targetRarity = "Rare";
            } else if (roll <= 9800) {
                targetRarity = "Double Rare";
            } else if (roll <= 9950) {
                targetRarity = "Ultra Rare";
            } else if (roll <= 9990) {
                targetRarity = "Special Illustration Rare";
            } else {
                targetRarity = "Hyper Rare";
            }

            // Find all cards loaded in this set that match the targeted rarity
            ArrayList<Card> possibleCards = new ArrayList<>();
            for (Card card : cards) {
                if (card.getRarity().equalsIgnoreCase(targetRarity)) {
                    possibleCards.add(card);
                }
            }
            // If there are possible cards, select one at random
            if (possibleCards.isEmpty() && !cards.isEmpty()) {
                Card fallbackCard = cards.get(random.nextInt(cards.size()));
                drawnFromPack.add(fallbackCard);
            } else if (!possibleCards.isEmpty()) {
                // Select a random card of the targetRarity from the possibleCards list
                Card drawnCard = possibleCards.get(random.nextInt(possibleCards.size()));
                drawnFromPack.add(drawnCard);
            }
        }
        // Return the drawn from pack ArrayList<Card>
        return drawnFromPack;
    }

    /**
     * Returns the list of Card objects contained in the CardSet.
     * @return the list of Card objects in the set
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Returns the size of the CardSet, i.e., the number of cards it contains.
     * @return the number of cards in the CardSet
     */
    public int getSetSize() {
        // Return the size of the 'cards' ArrayList
        return cards.size();
    }

    /**
     * Calculates the base chance percentage of drawing a specific card from the CardSet.
     * The calculation is based on the rarity of the card and the number of cards in that rarity tier.
     * @param card the Card object for which to calculate the base chance percentage
     * @return the base chance percentage of drawing the specified card
     */
    public double baseChancePercentage(Card card) {
        String rarity = card.getRarity();
        double tierChance = 0.0;

        // Determine the chance of drawing a card based on the rarity tier
        if(rarity.equalsIgnoreCase("Common")) {
            tierChance = 60.0;
        } else if (rarity.equalsIgnoreCase("Uncommon")) {
            tierChance = 25.0;
        } else if (rarity.equalsIgnoreCase("Rare")) {
            tierChance = 10.0;
        } else if (rarity.equalsIgnoreCase("Double Rare")) {
            tierChance = 3.0;
        } else if (rarity.equalsIgnoreCase("Ultra Rare")) {
            tierChance = 1.5;
        } else if (rarity.equalsIgnoreCase("Special Illustration Rare")) {
            tierChance = 0.4;
        } else if (rarity.equalsIgnoreCase("Hyper Rare")) {
            tierChance = 0.1;
        } else {
            return 0.0;
        }

        // Count the number of cards in the set that have the same rarity as the specified card
        int cardsInTier = 0;
        for (Card c : cards) {
            if (c.getRarity().equalsIgnoreCase(rarity)) {
                cardsInTier++;
            }
        }

        // Divide the tier chance by the number of cards in the pool
        if (cardsInTier == 0) {
            return 0.0;
        } else {
            return tierChance / cardsInTier;
        }
    }

    /**
     * Calculates the cumulative odds of drawing a specific card at least once when opening multiple packs.
     * @param baseChancePercentage the base chance percentage of drawing the card from a single pack
     * @param packsToOpen the number of packs to be opened
     * @return the cumulative odds percentage of drawing the specified card at least once
     */
    public double calculateCumulativeOdds(double baseChancePercentage, int packsToOpen) {
        // Convert the base chance percentage to a decimal
        double baseChanceDecimal = baseChancePercentage / 100.0;

        // Calculate the probability of not drawing the card in a single pack
        double chanceOfFailure = 1.0 - baseChanceDecimal;

        // Calculate the probability of not drawing the card in all packs
        double chanceOfTotalFailure = Math.pow(chanceOfFailure, packsToOpen);

        // Calculate the cumulative odds of drawing the card at least once
        double cumulativeOdds = 1.0 - chanceOfTotalFailure;

        // Convert back to percentage
        return cumulativeOdds * 100.0;
    }
}
