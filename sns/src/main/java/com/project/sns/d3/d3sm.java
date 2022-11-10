package com.project.sns.d3;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class d3sm implements d3s {
	@Autowired
	d3dao d3dao;

	@Override
	public List<Map<String, Object>> segu_select(Map<String, Object> map) {
		System.out.println("segu2");
		return this.d3dao.segu_select(map);
	}

	@Override
	public List<Map<String, Object>> susunggu_select(Map<String, Object> map) {
		System.out.println("susunggu2");
		return this.d3dao.susunggu_select(map);
	}
}