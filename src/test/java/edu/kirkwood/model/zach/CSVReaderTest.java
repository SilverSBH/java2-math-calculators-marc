package edu.kirkwood.model.zach;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class CSVReaderTest {

    @Test
    void testLoadSet() {
        // Arrange: point to temporary CSV file for testing inside src/test/resources/data/DRI-Test-Set.csv
        String testFilePath = "data/DRI-Test-Set.csv";
        // ACT: Call the CSVReader
        CardSet cardSet = CSVReader.loadSet(testFilePath);

        //Assert: confirm reader read the set
        assertNotNull(cardSet, "The loaded CardSet should not be null.");


        // Assert that the CardSet contains the expected number of cards
        assertEquals(3, cardSet.getSetSize(), "CardSet should contain 3 cards after loading from CSV.");

        // Assert that the cards have the expected attributes
        List<Card> laprasCards = cardSet.searchPokemon("Misty's Lapras");
        assertEquals(1, laprasCards.size(), "There should be 1 Misty's Lapras card in the CardSet.");

        Card lapras = laprasCards.get(0);
        assertEquals("Misty's Lapras", lapras.getName(), "Misty's Lapras should have the correct name.");
        assertEquals("50/182", lapras.getSetNum(), "Misty's Lapras should have the correct set number.");
        assertEquals("Common", lapras.getRarity(), "Misty's Lapras should have the correct rarity.");

        // Verify the prefix of the setID is correctly derived from the filename
        assertEquals("DRI", lapras.getSetID(), "Misty's Lapras should have the correct set ID derived from the filename.");
    }
}