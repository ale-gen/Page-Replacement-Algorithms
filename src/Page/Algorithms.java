package Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Algorithms {
	
	public int FIFO(ArrayList<Page> list, int frameNumber) {
		
		int pageFaults = 0;
		int found = 1;
		ArrayList<Page> list1 = new ArrayList<Page>(list.size());
		Queue frames = new Queue(frameNumber);
		
		for (int i = 0; i < list.size(); i++) {
			list1.add(list.get(i));
		}
		
		while (!list1.isEmpty()) {
				if (frames.size() < frameNumber) {
					frames.push(list1.get(0));
					pageFaults++;
					//System.out.println("Błąd strony dla " + list1.get(0).getPageNumber());
					list1.remove(0);
				} else {
					for (int i = 0; i < frames.size() - 1; i++) {
						if (list1.get(0).getPageNumber() == frames.get(i).getPageNumber()) {
							list1.remove(0);
							found = 1;
							//System.out.println("Nie ma błędu strony");
							break;
						} else {
							found = 0;
						}
					}
					if (found == 0) {
						frames.pop();
						frames.push(list1.get(0));
						pageFaults++;
						list1.remove(0);
					}
				}
		}
		return pageFaults;
	}
	
	public int OPT(ArrayList<Page> list, int frameNumber) {
		
		int pageFaults = 0;
		int found = 1;

		ArrayList<Page> list1 = new ArrayList<Page>(list.size());
		ArrayList<Integer> frames = new ArrayList<Integer>(frameNumber);
		
		for (int i = 0; i < list.size(); i++) {
			list1.add(list.get(i));
		}
		
		while (!list1.isEmpty()) {
			
			if (frames.size() < frameNumber) {
				if (!frames.contains(list1.get(0).getPageNumber())) {
					frames.add(list1.get(0).getPageNumber());
					pageFaults++;
					System.out.println(pageFaults);
					System.out.println("Wolna ramka, więc wstawiamy: " + list1.get(0).getPageNumber());
				} 
			} else {
				if (frames.contains(list1.get(0).getPageNumber())) {
					System.out.println("Ramka już jest w pamięci, więc nic nie robimy: " + list1.get(0).getPageNumber());
				} else {
					System.out.println("Nie ma wolnej ramki: ");
					ArrayList<Integer> futurePage = new ArrayList<Integer>();
					for (int j = 1; j < list1.size(); j++) {
						if (!futurePage.contains(list1.get(j).getPageNumber())) {
							futurePage.add(list1.get(j).getPageNumber());
						}
					}
					System.out.println(futurePage.toString());
					for (int j = 0; j < frames.size(); j++) {
						if (!futurePage.contains(frames.get(j))) {
							System.out.println("Ramka nie pojawi się w przyszłości, więc możemy ją zastąpić: " + list1.get(0).getPageNumber() + "za "+ frames.get(j));
							frames.remove(j);
							frames.add(j, list1.get(0).getPageNumber());
							found = 0;
							pageFaults++;
							System.out.println(pageFaults);
							System.out.println(frames.toString());
							break;
						} else {
							found = 1;
						}
					}
					if (found == 1) {
						System.out.println("Każda ramka pojawi się jeszcze w przyszłości, więc usuwamy tą najpóźniejszą: ");
						for (int i = futurePage.size() - 1; i >= 0; i--) {
							if (frames.contains(futurePage.get(i))) {
								System.out.println("Usuwamy ramkę: " + futurePage.get(i) + " i zamieniamy ją na: " + list1.get(0).getPageNumber());
								frames.remove(futurePage.get(i));
								frames.add(list1.get(0).getPageNumber());
								pageFaults++;
								System.out.println(pageFaults);
								break;
							} 
						}
					}
				}
			}
			
			list1.remove(0);
		}
		
		return pageFaults;
	}
	
	public int LRU(ArrayList<Page> list, int frameNumber) {
		
		int pageFaults = 0;
		
		ArrayList<Page> list1 = new ArrayList<Page>(list.size());
		HashSet<Integer> frame = new HashSet<Integer>(); 
		HashMap<Integer, Integer> indexes = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < list.size(); i++) {
			list1.add(list.get(i));
		}
		
		for (int i = 0; i < list.size(); i++) {
			
			if (frame.size() < frameNumber) {
				if (!frame.contains(list1.get(i).getPageNumber())) {
					frame.add(list1.get(i).getPageNumber());
					//System.out.println("Wolne miejsce: dotychczasowy rozmiar: " + frame.size());
					//System.out.println("Strona: " + list1.get(i).getPageNumber());
					pageFaults++;
				}
				indexes.put(list1.get(i).getPageNumber(), i);
				//System.out.println(indexes.toString());
			} else {
				if (!frame.contains(list1.get(i).getPageNumber())) {
					int lru = Integer.MAX_VALUE;
					int val = Integer.MIN_VALUE;
					
					Iterator<Integer> itr = frame.iterator();
					
					while (itr.hasNext()) {
						int temp = itr.next();
						if (indexes.get(temp) < lru) {
							lru = indexes.get(temp);
							val = temp;
						}
					}
					frame.remove(val);
					indexes.remove(val);
					frame.add(list1.get(i).getPageNumber());
					pageFaults++;
				}
				indexes.put(list1.get(i).getPageNumber(), i);
			}
		}
		return pageFaults;
	}
	
	public int SCA2(ArrayList<Page> list, int frameNumber) {
		int pageFaults = 0;
		
		ArrayList<Page> list1 = new ArrayList<Page>(list.size());
		ArrayList<Integer> frame = new ArrayList<Integer>(frameNumber);
		ArrayList<Integer> bit = new ArrayList<Integer>(frameNumber);
		
		for (int i = 0; i < list.size(); i++) {
			list1.add(list.get(i));
		}
		
		for (int i = 0; i < list1.size(); i++) {
			
			if (frame.size() < frameNumber) {
				frame.add(list1.get(i).getPageNumber());
				bit.add(1);
				
				//System.out.println("Błąd strony (wolna ramka) : " + list1.get(i).getPageNumber());
				pageFaults++;
			} else {
				if (!frame.contains(list1.get(i).getPageNumber())) {
					while (true) {
					if (bit.get(0) == 0) {
						frame.remove(0);
						bit.remove(0);
						frame.add(list1.get(i).getPageNumber());
						bit.add(1);
						//System.out.println("Błąd strony (ramki nie było) : " + list1.get(i).getPageNumber());
						pageFaults++;
						break;
					} else {
						int temp = frame.get(0);
						frame.remove(0);
						bit.remove(0);
						frame.add(temp);
						bit.add(0);
					}
					}
				}  else {
					//System.out.println("Brak błędu dla : " + list1.get(i).getPageNumber());
				}
			}
			
			
		}
		
		
		
		return pageFaults;
	}
	
	public int Rand(ArrayList<Page> list, int frameNumber) {
		int pageFaults = 0;
		int found = 1;
		ArrayList<Page> list1 = new ArrayList<Page>(list.size());
		ArrayList<Page> frames = new ArrayList<Page>(frameNumber);
		
		for (int i = 0; i < list.size(); i++) {
			list1.add(list.get(i));
		}
		
		while (!list1.isEmpty()) {
			if (frames.size() < frameNumber) {
				frames.add(list1.get(0));
				list1.remove(0);
				pageFaults++;
			} else {
				for (int i = 0; i < frames.size() - 1; i++) {
					if (list1.get(0).getPageNumber() == frames.get(i).getPageNumber()) {
						list1.remove(0);
						found = 1;
						//System.out.println("Nie ma błędu strony");
						break;
					} else {
						found = 0;
					}
				}
				if (found == 0) {
					Random r = new Random(); 
					int k = r.nextInt(frameNumber);
					//System.out.println("k = " + k);
					frames.remove(k);
					frames.add(k, list1.get(0));
					list1.remove(0);
					pageFaults++;
				}
			}
		}
		return pageFaults;
	}
	
	
}
