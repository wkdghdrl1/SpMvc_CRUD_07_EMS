package com.biz.ems.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.ems.model.EmailVO;

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
	
	@Select(" SELECT * FROM tbl_ems WHERE ems_to_email LIKE '%' ||  #{ems_to_email} || '%' ")
	public List<EmailVO> findByToEmail(String search);
	
	@Select(" SELECT * FROM tbl_ems WHERE ems_content LIKE '%' || #{ems_content} || '%' ")
	public List<EmailVO> findByContent(String search);
	
	@Select(" SELECT * FROM tbl_ems WHERE ems_subject LIKE '%' || #{ems_subject} || '%' ")
	public List<EmailVO> findBySubject(String search);

	@Select(" SELECT * FROM tbl_ems WHERE ems_from_name LIKE '%' || #{ems_from_name} || '%' ")
	public List<EmailVO> findByFromName(String search);
	
	
	@Select("SELECT * FROM ( SELECT rownum AS rnum, A.* FROM ( "
	         +" SELECT * FROM tbl_ems "
	         + " ORDER BY ems_send_date DESC, ems_send_time DESC ) A ) "
	         + " WHERE rnum BETWEEN #{start} AND #{end} ")
	public List<EmailVO> selectAll(HashMap<String,Object> option); 

    
    @Select(" SELECT COUNT(*) FROM tbl_ems ")
	public int countArticle();
}
