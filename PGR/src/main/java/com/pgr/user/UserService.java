package com.pgr.user;

import java.util.List;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pgr.Const;
import com.pgr.SecurityUtils;
import com.pgr.model.UserDomain;
import com.pgr.model.UserEntity;

@Service
public class UserService {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private SecurityUtils sUtils;

	@Autowired
	private JavaMailSender emailSender;

	public int join(UserEntity p) throws Exception {
		UserEntity check = mapper.selUser(p);

		if (p.getUserEmail().equals("")) { // 아이디(이메일)칸이 비어 있으면 0을 리턴
			return 0;
		}

		// 비밀번호를 암호화
		String salt = sUtils.getSalt();
		String hashPw = sUtils.getHashPw(p.getUserPw(), salt);

		if (check != null) { // 이미 있는 아이디(이메일)이면 1을 리턴
			return 1;
		}
		if (p.getUserPw().equals("")) { // 비밀번호 칸이 비어 있으면 2를 리턴
			return 2;
		}
		if (p.getUserPwRe().equals("")) { // 비밀번호 확인 칸이 비어 있으면 3을 리턴
			return 3;
		}
		if (p.getNickname().equals("")) { // 닉네임 칸이 비어 있으면 4를 리턴
			return 4;
		}
		if (!p.getUserPw().equals(p.getUserPwRe())) { // 비밀번호와 비밀번호 확인 칸의 값이 다르면 5를 리턴
			return 5;
		}
		p.setUserPw(hashPw);

		mapper.insUser(p); // if문에서 하나도 안걸리면 정보를 입력

		return 6; // 회원가입이 성공하면 6를 리턴
	}

	// 1: 로그인 성공, 2: 아이디 없음, 3: 비밀번호가 틀림, 0: 에러
	public int login(UserEntity p, HttpSession hs) throws Exception {
		UserEntity check = mapper.selUser(p);

		if (check == null) {
			return 2;
		}

		if (!BCrypt.checkpw(p.getUserPw(), check.getUserPw())) {
			return 3;
		}

		check.setUserPw(null);
		hs.setAttribute(Const.KEY_LOGINUSER, check);
		return 1;
	}

	public MimeMessage createMessage(UserEntity p) throws Exception {

		MimeMessage message = emailSender.createMimeMessage();

		message.addRecipients(RecipientType.TO, p.getUserEmail());// 보내는 대상
		message.setSubject("PGR 임시 비밀번호가 도착했습니다.");// 제목

		String msg = "";
		msg += "PGR 임시 비밀번호 입니다.";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: blue;'>";
		msg += p.getNickname() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
		msg += "<p>임시 비밀번호 : ";
		msg += p.getUserPw() + "</p></div>";

		message.setText(msg, "utf-8", "html");// 내용
		message.setFrom(new InternetAddress(Const.FROM_ADDRESS));
		System.out.println("이메일 전송 완료");
		return message;

	}

	public int findPw(UserEntity p) throws Exception {
		UserEntity check = mapper.selUser(p);
		// 이메일이 없으면
		if (check == null) {
			return 1;
		}
		// 가입된 닉네임이 아니면
		if (!p.getNickname().equals(check.getNickname())) {
			return 2;
		} else {
			// 임시 비밀번호 생성
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}

			// 비밀번호 변경
			System.out.println("변경 비밀번호  : " + pw);
			p.setUserPw(pw);
			mapper.updateUserPassword(p);

			// 비밀번호 변경 메일 발송
			sendSimpleMessage(p);

			// 비밀번호 암호화 DB저장
			String salt = sUtils.getSalt();
			String hashPw = sUtils.getHashPw(pw, salt);

			p.setUserPw(hashPw);
			mapper.updateUserPassword(p);

			return 3;
		}

	}

	public void sendSimpleMessage(UserEntity p) throws Exception {
		MimeMessage message = createMessage(p);
		try {// 예외처리
			emailSender.send(message);
		} catch (MailException es) {
			es.printStackTrace();
			throw new IllegalArgumentException();
		}

	}

	public int profileChange(UserDomain p, HttpSession hs) throws Exception {
		UserEntity check = mapper.selUser(p);
		
		if (p.getNickname().equals("")) {
			return 0;
		}
		if (!BCrypt.checkpw(p.getUserPw(), check.getUserPw())) {
			return 1;
		}
		if (p.getUserNewPw().equals("")) {
			return 2;
		}
		if (!p.getUserNewPw().equals(p.getUserPwRe())) {
			return 3;
		}

		String salt = sUtils.getSalt();
		String hashPw = sUtils.getHashPw(p.getUserNewPw(), salt);

		p.setUserPw(hashPw);
		
		check.setNickname(p.getNickname());
		check.setUserPw(p.getUserPw());
		
		hs.setAttribute(Const.KEY_LOGINUSER, check);
		
		mapper.profileChange(p);
		return 4;
	}
	
	public List<UserEntity> selTopUser() {
		return mapper.selTopUser();
	}
}
