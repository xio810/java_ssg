package java_ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		
		System.out.println("====프로그램 시작====");
		
		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		
		List<Article> articles = new ArrayList<>();
		
		while(true) {
			System.out.println("====명령어를 입력해 주세요====");
			
			String command = sc.nextLine();
			command = command.trim();
			
			if (command.length() == 0) {
				continue;
			}
			if (command.equals("exit")) {
				break;
			}
			
			//article write 시작 
			if (command.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				
				System.out.println("제목을 입력해 주세요 :");
				String title = sc.nextLine();
				
				System.out.println("내용을 입력해 주세요 :");
				String body = sc.nextLine();
				
				Article article = new Article(id, title, body);
				articles.add(article);
				
				System.out.println(id + " 번 글이 생성되었습니다.");
			}else if (command.equals("article list")) {
				
				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				
				System.out.println("====LIST====");
				
				for (int i = articles.size() - 1; i >= 0; i--) {
 					Article article = articles.get(i);

 					System.out.println("번호 : " + article.id + " | 제목 : " + article.title);
 				}
				
			}else {
				System.out.println(command + " 는 존재하지 않는 명령어 입니다.");
			}//article write 끝 
			
		}//while끝
		
		sc.close();
		
		System.out.println("====프로그램 종료====");
	}//main끝 
}//class main 끝


//==============class==============
class Article {
	int id;
	String title;
	String body;
	
	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
}// class article