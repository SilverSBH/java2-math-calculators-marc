package edu.kirkwood.model.zach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the CardSet class.
 * This class tests the functionality of adding cards, searching for Pokémon,
 * opening packs, and calculating odds within a CardSet.
 */
public class CardSetTest {
    private CardSet cardSet;
    private Card testLapras;
    private Card testHoOh;

    /**
     * Sets up the test environment before each test case.
     * Initializes a new CardSet and adds some test cards to it.
     */
    @BeforeEach
    void setUp() {
        cardSet = new CardSet();
        testLapras = new Card("Misty's Lapras", "50/182", "Common", "Destined Rivals");
        testHoOh = new Card("Ethan's Ho-Oh ex", "230/182", "Special Illustration Rare", "Destined Rivals");

        cardSet.addCard(testLapras);
        cardSet.addCard(testHoOh);
        // Add a second Misty's Lapras card to test searching for multiple results
        cardSet.addCard(new Card("Misty's Lapras", "194/182", "Illustration Rare", "Destined Rivals"));
        // Add a promo card to test unknown rarities
        cardSet.addCard(new Card("Blaziken", "192/182", "Illustration Rare", "Destined Rivals"));
    }
    /**
     * Tests the addCard method of the CardSet class.
     * Ensures that cards are added correctly and the set size is updated.
     */
    @Test
    void addCard() {
        assertEquals(4, cardSet.getSetSize(), "CardSet should contain 3 cards after setup.");
        cardSet.addCard(new Card("Shaymin", "185/182", "Illustration Rare", "Destined Rivals"));
        assertEquals(5, cardSet.getSetSize(), "CardSet should contain 4 cards after adding a new card.");
    }


    /**
     * Tests the searchPokemon method of the CardSet class.
     * Ensures that searching for a Pokémon by name returns the correct cards.
     */
    @Test
    void searchPokemon() {
        List<Card> laprasCards = cardSet.searchPokemon("Misty's Lapras");
        assertEquals(2, laprasCards.size(), "There should be 2 Misty's Lapras cards in the CardSet.");

        List<Card> blazikenCards = cardSet.searchPokemon("Blaziken");
        assertEquals(1, blazikenCards.size(), "There should be 1 Blaziken card in the CardSet.");
        assertEquals("Blaziken", blazikenCards.get(0).getName(), "The returned card should have the name 'Blaziken'.");
    }


    /**
     * Tests the openPack method of the CardSet class.
     * Ensures that the correct number of cards are drawn from the pack.
     */
    @Test
    void openPack() {
        int expectedPackSize = 10;
        List<Card> pulledPack = cardSet.openPack(expectedPackSize);

        assertEquals(expectedPackSize, pulledPack.size(), "The opened pack should contain the expected number of cards.");
    }

    /**
     * Tests the getSetSize method of the CardSet class.
     * Ensures that the size of the card set is returned correctly.
     */
    @Test
    void getSetSize() {
        assertEquals(4, cardSet.getSetSize(), "CardSet should contain 4 cards after setup.");
    }
    /**
     * Tests the baseChancePercentage method of the CardSet class.
     * Ensures that the base chance percentage for different rarities is calculated correctly.
     */
    @Test
    void baseChancePercentage() {
        assertEquals(60.0, cardSet.baseChancePercentage(testLapras), 0.01, "The base chance percentage for a Common card should be 60.0.");
        assertEquals(0.4, cardSet.baseChancePercentage(testHoOh), 0.01, "The base chance percentage for a Special Illustration Rare card should be 0.4.");
    }

    /**
     * Tests the calculateCumulativeOdds method of the CardSet class.
     * Ensures that the cumulative odds are calculated correctly for different scenarios.
     */
    @Test
    void calculateCumulativeOddsWithOnePack() {
        // A 10% base chance over 1 pack should mathematically remain exactly 10%.
        double onePackOdds = cardSet.calculateCumulativeOdds(10.0, 1);
        assertEquals(10.0, onePackOdds, 0.001,
                "Odds for 1 pack should equal the exact base chance.");
    }


    /**
     * Tests the calculateCumulativeOdds method of the CardSet class with multiple packs.
     * Ensures that the cumulative odds are calculated correctly for multiple packs.
     */
    @Test
    void calculateCumulativeOddsWithTwoPacks() {
        // A 50% base chance over 2 packs: 1-(1-0.5)^2 = 1-0.25 = 0.75 = 75%
        double twoPackOdds = cardSet.calculateCumulativeOdds(50.0, 2);
        assertEquals(75.0, twoPackOdds, 0.001,
                "Odds for 2 packs should equal the expected cumulative odds.");
    }

    /**
     * Tests the calculateCumulativeOdds method of the CardSet class with zero packs.
     * Ensures that the cumulative odds are calculated correctly for zero packs.
     */
    @Test
    void calculateCumulativeOddsWithZeroPacks() {
        // Opening 0 packs should always result in a 0% chance of success.
        double zeroPacksOdds = cardSet.calculateCumulativeOdds(25.0, 0);
        assertEquals(0.0, zeroPacksOdds, 0.001,
                "Odds for 0 packs should equal 0%.");
    }

    /**
     * Tests the calculateCumulativeOdds method of the CardSet class with a high number of packs.
     * Ensures that the cumulative odds are calculated correctly for a large number of packs.
     */
    @Test
    void calculateRealisticChaseOdds() {
        // A 1% base chance over 50 packs: 1-(1-0.01)^50 = 1-0.605 = 0.395 = 39.5%
        double realisticChaseOdds = cardSet.calculateCumulativeOdds(1.0, 50);
        assertEquals(39.5, realisticChaseOdds, 0.001,
                "Odds for 50 packs should be approximately 39.5%.");
    }
}