package com.his.dao;

// @author ndanhkhoi
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BenhNhanDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public List< Map<String, Object>> getAll() {
		final String query = "CALL `DS_BENHNHAN`(); ";
		return jdbcTemplate.queryForList(query);
	}

	@Transactional
	public Map<String, Object> get(long MAYTE) {
		final String query = "CALL `TT_BENHNHAN`(?); ";
		Object[] params = new Object[]{MAYTE};
		int[] types = new int[]{Types.BIGINT};
		try {
			return jdbcTemplate.queryForMap(query, params, types);
		} catch (EmptyResultDataAccessException ex) {
			return new HashMap<>();
		}
	}

	@Transactional
	public int add(Object[] params) {
		final String query = "CALL `THEM_BENHNHAN`(?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
		int[] types = new int[]{
			Types.SMALLINT,
			Types.INTEGER,
			Types.INTEGER,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.DATE,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.VARCHAR
		};
		return jdbcTemplate.update(query, params, types);
	}

	@Transactional
	public int update(Object[] params) {
		final String query = "CALL `CAPNHAT_BENHNHAN`(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
		int[] types = new int[]{
			Types.SMALLINT,
			Types.INTEGER,
			Types.INTEGER,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.DATE,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.BIGINT
		};
		return jdbcTemplate.update(query, params, types);
	}


}
