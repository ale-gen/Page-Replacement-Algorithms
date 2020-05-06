package Page;

public class Queue {

	int maxSize;
	Page[] queue;
	int first;
	int last;
	int pagesNumber;
	
	public Queue(int size) {
		maxSize = size;
		first = 0;
		last = -1;
		queue = new Page[maxSize];
		pagesNumber = 0;
	}
	
	public void push(Page page) {
		if (last == maxSize - 1) {
			last = -1;
		}
		queue[++last] = page;
		pagesNumber++;
	}
	
	public Page pop() {
		
		Page temp = queue[first++];
		if (first == maxSize) 
			first = 0;
		pagesNumber--;
		return temp;
	}
	
	public Page get(int index) {
		return queue[index];
	}
	
	public Page getPage() {
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
