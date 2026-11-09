package edu.kirkwood.model.zach;

/**
 * Represents a Pokémon card from a booster pack with attributes such as name,
 * set number, rarity, set ID, and chance of obtaining the card.
 */
public class Card implements Comparable<Card> {
    private String name;
    private String setNum;
    private String rarity;
    private String setID;


    /**
     * Constructs a new Card with the specified attributes.
     * @param name the name of the Pokémon on the card (e.g., "Pikachu")
     * @param setNum the set number of the card(e.g., "023/189")
     * @param rarity the rarity of the card (e.g., "Common", "Rare", "Ultra Rare")
     * @param setID the set ID of the card
     *
     */
    public Card(String name, String setNum, String rarity, String setID) {
        this.name = name;
        this.setNum = setNum;
        this.rarity = rarity;
        this.setID = setID;
    }

    /**
     * Returns the name of the Pokémon on the card.
     * @return the name of the Pokémon on the card.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the set number of the card.
     * @return the set number of the card
     */
    public String getSetNum() {
        return setNum;
    }

    /**
     * Returns the rarity of the card.
     * @return the rarity of the card
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * Returns the set ID of the card.
     * @return the set ID of the card
     */
    public String getSetID() {
        return setID;
    }

    /**
     * Returns a string representation of the card, including its name, set number, rarity, and set ID.
     * @return a string representation of the card
     */
    @Override
    public String toString() {
        return  name  +
                ", Set No: " + setNum +
                ", " + rarity +
                ", Set: " + setID +
                ".";
    }

    /**
     * Compares this card to another card for equality based on their attributes.
     * @param obj the object to compare with
     * @return true if the cards are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Card card = (Card) obj;

        return name.equals(card.name) &&
                setNum.equals(card.setNum) &&
                rarity.equals(card.rarity) &&
                setID.equals(card.setID);
    }

    /**
     * Compares this card with another card for sorting purposes.
     * Cards are first compared by name (case-insensitive), and if the names are the same,
     * they are compared by set number (numerically).
     * @param other the card to compare with
     * @return a negative integer, zero, or a positive integer as this card is less than,
     *         equal to, or greater than the specified card
     */
    @Override
    public int compareTo(Card other) {
       int nameComparison = this.name.compareToIgnoreCase(other.name);

       if (nameComparison != 0) {
           return nameComparison;
       }

       // to access the object on which the current object is based, use the callable prop


       // If the names are exactly the same, sort numerically by set number
        try{
            int thisNum = Integer.parseInt(this.setNum.split("/")[0]);
            int otherNum = Integer.parseInt(other.setNum.split("/")[0]);

            return Integer.compare(thisNum, otherNum);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // If parsing fails, or if Promo card, fall back to string comparison
            return this.setNum.compareToIgnoreCase(other.setNum);
        }
    }
}