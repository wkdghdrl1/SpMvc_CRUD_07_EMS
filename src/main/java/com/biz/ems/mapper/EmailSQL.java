package com.biz.ems.mapper;

import org.apache.ibatis.jdbc.SQL;

public class EmailSQL {
			
	public String email_insert_sql() {
		
		SQL sql = new SQL() {{
		INSERT_INTO("tbl_ems");
		INTO_COLUMNS("EMS_SEQ").INTO_VALUES("SEQ_EMS.NEXTVAL");
		INTO_COLUMNS("EMS_TO_EMAIL").INTO_VALUES("#{ems_to_email}");
		INTO_COLUMNS("EMS_FROM_EMAIL").INTO_VALUES("#{ems_from_email}");
		INTO_COLUMNS("EMS_TO_NAME").INTO_VALUES("#{ems_to_name}");
		INTO_COLUMNS("EMS_FROM_NAME").INTO_VALUES("#{ems_from_name}");
		INTO_COLUMNS("EMS_SEND_DATE").INTO_VALUES("#{ems_send_date}");
		INTO_COLUMNS("EMS_SEND_TIME").INTO_VALUES("#{ems_send_time}");
		INTO_COLUMNS("EMS_SUBJECT").INTO_VALUES("#{ems_subject}");
		INTO_COLUMNS("EMS_CONTENT").INTO_VALUES("#{ems_content}");
		INTO_COLUMNS("EMS_FILE1").INTO_VALUES("#{ems_file1}");
		INTO_COLUMNS("EMS_FILE2").INTO_VALUES("#{ems_file2}");
		
		}};
			return sql.toString();	
	}
	
	
	
	public String email_update_sql() {
		
		SQL sql = new SQL() {{
		UPDATE("tbl_ems");
		SET("ems_to_email = #{ems_to_email}");
		SET("ems_from_email = #{ems_from_email}" );
		SET("ems_to_name = #{ems_to_name}");
		SET("ems_from_name = #{ems_from_name}");
		SET("ems_send_date = #{ems_send_date}");
		SET("ems_send_time = #{ems_send_time}");
		SET("ems_subject = #{ems_subject}");
		SET("ems_content = #{ems_content}");
		SET("ems_file1 = #{ems_file1}");
		SET("ems_file2 = #{ems_file2}");
		WHERE("ems_seq = #{ems_seq}");
		}};
				
		return sql.toString();
	}
}
