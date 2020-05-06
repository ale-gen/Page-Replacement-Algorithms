package Page;

public class Page {
	
	private int arrivalTime;
	private int pageNumber;
	
	public Page(int pageNumber, int arrivalTime) {
		this.arrivalTime = arrivalTime;
		this.pageNumber = pageNumber;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	
	
	

}
