package videopoker.game; 

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class abstract the concept of a poker hand. 
 * A poker hand is always made of five cards and no other configuration is permitted.
 * 
 *
 */
public class Hand {
	
	/**
	 * Exception class for duplicated cards in an hand
	 *
	 */
	public class DuplicateCardException extends RuntimeException {
		public DuplicateCardException(){
			super();
		}
	}

	/**
	 * Array containing the five cards that constitute the hand.
	 */
	private Card[] hand = new Card[5];
	
	
	/**
	 * a hands power (value)
	 */
	private String handPower = WinningPrizes.HAND_NONE;
	
	/**
	 * Initialize the hand with the specified cards.
	 * @param c0 : first card in the hand
	 * @param c1 : second card in the hand
	 * @param c2 : third card in the hand
	 * @param c3 : fourth card in the hand
	 * @param c4 : fifth card in the hand
	 * 
	 * @exception DuplicateCardException if two equal cards are inserted in the hand.
	 * @exception IllegalArgumentException if one of the cards is a null reference.
	 */
	public Hand(Card c0, Card c1, Card c2, Card c3, Card c4) throws DuplicateCardException{
		
		if (c0.equals(c1) || c0.equals(c2) || c0.equals(c3) || c0.equals(c4) || c1.equals(c2) ||
				c1.equals(c3) || c1.equals(c4) || c2.equals(c3) || c2.equals(c4) || c3.equals(c4))
			throw new DuplicateCardException();
		
		if (c0 == null || c1 == null || c2 == null || c3 == null || c4 == null)
			throw new IllegalArgumentException("Card cannot be NULL");
		
		hand[0] = c0;
		hand[1] = c1;
		hand[2] = c2;
		hand[3] = c3;
		hand[4] = c4;
	}
	
	/**
	 * Return the Card in the current hand in the specified position.
	 * @param pos : position in the hand of the card we want to retrieve
	 * @return the Card in the specified position of the hand.
	 */
	public Card getCard(int pos){
		return hand[pos];
	}
	
	/**
	 * Return an array of strings that contains the textual description of the five cards that
	 * constitute the hand.
	 * @return string array containing the textual description of the cards in the hand.
	 */
	public String[] getHandStrArr(){
		String[] handStr = new String[5];
		for(int i = 0; i < 5; i ++){
			if(this.getCard(i) != null)
				handStr[i] = this.getCard(i).toString();
		}
		return handStr;
	}
	
	/**
	 * Change a card in the hand in a specified position with a new card.
	 * @param index : position of the card to substitute.
	 * @param card : card to insert in the specified position.
	 * @exception IllegalArgumentException if the card is a NULL reference.
	 */
	public void setCard(int index, Card card){
		if (card == null)
			throw new IllegalArgumentException("Card cannot be NULL");
		this.hand[index] = card;
	}
	
	/**
	 * Set hand power 
	 * @param handpower
	 */
	public void setHandPower(String handpower){
		this.handPower = handpower;
	}
	
	/**
	 * Provides an array of cards that constitutes the current hand.
	 * @return card array containing the cards in the hand.
	 */
	public Card[] getCardList(){
		
		Card[] new_card = {hand[0], hand[1], hand[2], hand[3], hand[4]};
		
		return new_card;
	}
	
	/**
	 * Order the cards in the hand by value (increasing). The original hand in not modified.
	 * @return card array containing the cards in the hand ordered by value.
	 */
	public Card[] orderByValue(){
		
		Card[] new_card = {hand[0], hand[1], hand[2], hand[3], hand[4]};
		Arrays.sort(new_card, new Comparator<Card>() {
			
		    public int compare(Card c1, Card c2) {
		        return c1.getValueWeight()-c2.getValueWeight();
		    }
		});
		
		return new_card;
		
	}
	
	/**
	 * Order the cards in the hand by value and by suit.
	 * <p> This means that cards of the same suit are grouped together 
	 * in increasing order. The original hand in not modified. </p>
	 * @return card array containing the cards in the hand ordered by value and suit.
	 */
	public Card[] orderByValueSuit(){
		
		Card[] new_card = {hand[0], hand[1], hand[2], hand[3], hand[4]};
		Arrays.sort(new_card, new Comparator<Card>() {
			
		    public int compare(Card c1, Card c2) {
		        return c1.getTotalWeight()-c2.getTotalWeight();
		    }
		});
		
		return new_card;
		
	}
	
	/**
	 * Searches a card in the hand and return the position in which the card is found.
	 * <p> If the card is not found, -1 is returned. </p>
	 * @param card : card to search in the hand.
	 * @return position of the card in the hand.
	 */
	public int search(Card card){
		for (int i = 0; i < hand.length; i++){
			if (hand[i].equals(card))
				return i;
		}
		
		return -1;
	}
	
	/**
	 * Fetching method to retrieve a hand power (value).
	 * @return current hands power
	 */
	public String getHandPower(){
		return this.handPower;
	}
}
