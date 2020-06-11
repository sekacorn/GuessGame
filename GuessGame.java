import java.util.Scanner;
import java.util.Random;
//Sekacorn

public class GuessGame {
	public static void main(String[] args) {
		Scanner kbd = new Scanner(System.in);
		String runagain = "yes";

		// Declaring random number
		Random rand = new Random();
		StringBuffer dashes;
		int Chances;
		boolean done;
		final int CHANCES = 6;
		String wordGuess;
		int Computer = 0;
		int Playerwin = 0;
		String guesses = " ";
		char letter = ' ';
		//An Array  that contains Twenty(20) available words to be used in game.
		String[] words = { "elephant", "analog", "guitar", "computer",
				"jersey", "monkey", "donkey", "mother", "father", "awesome",
				"school", "Lion", "bench", "answer", "enter", "house",
				"promote", "given", "sister", "brother" };
		String word = " ";
		char guess1;
		

		Chances = CHANCES;

		// The game starts
		while (runagain.equals("yes") || runagain.equals("Yes")|| runagain.equals("YES")) {
			word = RandomWord(word, words, rand);
			dashes = WordDashes(word);
			System.out.println("Welcome to the Hangman Word game!");
			done = false;
			while (!done) {

				System.out.println(dashes);
				System.out
						.println("Choose a letter or enter zero to guess the word");
				wordGuess = kbd.next();

				guess1 = wordGuess.charAt(0);
				if (guess1 == '0') {
					System.out.println("Guess the word!");
					wordGuess = kbd.next();
				}

				// process the guess if its more that one character
				if (wordGuess.length() > 1) {
					done = ProcessGuessWord1(wordGuess, word, Playerwin,
							Computer, done, Chances);
					if (wordGuess.equals(word)) {
						
						Playerwin++;
						
						 done = true;
					}

					else {
						
						--Chances;
						Computer++;
						

						 done = true;
						}
					if (word.indexOf(letter) < 0) {
						Chances--;
					}
				} else // processing if it is a single letter. if it is in the
						// word
						// it replaces the dashes o

				{
					letter = wordGuess.charAt(0);
					guesses += letter;
					if (word.indexOf(letter) < 0) {
						--Chances;}
				
					done = ProcessGuessLetter2(letter, wordGuess, Chances,
							Computer, word, Playerwin, dashes, done, guesses,
							kbd); 
					
							if (Chances == 1){
							System.out
					.println("That letter is not in the word. you must guess the word now");
			System.out.println("Guess the word!");
			wordGuess = kbd.next();

			// processing wordguess
			if (wordGuess.length() > 1) {

				if (wordGuess.equals(word)) {
					Playerwin++;
					System.out.println("Thats corrrect!");
				

				
				done = true;}
			 else {
				Computer++;
				System.out.println("That is not correct!");
				--Chances;
				
				
				 done = true;
			}
			}
			}
					if (Chances == 0)

					{

						Computer++;
						
						
						done = true;
					}
					if (word.equals(dashes.toString())) {
						Playerwin++;
						System.out.println("That is correct!");
						
						 done = true;
					}
					
				}
				

			}

			
			System.out.println("Would you like to play again? please enter 'no' or 'yes'");
			runagain = kbd.next();
			if (runagain.equals ("no")||runagain.equals ("NO")||runagain.equals ("No")) {
				System.out.println("Computer Wins " + Computer
						+ "  Player wins " + Playerwin);
				System.out.println(" See you later!");
			}
		}
	}// End of Main
		
	//A function must that is used to return a random Word to be used in the game
	public static String RandomWord(String word, String[] words, Random rand) {

		word = words[rand.nextInt(words.length)];
		return word;
	}

	// A function used  that accepts the word guessed the word and returns true
	public static boolean ProcessGuessWord1(String wordGuess, String word,
			int Playerwin, int Computer, boolean done, int Chances) {
		if (wordGuess.length() > 1) {
			/*
			 * 
			 * process word wordGuess. If guess equals the word then thats
			 * correct if not it's not correct
			 */
			{
				if (wordGuess.equals(word)) {
					System.out.println("Thats correct!");
					Playerwin++;
					return done = true;
				}
				else {
					System.out.println("That is not correct!");
					--Chances;
					Computer++;
			
					return done = true;
				}
			}
		}
		return done;

	}

	/*****************************************************
	 * Data in: letter, wordGuess, Chances, Computer, word, Playerwin done, kbd,
	 * dashes Data out: done
	 * */
	// A function used to accept the letter and checks to see if the letter is in the Word
	public static boolean ProcessGuessLetter2(char letter, String wordGuess,
			int Chances, int Computer, String word, int Playerwin,
			StringBuffer dashes, boolean done, String guesses, Scanner kbd) {
		letter = wordGuess.charAt(0);
		guesses += letter;
		if (word.indexOf(letter) < 0) {
			
			System.out.print("That letter is not in the word. You have "
					+ Chances + " more guesses");
			System.out.println("");
		} else // if letter is in the word
		{
			// places the correct number of dashes corresponding the
			// word.
			WordLetter(word, dashes, letter);
		}
		
		done =CheckAllLetter(guesses,  word, done);
		return done;
	}


	/*
	 * Change dashes to matching letter for position goes 333Checks if the word
	 * is in the Letter
	 */
	/******************************************************
	 * WordMatching Data in: word, dashes,letter Data out: None l
	 */
	// A function that  displays the contents of the players's version of the word
		// Letters that have not been guessed show up as underscores
	public static void WordLetter(String word, StringBuffer dashes, char letter) {
		for (int index = 0; index < word.length(); index++)
			if (word.charAt(index) == letter)
				dashes.setCharAt(index, letter);
		System.out.println("That is correct!");
	}

	/************************************************************
	 * WordDashes Data in: 
	 * word Data out: dashes
	 */

	//
	public static StringBuffer WordDashes(String word) {
		StringBuffer dashes = new StringBuffer(word.length());
		for (int i = 0; i < word.length(); i++){
			dashes.append('-');
		
	}
		return dashes;}

	/************************************************************
	 * Data in: guesses, word
	 * Data out: done
	 *
	 */
	//A function Checks for if all guessed letters are equivalent to the Computer word
	public static boolean CheckAllLetter(String guesses, String word, boolean done){
		if (guesses.equals(word)){
				done = true;
		}
				else{
					done = false;}
		return done;
	}}
