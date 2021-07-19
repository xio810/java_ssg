package java_ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java_ssg.dto.ArticleClass;
import java_ssg.util.Util;

public class App {

	private List<ArticleClass> articles1;

	public App() {
		articles1 = new ArrayList<>();
	}

	public void start() {

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
				ArticleClass article2 = new ArticleClass(id, regDate, title, body);
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
					ArticleClass article4 = articles1.get(i);

//					System.out.println("번호 : " + article4.id + " | 제목 : " + article4.title + " | 조회수 : " + article4.hit);
					System.out.printf("%4d | %4d | %s\n", article4.id, article4.hit, article4.title);
				}

			} else if (command.startsWith("article detail ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				ArticleClass foundArticle = getArticleById(id);

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

				ArticleClass foundArticle = getArticleById(id);

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
				// String regDate = Util.getNowDateStr(); //수정되면 날짜도 변경되게 하고 싶으면.

				foundArticle.title = title;
				foundArticle.body = body;
				// foundArticle.regDate = regDate;

				System.out.println(id + "번 째 게시물이 수정되었습니다.");

			} else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
				int foundIndex = getArticleIndexById(id);
				
//				for (int i = 0; i < articles1.size(); i++) {
//					ArticleClass article6 = articles1.get(i);
//
//					if (article6.id == id) {
//						foundIndex = i;
//						break;
//					}
//				}

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

	private int getArticleIndexById(int id) {
		int i = 0;
		for (ArticleClass article5 : articles1) {
			if (article5.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private ArticleClass getArticleById(int id) {
		int index = getArticleIndexById(id);
		
		if (index != -1) {
			return articles1.get(index);
		}

//		for (int i = 0; i < articles1.size(); i++) {
//			ArticleClass article5 = articles1.get(i);
//
//			if (article5.id == id) {
//				return articles1.get(i);
//			}
//		}
		return null;

	}

	private void makeTestData() {
		System.out.println("테스트 데이터 생성 ");

		articles1.add(new ArticleClass(1, Util.getNowDateStr(), "제목1", "내용1", 33));
		articles1.add(new ArticleClass(2, Util.getNowDateStr(), "제목2", "내용2", 66));
		articles1.add(new ArticleClass(3, Util.getNowDateStr(), "제목3", "내용3", 100));

	}
}
