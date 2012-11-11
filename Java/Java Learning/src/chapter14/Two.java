package chapter14;

import java.util.LinkedList;
import java.util.Random;

import static java.lang.Math.random;

public class Two {
	private final static int MAX_NUMBER = 13;
	
	private static LinkedList<String> deck = new LinkedList<String>();
	private static Random rand = new Random();
	
	public static void main(String[] args) {
		initDeck();
		printDeck();
		shuffleDeck(52*7);
		printDeck();
	}
	
	private static void shuffleDeck(int times) {
		for (int i = 0; i < times; i++) {
			int index1 = rand.nextInt(deck.size());
			int index2;
			do {
				index2 = rand.nextInt(deck.size());
			} while (index1 == index2);
			
			String tmpCard = deck.get(index1);
			deck.set(index1, deck.get(index2));
			deck.set(index2, tmpCard);
		}
	}

	private static void printDeck() {
		for (String card : deck) {
			System.out.printf("%s ", card);
		}
		
		System.out.println();
	}
	
	private static void initDeck() {
		char[] suits = {'C', 'D', 'H', 'S'};
		for (char suit : suits) {
			for (int number = 1; number <= MAX_NUMBER; number++) {
				String card;
				switch (number) {
					case 1 :
						card = 'A' + String.valueOf(suit);
						break;
						
					case 11 :
						card = 'J' + String.valueOf(suit);
						break;

					case 12 :
						card = 'Q' + String.valueOf(suit);
						break;
		
					case 13 :
						card = 'K' + String.valueOf(suit);
						break;
						
					default :
						card = String.valueOf(number) + String.valueOf(suit);
				}
				
				deck.add(card);
			}
		}
	}
}
