package com.his.dao;

// @author ndanhkhoi
import java.sql.Types;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TaiKhoanDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public Map<String, Object> get(String TAIKHOAN) {
		final String query = "CALL `TT_QUANTRI`(?); ";
		Object[] params = new Object[]{TAIKHOAN};
		int[] types = new int[]{Types.VARCHAR};
		return jdbcTemplate.queryForMap(query, params, types);
	}

}
