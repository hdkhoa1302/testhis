package com.his.dao;

// @author ndanhkhoi

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class OthersDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	public List<Map<String, Object>> dsDanToc(){
		final String query = "CALL `DS_DANTOC`(); ";
		return jdbcTemplate.queryForList(query);
	}

	@Transactional
	public List<Map<String, Object>> dsNgheNghiep(){
		final String query = "CALL `DS_NGHENGHIEP`(); ";
		return jdbcTemplate.queryForList(query);
	}

	@Transactional
	public List<Map<String, Object>> dsTinh(){
		final String query = "CALL `DS_TINH`(); ";
		return jdbcTemplate.queryForList(query);
	}

	@Transactional
	public List<Map<String, Object>> dsHuyen(int MATINH){
		final String query = "CALL `DS_HUYEN`(?); ";
		Object[] params = new Object[]{MATINH};
		int[] types = new int[]{Types.INTEGER};
		return jdbcTemplate.queryForList(query, params, types);
	}
	
	@Transactional
	public List<Map<String, Object>> dsXa(int MAHUYEN){
		final String query = "CALL `DS_XA`(?); ";
		Object[] params = new Object[]{MAHUYEN};
		int[] types = new int[]{Types.INTEGER};
		return jdbcTemplate.queryForList(query, params, types);
	}

}
