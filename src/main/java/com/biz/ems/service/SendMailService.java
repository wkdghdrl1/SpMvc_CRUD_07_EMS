package com.biz.ems.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.biz.ems.mapper.EmailDao;
import com.biz.ems.model.EmailVO;

@Service
public class SendMailService {
	
		@Autowired
		JavaMailSender xMail;
		
		@Autowired
		EmailDao eDao;
		
		@Autowired
		ServletContext context;
		
		private final String uploadFolder = "c:/bizwork/upload/";
		
		public void sendMail(EmailVO mailVO) {
			
			String from_eamil = mailVO.getEms_from_email();
			String to_email = mailVO.getEms_to_email(); 
			String subject = mailVO.getEms_subject();
			String content = mailVO.getEms_content();
			String file1 = mailVO.getEms_file1();
			String file2 = mailVO.getEms_file2();
			
			// Email에 사용되는 문서구조 생성 : MimeMessage라고 한다.
			MimeMessage message = xMail.createMimeMessage();
			
			// 메일을 보낼 때 사용할 보조도구 선언
			MimeMessageHelper mHelper;
			
			try {
				mHelper = new MimeMessageHelper(message, true, "UTF-8");
				mHelper.setFrom(from_eamil);
				mHelper.setTo(to_email);
				mHelper.setSubject(subject);
				
				
				// 2번째 항목을
				// true 하면 HTML방식으로 메일보내기
				// 생략하거나 false하면 text방식으로 메일보내기
				mHelper.setText(content, true);
				
				
				//서버의 저장소에 저장된 파일을
				// 메일에 첨부할 수 있도록 변환하는 과정
				FileSystemResource fr = null;
				if(!file1.isEmpty()) {
				
					fr = new FileSystemResource(new File(uploadFolder, file1));
				// fr 파일리소스를 fiel1의 이름으로 첨부하겠다. 
				mHelper.addAttachment(file1, fr);
				}
				
				if(!file2.isEmpty()) {
				
					fr = new FileSystemResource(new File(uploadFolder, file2));
				// fr 파일리소스를 fiel1의 이름으로 첨부하겠다. 
				mHelper.addAttachment(file2, fr);
				}

				xMail.send(message);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		public int insert(EmailVO emailVO) {
			
			int ret = eDao.insert(emailVO);
			return ret;
		}


		public EmailVO findBySeq(long ems_seq) {
			EmailVO emailVO = eDao.findBySeq(ems_seq);
			return emailVO;
		}

		public int delete(long ems_seq) {
			int ret = eDao.delete(ems_seq);
			return ret;
		}

		public int update(EmailVO emailVO) {
			int ret = eDao.update(emailVO);
			return 0;
		}

		public List<EmailVO> findByFromName(String search) {
			List<EmailVO> emailList = eDao.findByFromName(search);
			return emailList;
		}

		public List<EmailVO> findBySubject(String search) {
			List<EmailVO> emailList = eDao.findBySubject(search);
			return emailList;
		}

		public List<EmailVO> findByContent(String search) {
			List<EmailVO> emailList = eDao.findByContent(search);
			return emailList;
		}

		public List<EmailVO> findByToEmail(String search) {
			List<EmailVO> emailList = eDao.findByToEmail(search);
			return emailList;	
		}



		public List<EmailVO> selectAll(HashMap<String,Object> option) {
			List<EmailVO> lists = eDao.selectAll(option);
			return lists;
		}

		public int countArticle() {
			// TODO Auto-generated method stub
			return eDao.countArticle();
		}


}
