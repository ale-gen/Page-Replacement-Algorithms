package Page;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FinalResult {
	
	private int pagesNumber;
	private int framesNumber;
	private int range;
	
	Algorithms alg = new Algorithms();
	ArrayList<Page> list = new ArrayList<Page>();
	
	public ArrayList<Results> pageCount () {
		
		ArrayList<Results> result = new ArrayList<Results>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of random-access memory: ");
		framesNumber = sc.nextInt();
		System.out.println("Enter the size of virtual memory: ");
		pagesNumber = sc.nextInt();
		
		//pageService(pagesNumber);
		
		/*list.add(new Page(1, 0));
		list.add(new Page(2, 0));
		list.add(new Page(3, 0));
		list.add(new Page(4, 0));
		list.add(new Page(1, 0));
		list.add(new Page(2, 0));
		list.add(new Page(5, 0));
		list.add(new Page(1, 0));
		list.add(new Page(2, 0));
		list.add(new Page(3, 0));
		list.add(new Page(4, 0));
		list.add(new Page(5, 0));*/
		
		/*list.add(new Page(7, 0));
		list.add(new Page(0, 0));
		list.add(new Page(1, 0));
		list.add(new Page(2, 0));
		list.add(new Page(0, 0));
		list.add(new Page(3, 0));
		list.add(new Page(0, 0));
		list.add(new Page(4, 0));
		list.add(new Page(2, 0));
		list.add(new Page(3, 0));
		list.add(new Page(0, 0));
		list.add(new Page(3, 0));
		list.add(new Page(2, 0));*/
		
		list.add(new Page(1, 0));
		list.add(new Page(2, 0));
		list.add(new Page(3, 0));
		list.add(new Page(2, 0));
		list.add(new Page(1, 0));
		list.add(new Page(4, 0));
		list.add(new Page(3, 0));
		list.add(new Page(5, 0));
		list.add(new Page(6, 0));
		list.add(new Page(4, 0));
		list.add(new Page(3, 0));
		list.add(new Page(5, 0));
		list.add(new Page(3, 0));
		list.add(new Page(5, 0));
		list.add(new Page(6, 0));
		list.add(new Page(7, 0));
		list.add(new Page(2, 0));
		list.add(new Page(1, 0));
		list.add(new Page(5, 0));
		list.add(new Page(7, 0));
		
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).getPageNumber() + ", ");
		}
		int FIFOpageFaults = alg.FIFO(list, framesNumber);
		int OPTPageFaults = alg.OPT(list, framesNumber);
		int LRUPageFaults = alg.LRU(list, framesNumber);
		int SCAPageFaults = alg.SCA2(list, framesNumber);
		int RandPageFaults = alg.Rand(list, framesNumber);
		
		result.add(new Results("FIFO: ", FIFOpageFaults));
		result.add(new Results("OPT: ", OPTPageFaults));
	    result.add(new Results("LRU: ", LRUPageFaults));
	    result.add(new Results("SCA: ", SCAPageFaults));
	    result.add(new Results("RAND: ", RandPageFaults));
		return result;
	}
	
	public ArrayList<Page> pageService(int pagesNumber) {
		
		while (pagesNumber > 0) {
			System.out.println(pagesNumber);
			if (pagesNumber >= 20) {
				int quantity = (int) pagesNumber / 2;
				System.out.println(quantity);
				for (int i = 0; i < quantity; i++) {
					Random r = new Random();
					int pageNumber = r.nextInt(5);
					list.add(new Page(pageNumber, 0));
				}
				pagesNumber -= quantity;
			} else if (pagesNumber >= 10) {
				int quantity = (int) pagesNumber / 2;
				for (int i = 0; i < quantity; i++) {
					Random r = new Random();
					int pageNumber = r.nextInt(10);
					list.add(new Page(pageNumber, 0));
				}
				pagesNumber -= quantity;
			} else {
				int quantity = pagesNumber;
				for (int i = 0; i < quantity; i++) {
					Random r = new Random();
					int pageNumber = r.nextInt(20);
					list.add(new Page(pageNumber, 0));
				}
				pagesNumber -= quantity;
			}
			System.out.println(pagesNumber);
		}
		return list;
	}

}
