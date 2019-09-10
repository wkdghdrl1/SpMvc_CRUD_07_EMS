package com.biz.ems.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.biz.ems.model.EmailVO;
import com.biz.ems.service.FileService;
import com.biz.ems.service.Pager;
import com.biz.ems.service.SendMailService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping(value="/ems")
public class EmsController {
	
	@Autowired
	SendMailService xMailService;
	
	@Autowired
	FileService fService;
	
	@ModelAttribute("emailVO")
	public EmailVO newEamilVO() {
		return new EmailVO();
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String list(
						@RequestParam(defaultValue = "1")int curPage, 
						@RequestParam(defaultValue = "") String keyword,
						@RequestParam(defaultValue = "all") String search_option,
														Model model
						) {
			
			HashMap<String, String> s_option = new HashMap<String, String>();
			s_option.put("search_option", search_option);
			s_option.put("keyword", keyword);
			int count = xMailService.countArticle(s_option);
			Pager pager = new Pager(count,curPage);
			int start = pager.getPageBegin();
			int end = pager.getPageEnd();
			HashMap<String,Object> option = new HashMap<>();
			option.put("start", start);
			option.put("end", end);
			option.put("search_option", search_option);
			option.put("keyword", keyword);
		
			List<EmailVO> emailList = xMailService.selectAll(option);
			
			model.addAttribute("keyword", keyword);
			model.addAttribute("search_option", search_option);
			model.addAttribute("pager",pager);
			model.addAttribute("LIST",emailList);
			model.addAttribute("BODY","LIST");
		//??? 
		return "home";
	}
	
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(@ModelAttribute("emailVO")EmailVO emailVO, Model model) {
		
		
		LocalDateTime ldt = LocalDateTime.now();
		String curDate 
		= ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
		
		String curTime
		= ldt.format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString();
		
		
		emailVO.setEms_send_date(curDate);
		emailVO.setEms_send_time(curTime);
		emailVO.setEms_from_email("power374@naver.com");
		emailVO.setEms_from_name("비올레");
		
		model.addAttribute("emailVO", emailVO);
		model.addAttribute("BODY", "WRITE");
		
		return "home";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(
			@ModelAttribute("emailVO") EmailVO emailVO,
			@RequestParam("file1") MultipartFile file1,
			@RequestParam("file2") MultipartFile file2,
			BindingResult result,
			Model model
			) {
		
		String file_name1 = fService.fileUp(file1);
		emailVO.setEms_file1(file_name1);
		String file_name2 = fService.fileUp(file2);
		emailVO.setEms_file2(file_name2);
		if(result.hasErrors()) {
			model.addAttribute("BODY", "WRITE");
			return "home";
		}
		int ret = xMailService.insert(emailVO);
		xMailService.sendMail(emailVO);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam("ems_seq") long ems_seq, Model model) {
		
		EmailVO emailVO = xMailService.findBySeq(ems_seq);
		model.addAttribute("EMAILVO", emailVO);
		model.addAttribute("BODY", "VIEW");
		
		
		return "home";
	}
	
	@RequestMapping(value = "/delete/{ems_seq}", method=RequestMethod.GET)
	public String delete(@PathVariable long ems_seq, Model model) {
		
		int ret = xMailService.delete(ems_seq);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam("ems_seq") long ems_seq, Model model) {
		
		EmailVO emailVO = xMailService.findBySeq(ems_seq);
		model.addAttribute("emailVO", emailVO);
		model.addAttribute("BODY", "WRITE");
		return "home";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute EmailVO emailVO,
			@RequestParam("file1") MultipartFile file1, 
			@RequestParam("file2") MultipartFile file2, 
			Model model) {
		
		String file_name1 = fService.fileUp(file1);
		emailVO.setEms_file1(file_name1);
		String file_name2 = fService.fileUp(file2);
		emailVO.setEms_file2(file_name2);
		int ret = xMailService.update(emailVO);
		xMailService.sendMail(emailVO);
		return "redirect:/";
	}
	

	

}
;