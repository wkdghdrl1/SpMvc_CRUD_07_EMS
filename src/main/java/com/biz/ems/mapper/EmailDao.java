package com.biz.ems.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.ems.model.EmailVO;

import lombok.extern.slf4j.Slf4j;

public interface EmailDao {
	
	@Select(" SELECT * FROM tbl_ems WHERE ems_seq = #{ems_seq} ")
	public EmailVO findBySeq(long ems_seq);
	
	public List<EmailVO> fileByFrom(String ems_from_email);
	public List<EmailVO> fileByTo(String ems_to_email);
	
	public List<EmailVO> fileByFromAndTo(
			@Param("ems_from_email")String ems_from_email, 
			@Param("ems_from_email")String ems_to_email);
	/*
	 *  매개변수가 2개 이상일 경우는
	 *  반드시 @Param으로 변수 이름을 명시해주어야 한다.
	 * 
	 */
	
	@InsertProvider(type = EmailSQL.class , method = "email_insert_sql")
	public int insert(EmailVO emailVO);
	
	@UpdateProvider(type = EmailSQL.class, method = "email_update_sql")
	public int update(EmailVO emailVO);
	
	@Delete(" DELETE FROM tbl_ems WHERE ems_seq = #{ems_seq} ")
	public int delete(long ems_seq);
	
	
	@SelectProvider(type=EmailSQL.class,method="bbs_list_all")
	public List<EmailVO> selectAll(HashMap<String,Object> option); 


	
    @SelectProvider(type=EmailSQL.class,method="bbs_select_count_sql")
	public int countArticle(HashMap<String, String> s_option);
}
