package fr.tse.fise2.info4.lab10.exercise2;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

public class TestProducer {

	/**
	 * We call {@link Producer#generatedWords()} before the production has started,
	 * therefore we expect an {@link IllegalStateException}, which is unchecked
	 */
	@Test(expected = IllegalStateException.class)
	public void test() {
		// TODO modify the test to take an int and a queue in the constructor
		BlockingQueue<String> sharedQueue = new LinkedBlockingQueue<>();
		Producer prod = new Producer(sharedQueue, 2);
		prod.generatedWords();
	}

	@Test
	public void testWordGeneration() {
		BlockingQueue<String> sharedQueue = new LinkedBlockingQueue<>();
		Producer prod = new Producer(sharedQueue, 4);
		prod.setTerminationFlag();
		prod.generateSentence(10);
		prod.generateSentence(2);
		// Assert that the number of generated words is good
		assertEquals(12, prod.generatedWords());
	}

}
