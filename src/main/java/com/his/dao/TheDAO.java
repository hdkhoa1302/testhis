package com.his.dao;

// @author ndanhkhoi
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TheDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public List< Map<String, Object>> getAll() {
		final String query = "CALL `DS_THE`(); ";
		return jdbcTemplate.queryForList(query);
	}

	@Transactional
	public int write(long MAYTE) {
		final String query = "CALL `CAP_THE`(?); ";
		Object[] params = new Object[]{MAYTE};
		int[] types = new int[]{
			Types.BIGINT
		};
		return jdbcTemplate.update(query, params, types);
	}

	@Transactional
	public Map<String, Object> read(long MATHE) {
		final String query = "CALL `DOC_THE`(?); ";
		Object[] params = new Object[]{MATHE};
		int[] types = new int[]{Types.BIGINT};
		try {
			return jdbcTemplate.queryForMap(query, params, types);
		} catch (EmptyResultDataAccessException | DataIntegrityViolationException ex) {
			return new HashMap<>();
		}
	}

	@Transactional
	public List< Map<String, Object>> history(long MATHE) {
		final String query = "CALL `LICHSU_THE`(?); ";
		Object[] params = new Object[]{MATHE};
		int[] types = new int[]{Types.BIGINT};
		return jdbcTemplate.queryForList(query, params, types);
	}

	@Transactional
	public List< Map<String, Object>> statistic(long NAM) {
		final String query = "CALL `THONGKE_THE`(?); ";
		Object[] params = new Object[]{NAM};
		int[] types = new int[]{Types.INTEGER};
		return jdbcTemplate.queryForList(query, params, types);
	}

	@Transactional
	public Map<String, Object> info(long MATHE) {
		final String query = "CALL `TT_THE`(?); ";
		Object[] params = new Object[]{MATHE};
		int[] types = new int[]{Types.BIGINT};
		try {
			return jdbcTemplate.queryForMap(query, params, types);
		} catch (EmptyResultDataAccessException ex) {
			return new HashMap<>();
		}
	}

	@Transactional
	public int disabled(Object[] params) {
		final String query = "CALL `VOHIEU_THE`(?, ?); ";
		int[] types = new int[]{
			Types.INTEGER,
			Types.VARCHAR
		};
		return jdbcTemplate.update(query, params, types);
	}

}
