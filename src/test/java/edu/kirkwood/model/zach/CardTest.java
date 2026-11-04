package edu.kirkwood.model.zach;

import edu.kirkwood.model.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Card class.
 * This class tests the constructor, getters, and toString method of the Card class.
 */
public class CardTest {

    /**
     * Tests the constructor and getter methods of the Card class.
     * Ensures that the attributes are correctly assigned and retrieved.
     */
    @Test
    public void testConstructorAndGetters() {
        // Arrange
        String expectedName = "Pikachu";
        String expectedSetNum = "023/189";
        String expectedRarity = "Common";
        String expectedSetID = "Base Set";

        // Act
        Card card = new Card(expectedName, expectedSetNum, expectedRarity, expectedSetID);

        // Assert
        assertEquals(expectedName, card.getName(), " getName() should return the assigned name.");
        assertEquals(expectedSetNum, card.getSetNum(), " getSetNum() should return the assigned set number.");
        assertEquals(expectedRarity, card.getRarity(), " getRarity() should return the assigned rarity.");
        assertEquals(expectedSetID, card.getSetID(), " getSetID() should return the assigned set ID.");

    }
    /**
     * Tests the overridden toString method of the Card class.
     * Ensures that the string representation of the card is formatted correctly.
     */
    @Test
    public void testToStringFormat(){
        // Arrange
        String name = "Blaziken";
        String setNum = "192/182";
        String rarity = "Illustration Rare";
        String setID = "Destined Rivals";


        Card card = new Card(name, setNum, rarity, setID);

        // Act
        String expectedOutput = "Blaziken, Set No: 192/182, Illustration Rare, Set: Destined Rivals.";
        String actualOutput = card.toString();

        // Assert
        assertEquals(expectedOutput, actualOutput, "toString() should return the correct format.");
    }

    /**
     * Tests the equals method of the Card class.
     * Ensures that cards with the same attributes are considered equal and those with different attributes are not.
     */
    @Test
    void testEquals() {
        // Arrange initial cards with the same attributes
        Card card1 = new Card("Blaziken", "192/182", "Illustration Rare", "Destined Rivals");
        Card card2 = new Card("Blaziken", "192/182", "Illustration Rare", "Destined Rivals");

        // Arrange different card with similar but different attributes
        Card card3 = new Card("Applin", "16/182", "Common", "Destined Rivals");
        Card card4 = new Card("Blaziken", "42/182", "Rare", "Destined Rivals");

        // Act & Assert
        assertTrue(card1.equals(card1)); // a card should always be equal to itself
        assertTrue(card1.equals(card2)); // cards with the same attributes should be equal
        assertFalse(card1.equals(card3)); // cards with different names should not be equal
        assertFalse(card1.equals(card4)); // cards with different set numbers should not be equal
        assertFalse(card1.equals(null)); // A card should not be equal to null - this test and the one below it were gemini assisted
        assertFalse(card1.equals("Cyndaquil")); // A card should not be equal to an object of a different class- gemini assisted test
    }


    /**
     * Tests the compareTo method of the Card class.
     * Ensures that cards are compared correctly based on their names and set numbers.
     */
    @Test
    void compareTo() {
        // Arrange cards for alphabetical name comparison
        Card blaziken = new Card("Blaziken", "192/182", "Common", "Destined Rivals");
        Card applin = new Card("Applin", "16/182", "Common", "Destined Rivals");

        // Arrange cards for set number comparison
        Card LaprasCommon = new Card("Misty's Lapras", "50/182", "Common", "Destined Rivals");
        Card LaprasRare = new Card("Misty's Lapras", "194/182", "Common", "Destined Rivals");

        // Act & Assert name comparison
        assertTrue(blaziken.compareTo(applin) > 0, "Blaziken should come after Applin alphabetically.");
        assertTrue(applin.compareTo(blaziken) < 0, "Applin should come before Blaziken alphabetically.");

        // Act & Assert set number comparison
        assertTrue(LaprasCommon.compareTo(LaprasRare) < 0, "LaprasCommon should come before LaprasRare by set number. " + LaprasCommon.getSetNum() + " vs " + LaprasRare.getSetNum());

        assertTrue(blaziken.compareTo(blaziken) == 0, "A card should be equal to itself when compared.");
    }
}