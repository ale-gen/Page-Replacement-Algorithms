package Page;

public class IntQueue {

	int maxSize;
	int[] queue;
	int first;
	int last;
	int pagesNumber;
	
	public IntQueue(int size) {
		maxSize = size;
		first = 0;
		last = -1;
		queue = new int[maxSize];
		pagesNumber = 0;
	}
	
	public void push(int number) {
		if (last == maxSize - 1) {
			last = -1;
		}
		queue[++last] = number;
		pagesNumber++;
	}
	
	public void push(int index, int number) {
		queue[index] = number;
	}
	
	public int pop() {
		
		int temp = queue[first++];
		if (first == maxSize) 
			first = 0;
		pagesNumber--;
		return temp;
	}
	
	public int get(int index) {
		return queue[index];
	}
	
	public int getPage() {
		return queue[first];
	}
	
	public int size() {
		return pagesNumber;
	}
	
	public boolean isEmpty() {
		return pagesNumber == 0;
	}
	
	public boolean isFull() {
		return pagesNumber == maxSize;
	}
	
}
