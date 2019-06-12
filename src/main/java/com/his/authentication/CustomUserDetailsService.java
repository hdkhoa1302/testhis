package com.his.authentication;

// @author ndanhkhoi

import com.his.dao.TaiKhoanDAO;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * Khi người dùng đăng nhập thì Spring Security sẽ cần lấy các thông tin UserDetails hiện có để kiểm tra. 
 *  Vì vậy, cần tạo ra một class kế thừa lớp UserDetailsService mà Spring Security cung cấp để làm nhiệm vụ này.
*/
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private TaiKhoanDAO taiKhoanDAO;

	@Override
	public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
		Map<String, Object> user =  taiKhoanDAO.get(string);
		return new CustomUserDetails(user);
	}

}
