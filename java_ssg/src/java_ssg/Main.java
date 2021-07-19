package java_ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static List<ArticleClass1> articles1;

	static {
		articles1 = new ArrayList<>();
	}

	public static void main(String[] args) {

		System.out.println("====프로그램 시작====");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("====명령어를 입력해 주세요====");

			String command = sc.nextLine();
			command = command.trim();

			if (command.length() == 0) {
				continue;
			}
			if (command.equals("exit")) {
				break;
			}

			// article write 시작
			if (command.equals("article write")) {
				int id = articles1.size() + 1;

				String regDate = Util.getNowDateStr();

				System.out.println("제목을 입력해 주세요 :");
				String title = sc.nextLine();

				System.out.println("내용을 입력해 주세요 :");
				String body = sc.nextLine();

				// 클래스이름 객체이름 new연산자가 ArticleClass 클래스 호출
				ArticleClass1 article2 = new ArticleClass1(id, regDate, title, body);
				articles1.add(article2);

				System.out.println(id + " 번 글이 생성되었습니다.");

			} else if (command.equals("article list")) {

				if (articles1.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}

				System.out.println("====LIST====");
				System.out.println("번호  | 조회  | 제목");

				for (int i = articles1.size() - 1; i >= 0; i--) {
					ArticleClass1 article4 = articles1.get(i);

//					System.out.println("번호 : " + article4.id + " | 제목 : " + article4.title + " | 조회수 : " + article4.hit);
					System.out.printf("%4d | %4d | %s\n", article4.id, article4.hit, article4.title);
				}

			} else if (command.startsWith("article detail ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				ArticleClass1 foundArticle = null;

				for (int i = 0; i < articles1.size(); i++) {
					ArticleClass1 article5 = articles1.get(i);

					if (article5.id == id) {
						foundArticle = article5;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.println(id + " 번 게시물이 존재하지 않습니다.");
					continue;
				}

				foundArticle.increaseHit();

				System.out.println("번호 : " + foundArticle.id);
				System.out.println("날짜 : " + foundArticle.regDate);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);
				System.out.println("조회수 : " + foundArticle.hit);

			} else if (command.startsWith("article modify ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				ArticleClass1 foundArticle = null;

				for (int i = 0; i < articles1.size(); i++) {
					ArticleClass1 article5 = articles1.get(i);

					if (article5.id == id) {
						foundArticle = article5;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.println(id + " 번 게시물이 존재하지 않습니다.");
					continue;
				}

				System.out.println("제목을 입력해 주세요 :");
				String title = sc.nextLine();
				System.out.println("내용을 입력해 주세요 :");
				String body = sc.nextLine();
				// String regDate = Util.getNowDateStr(); //수정되면 날짜도 변경되게 하고 싶으면.

				foundArticle.title = title;
				foundArticle.body = body;
				// foundArticle.regDate = regDate;

				System.out.println(id + "번 째 게시물이 수정되었습니다.");

			} else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				int foundIndex = -1;

				for (int i = 0; i < articles1.size(); i++) {
					ArticleClass1 article6 = articles1.get(i);

					if (article6.id == id) {
						foundIndex = i;
						break;
					}
				}

				if (foundIndex == -1) {
					System.out.println(id + " 번 게시물이 존재하지 않습니다.");
					continue;
				}

				articles1.remove(foundIndex);

				System.out.println(id + " 번 게시물을 삭제했습니다.");

				// delete end
			} else {
				System.out.println(command + " 는 존재하지 않는 명령어 입니다.");
			} // article write 끝

		} // while끝

		sc.close();

		System.out.println("====프로그램 종료====");
	}// main끝

	private static void makeTestData() {
		System.out.println("테스트 데이터 생성 ");

		articles1.add(new ArticleClass1(1, Util.getNowDateStr(), "제목1", "내용1", 33));
		articles1.add(new ArticleClass1(2, Util.getNowDateStr(), "제목2", "내용2", 66));
		articles1.add(new ArticleClass1(3, Util.getNowDateStr(), "제목3", "내용3", 100));

	}
}// class main 끝

//==============class==============
class ArticleClass1 {
	int id;
	String regDate;
	String title;
	String body;
	int hit;

	public ArticleClass1(int id, String regDate, String title, String body) {
		this(id, regDate, title, body, 0);
	}

	public ArticleClass1(int id, String regDate, String title, String body, int hit) {
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