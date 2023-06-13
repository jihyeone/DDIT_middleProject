package kr.or.ddit.middle.vo;

public class PageVO {
	private int start;	// 출력 시작 번호
	private int end;	// 출력 끝 번호
	
	private int startPage;	// 출력 페이지 시작 번호
	private int endPage;	// 출력 페이지 끝 번호
	private int totalPage;	// 총 페이지 수

	private static int perList = 5;	// 한 화면에 출력 할 글 수
	private static int perPage = 3;	// 한 화면에 출력 할 페이지 수 
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public static int getPerList() {
		return perList;
	}
	public static void setPerList(int perList) {
		PageVO.perList = perList;
	}
	public static int getPerPage() {
		return perPage;
	}
	public static void setPerPage(int perPage) {
		PageVO.perPage = perPage;
	}
	
}
