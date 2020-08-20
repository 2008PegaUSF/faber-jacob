import java.util.ArrayDeque;
//

public class PrimeEater {
	
	public static void main(String[] args) throws InterruptedException {
		ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
		
		DequeFillerThread filler = new DequeFillerThread(deq);
		DequeTakerThread taker = new DequeTakerThread(deq, filler);
		
		filler.start();
		taker.start();
	}
	
}
