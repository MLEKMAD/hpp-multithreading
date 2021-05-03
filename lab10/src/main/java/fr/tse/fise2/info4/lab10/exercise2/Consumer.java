package fr.tse.fise2.info4.lab10.exercise2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Queue;
/**
 * TODO Modify this class so that:
 * <ul>
 * <li>It counts the number of words in an application wide
 * {@link AtomicInteger} shared among the {@link Consumer}</li>
 * <li>Counts the occurrence of each word using an application wide
 * {@link ConcurrentHashMap} shared among the {@link Consumer}</li>
 * </ul>
 * 
 *
 */
public class Consumer implements Runnable {
	public static AtomicInteger wordsInLine = new AtomicInteger();  
	private BlockingQueue<String> sharedQueue;
	public Consumer() {
		
	}
	public Consumer(BlockingQueue<String> s) {
		super();
		sharedQueue = s;
	}
	

	@Override
	public void run() {
		while (!sharedQueue.isEmpty()) {
			String line;
			try {
				line = sharedQueue.take();
				wordsInLine.addAndGet(countWords(line));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}	
	}

	public int countWords(String sentence) {
		sentence = sentence.replaceAll("[^A-Za-z0-9 ]", "").replaceAll("  ", " "); // removing non-alphaneumeric and double spaces 
		return sentence.split(" ").length;
	}
	

	
	

}
