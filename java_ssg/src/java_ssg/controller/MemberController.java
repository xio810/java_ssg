package java_ssg.controller;

import java.util.List;
import java.util.Scanner;

import java_ssg.dto.Member;
import java_ssg.util.Util;

public class MemberController {
	private Scanner sc;
	private List<Member> members;

	public MemberController(Scanner sc, List<Member> members) {
		this.sc = sc;
		this.members = members;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		
		for (Member member : members) {
			if(member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}
		return false;
	}

	public void doJoin() {

		int id = members.size() + 1;

		String regDate = Util.getNowDateStr();
		String loginId = null;

		while (true) {
			System.out.println("아이디를 입력해 주세요 :");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
				System.out.println("이미 사용중인 아이디 입니다.");
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {

			System.out.println("비밀번호를 입력해 주세요 :");
			loginPw = sc.nextLine();
			System.out.println("비밀번호를 다시한번 입력해주세요 :");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해 주세요.");
				continue;
			}
			break;

		}

		System.out.println("이름을 입력해 주세요 :");
		String name = sc.nextLine();

		// member class
		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.println(id + " 번 회원이 생성되었습니다.");

	}

}
