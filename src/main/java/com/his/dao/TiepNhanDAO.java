package com.his.dao;

// @author ndanhkhoi

import java.sql.Types;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class TiepNhanDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	public List< Map<String, Object>> getAll() {
		final String query = "CALL `DS_TIEPNHAN`(); ";
		return jdbcTemplate.queryForList(query);
	}
	
	@Transactional
	public int add(Object[] params){
		final String query = "CALL `THEM_TIEPNHAN`(?, ?, ?); ";
		int[] types = new int[]{
			Types.DATE,
			Types.BIGINT,
			Types.BIGINT
		};
		return jdbcTemplate.update(query, params, types);
	}
	
	@Transactional
	public int delete(Object[] params){
		final String query = "CALL `XOA_TIEPNHAN`(?,  ?); ";
		int[] types = new int[]{
			Types.DATE,
			Types.BIGINT
		};
		return jdbcTemplate.update(query, params, types);
	}
	
	@Transactional
	public List< Map<String, Object>> history(Object[] params) {
		for (int i = 0; i<params.length; i++)
		{
			Object param = params[i];
			if (param.toString().length() == 0) params[i] = Types.NULL;
		}
		final String query = "CALL `LICHSU_KHAM`(?, ?, ?, ?); ";
		int[] types = new int[]{
			Types.BIGINT,
			Types.INTEGER,
			Types.INTEGER,
			Types.INTEGER
		};
		return jdbcTemplate.queryForList(query, params, types);
	}
	
	
}
