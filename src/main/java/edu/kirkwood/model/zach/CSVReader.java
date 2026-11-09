package edu.kirkwood.model.zach;

import edu.kirkwood.model.zach.Card;
import edu.kirkwood.view.UIUtility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A utility class for reading CSV files.
 * This class provides methods to read and process CSV files, allowing for easy extraction of data.
 */
public class CSVReader {
    String filePath = "";
    /**
     * Loads a set of Pokémon cards from a CSV file and returns a CardSet object.
     * The CSV file should have the following format:
     * name,setNum,rarity
     * setID is derived from the first segment of the filename
     * Each line represents a card with its attributes.
     *
     * @param filePath the path to the CSV file containing the card data ("resources/data/DRI-Pokemon-Destined-Rivals.csv")
     * @return a CardSet object containing the loaded cards
     */
    public static CardSet loadSet(String filePath) {

        CardSet cardSet = new CardSet();
        String line;
        String delimiter = ",";

        // Extract the setID from the file path
        String[] pathParts = filePath.split("/");
        String fileName = pathParts[pathParts.length - 1]; // gets "DRI-Pokemon-Destined-Rivals.csv"
        // gets "DRI"
        String setID = fileName.split("-")[0];

        //Read the CSV line by line from inside the JAR resources file
        try (InputStream is = CSVReader.class.getResourceAsStream("/" + filePath)) {

            if (is == null) {
                UIUtility.displayError("File not found: " + filePath);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                // Skip the header line
                br .readLine();

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(delimiter);

                    if (data.length == 3) {
                        String name = data[0].trim();
                        String setNum = data[1].trim();
                        String rarity = data[2].trim();

                        Card card = new Card(name, setNum, rarity, setID);
                        cardSet.addCard(card);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file at " + filePath + ": " + e.getMessage());
        }

        return cardSet;
    }
}