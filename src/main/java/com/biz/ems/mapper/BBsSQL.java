package com.biz.ems.mapper;

import java.util.HashMap;

import org.apache.ibatis.jdbc.SQL;

public class BBsSQL {
	
	public String bbs_select_count_sql(final HashMap<String, String> option) {
		
		SQL sql = new SQL() {{
			SELECT("count(*)");
			FROM("tbl_ems");
			if(option.get("search_option").equalsIgnoreCase("title")) {
				WHERE("ems_subject like #{keyword}");
			} else if (option.get("search_option").equalsIgnoreCase("writer")) {
				WHERE("ems_to_email like #{keyword}");
			}else if (option.get("search_option").equalsIgnoreCase("from_name")) {
				WHERE("ems_from_name like #{keyword}");
			}
			else if (option.get("search_option").equalsIgnoreCase("content")) {
				WHERE("ems_content like #{keyword}");
			} else {
				WHERE("ems_to_email LIKE #{keyword}") ;
				OR();
				WHERE("ems_subject LIKE #{keyword}");
				OR();
				WHERE("ems_content LIKE #{keyword}");
			}
		}};
		return sql.toString();
	}

	

	
	public String bbs_list_all(final HashMap<String, Object> option) {
		
		final SQL main_sql = new SQL() {{
			SELECT("*");
			FROM(" tbl_ems ");
			ORDER_BY("ems_send_date DESC, ems_send_time DESC") ;
			if(((String) option.get("search_option")).equalsIgnoreCase("title")) {
				WHERE("ems_subject like #{keyword}");
			}else if (((String) option.get("search_option")).equalsIgnoreCase("from_name")) {
				WHERE("ems_from_name like #{keyword}");
			}else if (((String) option.get("search_option")).equalsIgnoreCase("to_email")) {
				WHERE("ems_to_email like #{keyword}");
			}else if (((String) option.get("search_option")).equalsIgnoreCase("content")) {
				WHERE("ems_content like #{keyword}");
			} else {
				WHERE("ems_to_email LIKE #{keyword}") ;
				OR();
				WHERE("ems_subject LIKE #{keyword}");
				OR();
				WHERE("ems_content LIKE #{keyword}");
			}
		}};
		
		SQL sql = new SQL() {{
			SELECT("*");
			FROM("( SELECT rownum AS rnum, A.* FROM( " + main_sql.toString() + ") A )") ;
			WHERE("rnum BETWEEN #{start} AND #{end}");
			
		}};
		return sql.toString();
		
	}
	
	
}

