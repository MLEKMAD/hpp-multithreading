package fr.tse.fise2.info4.lab10.exercise2;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import de.svenjacobs.loremipsum.LoremIpsum;

/**
 * TODO complete this class as follows:
 * <ol>
 * <li>Constructor takes as parameter the number of sentences to generate and
 * the {@link BlockingQueue} in which to write the sentences</li>
 * <li>When {@link #run()} is called, the producer starts producing sentences in
 * the queue</li>
 * <li>You must keep track of the number of words generated</li>
 * 
 * </ol>
 * 
 * 
 *
 */
public class Producer implements Runnable {
	
	public BlockingQueue<String> sharedQueue = new LinkedBlockingQueue<>();
	public int totalOfWords = 0;
	private Boolean isProducing = true;
	private int numberOfSenetence;
	
	public Producer(BlockingQueue<String> sharedQueue, int numberOfSenetence) {
		super();
		this.sharedQueue = sharedQueue;
		this.numberOfSenetence = numberOfSenetence;
	}

	@Override
	public void run() {
		this.isProducing = true;
		int words = (int) (Math.random() * 100);
		for (int i=0; i<numberOfSenetence; i++) {
			try {
				String line = generateSentence(words);
				sharedQueue.put(line);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				isProducing = false;
			}
		}
		
		try {
			System.out.println("Number of words generated: "+ totalOfWords);
			
		} catch (IllegalStateException e) {
			System.out.println("The producer is still, you guessed it right, producing");
		}

	}

	/**
	 * 
	 * @param words the number of words in the sentence
	 * @return the generated sentence
	 */
	public String generateSentence(int words) {
		// Use the lorem ipsum generator
		// Library already in the pom (see maven dependencies)
		// Refer to the documentation : http://loremipsum.sourceforge.net/
		LoremIpsum loremIpsum = new LoremIpsum();
		totalOfWords += words;
		return loremIpsum.getWords(words);
	}

	/**
	 * 
	 * @return the number of words generated by the producer. If production is not
	 *         finished throw an {@link IllegalStateException}
	 */
	public int generatedWords() {
	if (isProducing) throw new IllegalStateException();
		return totalOfWords;
	}
	
	public void setTerminationFlag() {
		this.isProducing = false;
	}
	
//	public static void main(String[] args) {
//		BlockingQueue<String> sharedQueue = new LinkedBlockingQueue<>();
//		Producer prod = new Producer(sharedQueue, 4);
//		prod.generateSentence(10);
//		prod.generateSentence(2);
//		for (String line:sharedQueue) {
//			System.out.println(line);
//		}
//		
//		System.out.println("number of words: " + totalOfWords);
//	}

}