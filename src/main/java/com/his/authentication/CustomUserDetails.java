package com.his.authentication;

// @author ndanhkhoi
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*
* Mặc định Spring Security sử dụng một đối tượng UserDetails để chứa toàn bộ thông tin về người dùng. 
* Vì vậy, chúng ta cần tạo ra một class mới giúp chuyển các thông tin của User thành UserDetails
*/

public class CustomUserDetails implements UserDetails {

	private final Map<String, Object> user;

	public CustomUserDetails(Map<String, Object> user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Mặc định sẽ để tất cả là ROLE_USER. Để cho đơn giản.
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public String getPassword() {
		return user.get("MATKHAU").toString();
	}

	@Override
	public String getUsername() {
		return user.get("TAIKHOAN").toString();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
