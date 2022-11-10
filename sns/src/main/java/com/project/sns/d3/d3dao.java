package com.project.sns.d3;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class d3dao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public List<Map<String, Object>> segu_select(Map<String, Object> map) {
		System.out.println("segu3");
		return this.sqlSessionTemplate.selectList("d3.segu_select", map);
	}

	public List<Map<String, Object>> susunggu_select(Map<String, Object> map) {
		System.out.println("susunggu3");
		return this.sqlSessionTemplate.selectList("d3.susunggu_select", map);
	}
}