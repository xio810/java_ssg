package java_ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		System.out.println("====프로그램 시작====");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		List<ArticleClass> articles1 = new ArrayList<>();

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
				int id = lastArticleId + 1;
				lastArticleId = id;

				String regDate = Util.getNowDateStr();

				System.out.println("제목을 입력해 주세요 :");
				String title = sc.nextLine();

				System.out.println("내용을 입력해 주세요 :");
				String body = sc.nextLine();

				// 클래스이름 객체이름 new연산자가 ArticleClass 클래스 호출
				ArticleClass article2 = new ArticleClass(id, regDate, title, body);
				articles1.add(article2);

				System.out.println(id + " 번 글이 생성되었습니다.");

			} else if (command.equals("article list")) {

				if (articles1.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}

				System.out.println("====LIST====");

				for (int i = articles1.size() - 1; i >= 0; i--) {
					ArticleClass article4 = articles1.get(i);

					System.out.println("번호 : " + article4.id + " | 제목 : " + article4.title);
				}

			} else if (command.startsWith("article detail ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				ArticleClass foundArticle = null;

				for (int i = 0; i < articles1.size(); i++) {
					ArticleClass article5 = articles1.get(i);

					if (article5.id == id) {
						foundArticle = article5;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.println(id + " 번 게시물이 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호 : " + foundArticle.id);
				System.out.println("날짜 : " + foundArticle.regDate);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);

			} else if (command.startsWith("article modify ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				ArticleClass foundArticle = null;

				for (int i = 0; i < articles1.size(); i++) {
					ArticleClass article5 = articles1.get(i);

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
				//String regDate = Util.getNowDateStr(); //수정되면 날짜도 변경되게 하고 싶으면.
				
				foundArticle.title = title;
				foundArticle.body = body;
				//foundArticle.regDate = regDate;
				
				System.out.println(id + "번 째 게시물이 수정되었습니다.");

			} else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				int foundIndex = -1;

				for (int i = 0; i < articles1.size(); i++) {
					ArticleClass article6 = articles1.get(i);

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
}// class main 끝

//==============class==============
class ArticleClass {
	int id;
	String regDate;
	String title;
	String body;

	public ArticleClass(int id, String regDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
	}
}// class article