package lee;

//DTO -> 입력폼의 name와 반드시 같아야 된다. ->액션태그때문에
public class Board { //BoardDTO,BoardVO

	private int num;//게시물번호
	private String author,title,content,date;//작성자,글제목,글내용,작성일
	private int readcnt;//조회수
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	
	
}
