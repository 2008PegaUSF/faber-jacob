import java.util.Random;
import java.util.ArrayDeque;

public class DequeTakerThread extends Thread {
	
	public static ArrayDeque<Integer> deq;
	public static DequeFillerThread filler;
	
	public DequeTakerThread(ArrayDeque<Integer> deq, DequeFillerThread filler) {
		this.deq = deq;
		this.filler = filler;
	}
	
	public boolean isPrime(Integer n) {
		//Check each number from 2 to n-1. If n is divisible by one of the numbers i, then return false.
		for(int i = 2; i < n; i++) {
			if(n % i == 0) {
				return false;
			}
		}
		//If n is not divisible by any number under it, it is a prime.
		return true;
	}
	
	public boolean eatPrimes() {
		int num = -1;
		synchronized(deq) {
			if(!deq.isEmpty()) {
				num = deq.removeLast();
			}
		}
		if(num > -1) {
			if(isPrime(num)) {
				System.out.println(num + " is a prime!");
			}
			else {
				System.out.println(num + " is not a prime.");
			}
			return true;
		}
		else { //No prime found
			return false;
		}
	}
	
	public void run() {
		System.out.println("Taker thread started");
		while(filler.isAlive()) {
			eatPrimes();
		}
		
	}
}
