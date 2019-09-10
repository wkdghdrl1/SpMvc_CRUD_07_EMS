package com.biz.ems.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.biz.ems.model.EmailVO;

public interface BBsDao {

	public void write(EmailVO emailVO); //게시글 등록
		public void update(EmailVO emailVO);//게시글 수정
		public void delete(int bno);	  // 게시글 삭제
		public EmailVO read(int bno); 	  //상세게시글(1건)
		
		// 게시글 조회시 필요한 메서드(페이지네이션)
		@SelectProvider(type=BBsSQL.class,method="bbs_list_all")
		public List<EmailVO> listAll(HashMap<String, Object> option); // 게시글 목록
		
		@SelectProvider(type=BBsSQL.class,method="bbs_select_count_sql")
		public int countArticle(@Param("search_option") String search_option,@Param("keyword")  String keyword); //레코드 개수 계산
		
}
