package fr.tse.fise2.info4.lab10.exercise1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Counter {

	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final Lock writeLock = readWriteLock.writeLock();

	int counter;

	public Counter() {
		this.counter = 0;
	}

	public void incrementCounter() {
		writeLock.lock();
		try {
			int tmp = counter;
			tmp++;
			Thread.sleep((long) (Math.random() * 50));
			this.counter = tmp;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}

	}

	public int getCounter() {
		return counter;
	}

}
