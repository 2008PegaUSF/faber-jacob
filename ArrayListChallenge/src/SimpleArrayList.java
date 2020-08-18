
public class SimpleArrayList<T> {
	private Object arr[];
	int count = 0;
	
	//Constructor with no arguments sets initial size to 10
	SimpleArrayList() {
		arr = new Object[10];
	}
	
	//Constructor with size argument, sets initial size to 'size'
	SimpleArrayList(int size) {
		//Invalid sizes handled by setting size to 10
		if(size < 1) {
			size = 10;
		}
		arr = new Object[size];
	}
	
	//Adds a new object to the list. The array is extended if there is not enough room to add a new object
	void addToList(Object o) {
		if(count == arr.length-1) {
			expandArray();
		}
		arr[count++] = o;
	}
	
	//Copies the arr to a new array of double the length of the original, then sets arr to the new array
	void expandArray() {
		Object newArr[] = new Object[arr.length*2];
		for(int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i];
		}
		arr = newArr;
	}
	
	//Gets an object from the array
	Object getAt(int index) {
		if(index > count-1 || index < 0) {
			throw new IndexOutOfBoundsException("" + index);
		}
		return arr[index];
	}

	public String toString() {
		String s = "[";
		
		for(int i = 0; i < count; i++) {
			s += arr[i];
			if(i < count-1) {
				s += ", ";
			}
		}
		s += "]";
		return s;
	}
	
}
