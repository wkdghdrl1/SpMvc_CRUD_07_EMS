package com.biz.ems.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.ems.model.EmailVO;
import com.biz.ems.model.PagingVO;
import com.biz.ems.service.SendMailService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	SendMailService xMailService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		List<EmailVO> emailList = xMailService.selectAll();
		model.addAttribute("LIST", emailList);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	  @RequestMapping(value="/paging.do", method={RequestMethod.POST,RequestMethod.GET})
	    public String paging(Model model, PagingVO pagingVO){
	        logger.info("paging : " + pagingVO);
	        List<EmailVO> lists = xMailService.selectPaging(pagingVO);
	        pagingVO.setTotal(xMailService.selectTotalPaging());
	        model.addAttribute("lists", lists);
	        model.addAttribute("p", pagingVO);
	        return "paging";
	    }
	    
	
}
