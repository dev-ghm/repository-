package together.vo;

import java.sql.Date;

public class Feed {
	int no;
	String writerId;
	String title;
	String body;
	Date writedAt;
	int readCnt;
	public Feed() {
		super();
	}
	public Feed(int no, String writerId, String title, String body, Date writedAt, int readCnt) {
		super();
		this.no = no;
		this.writerId = writerId;
		this.title = title;
		this.body = body;
		this.writedAt = writedAt;
		this.readCnt = readCnt;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getWritedAt() {
		return writedAt;
	}
	public void setWritedAt(Date writedAt) {
		this.writedAt = writedAt;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

}
