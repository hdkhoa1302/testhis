package com.his.controller;

// @author ndanhkhoi

import com.his.dao.OthersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/others/")
public class OthersController {
	
	@Autowired
	private OthersDAO othersDAO;
	
	@RequestMapping("/nations")
	public ResponseEntity nations(){
		return new ResponseEntity(othersDAO.dsDanToc(), HttpStatus.OK);
	}

	@RequestMapping("/jobs")
	public ResponseEntity jobs(){
		return new ResponseEntity(othersDAO.dsNgheNghiep(), HttpStatus.OK);
	}

	@RequestMapping("/provinces")
	public ResponseEntity provinces(){
		return new ResponseEntity(othersDAO.dsTinh(), HttpStatus.OK);
	}

	@RequestMapping("/districts/{MATINH}")
	public ResponseEntity districts(@PathVariable int MATINH){
		return new ResponseEntity(othersDAO.dsHuyen(MATINH), HttpStatus.OK);
	}

	@RequestMapping("/wars/{MAHUYEN}")
	public ResponseEntity wars(@PathVariable int MAHUYEN){
		return new ResponseEntity(othersDAO.dsXa(MAHUYEN), HttpStatus.OK);
	}

}
