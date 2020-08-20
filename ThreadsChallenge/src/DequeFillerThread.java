import java.util.ArrayDeque;
import java.util.Random;
public class DequeFillerThread extends Thread {
	public static ArrayDeque<Integer> deq;
	
	public DequeFillerThread(ArrayDeque<Integer> deq) {
		this.deq = deq;
	}
	
	public void fillDeque() throws InterruptedException {
		Random r = new Random();
		int count = 0;
		while(count < 100) {
			synchronized(deq) {
				while(deq.size() < 5) {
					deq.addFirst(r.nextInt(10001));
					count++;
				}
			}
			System.out.println("Filler count: " + count);
		}
	}//end fillDeque
	
	public void run() {
		System.out.println("Filler thread started");
		try {
			fillDeque();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}
	
}
