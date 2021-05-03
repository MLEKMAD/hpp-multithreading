package fr.tse.fise2.info4.lab10.exercise2;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestConsumer {

	@Test
	public void test() {
		Consumer consumer = new Consumer();
		int wordsInSentence = consumer.countWords("not - implemented yet -");
		assertEquals(3, wordsInSentence);
	}

}
