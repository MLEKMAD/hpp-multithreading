package fr.tse.fise2.info4.lab10.exercise2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Exercice2 {

	public static void main(String[] args) {
		int nbThreads = 3;
		Thread[] consumers = new Thread[nbThreads];
		
		BlockingQueue<String> sharedQueue = new LinkedBlockingQueue<>();
		int numberOfSenetence = 10;
		Producer prod = new Producer(sharedQueue, numberOfSenetence);
		Thread producer = new Thread(prod); 
		producer.start();
		
		for (int i=0; i<nbThreads; i++) {
			consumers[i] = new Thread(new Consumer(sharedQueue));
			consumers[i].start();
		}
		
		try {
			producer.join();
			for (int i=0; i<nbThreads; i++) {
				consumers[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (String line : sharedQueue) {
			System.out.println(line);
		}
		
	}

}
