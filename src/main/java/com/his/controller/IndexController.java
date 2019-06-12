package com.his.controller;

// @author ndanhkhoi
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.his.authentication.CustomUserDetails;
import com.his.authentication.JwtTokenProvider;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	private final ObjectMapper mapper = new ObjectMapper();

	@RequestMapping("/")
	public String index() {
		StringBuilder sb = new StringBuilder("<h3 style='text-align: center;'>Server is running now...</h3");
		return new String(sb);
	}

	@RequestMapping("/api/login")
	public ResponseEntity login(
			@Valid
			@RequestBody Map<String, Object> user) {

		// Xác thực thông tin người dùng Request lên
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						user.get("TAIKHOAN").toString(),
						user.get("MATKHAU").toString()
				)
		);

		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Trả về jwt cho người dùng.
		String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("tokenType", "Bearer");
		objectNode.put("accessToken", jwt);
		return new ResponseEntity(objectNode, HttpStatus.OK);
	}

}
