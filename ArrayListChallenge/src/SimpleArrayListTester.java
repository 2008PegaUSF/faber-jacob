
public class SimpleArrayListTester {
	public static void main(String[] args) {
		
		//If initial size is 0, size is set to 10
		SimpleArrayList<Integer> s = new SimpleArrayList<Integer>(0);
		
		for(int i = 0; i < 15; i++) {
			s.addToList(i);
		}
		System.out.println(s);
		System.out.println(s.getAt(15));
	}
}
