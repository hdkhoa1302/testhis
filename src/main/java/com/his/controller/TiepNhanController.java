package com.his.controller;

// @author ndanhkhoi
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.his.dao.TiepNhanDAO;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tiepnhan")
public class TiepNhanController {
	
	@Autowired 
	private TiepNhanDAO tiepNhanDAO;
	
	private final ObjectMapper mapper = new ObjectMapper();

	@RequestMapping("/all")
	public ResponseEntity getAll() {
		return new ResponseEntity(tiepNhanDAO.getAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@RequestMapping("/add")
	public ResponseEntity add(@RequestBody Map<String, Object> tiepNhan) {
		Object[] params = new Object[]{
			tiepNhan.get("NGAYTIEPNHAN").toString(),
			tiepNhan.get("STTBATSO").toString(),
			tiepNhan.get("MAYTE").toString()
		};
		int rowAffected = tiepNhanDAO.add(params);
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("Row affected", rowAffected);
		return new ResponseEntity(objectNode, HttpStatus.OK);
	}
	
	@DeleteMapping
	@RequestMapping("/delete")
	public ResponseEntity delete(@RequestBody Map<String, Object> tiepNhan)
	{
		Object[] params = new Object[]{
			tiepNhan.get("NGAYTIEPNHAN").toString(),
			tiepNhan.get("STTBATSO").toString()
		};
		int rowAffected = tiepNhanDAO.delete(params);
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("Row affected", rowAffected);
		return new ResponseEntity(objectNode, HttpStatus.OK);
	}
	
	@PostMapping
	@RequestMapping("/history")
	public ResponseEntity history(@RequestBody Map<String, Object> object){
		Object[] params = new Object[]{
			object.get("MAYTE").toString(),
			object.get("MAXA").toString(),
			object.get("MAHUYEN").toString(),
			object.get("MATINH").toString()
		};
		System.out.println(params);
		return new ResponseEntity(tiepNhanDAO.history(params), HttpStatus.OK);
	}

}
