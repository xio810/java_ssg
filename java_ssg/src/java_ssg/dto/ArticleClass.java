package java_ssg.dto;

//==============class==============
public class ArticleClass extends Dto {
	public int id;
	public String regDate;
	public String title;
	public String body;
	public int hit;

	public ArticleClass(int id, String regDate, String title, String body) {
		this(id, regDate, title, body, 0);
	}

	public ArticleClass(int id, String regDate, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}

	public void increaseHit() {
		hit++;
	}
}// class article