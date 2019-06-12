package com.his.controller;

// @author ndanhkhoi
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.his.dao.BenhNhanDAO;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/benhnhan")
public class BenhNhanController {

	@Autowired
	private BenhNhanDAO benhNhanDAO;

	private final ObjectMapper mapper = new ObjectMapper();

	@RequestMapping("/all")
	public ResponseEntity getAll() {
		return new ResponseEntity(benhNhanDAO.getAll(), HttpStatus.OK);
	}

	@RequestMapping("/{MAYTE}")
	public ResponseEntity getById(@PathVariable long MAYTE) {
		Map<String, Object> benhNhan = benhNhanDAO.get(MAYTE);
		if (!benhNhan.keySet().isEmpty()) {
			return new ResponseEntity(benhNhan, HttpStatus.OK);
		}
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("Error", HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity(objectNode, HttpStatus.NOT_FOUND);
	}

	@PostMapping
	@RequestMapping("/add")
	public ResponseEntity add(@RequestBody Map<String, Object> benhNhan) {
		Object[] params = new Object[]{
			benhNhan.get("MADT").toString(),
			benhNhan.get("MAXA").toString(),
			benhNhan.get("MANN").toString(),
			benhNhan.get("HOTEN").toString(),
			benhNhan.get("CMND").toString(),
			benhNhan.get("NGAYSINH").toString(),
			benhNhan.get("GIOITINH").toString(),
			benhNhan.get("SDT").toString(),
			benhNhan.get("NGUOILH").toString(),
			benhNhan.get("CMNDNLH").toString()
		};
		int rowAffected = benhNhanDAO.add(params);
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("Row affected", rowAffected);
		return new ResponseEntity(objectNode, HttpStatus.OK);
	}

	@PostMapping
	@RequestMapping("/update")
	public ResponseEntity update(@RequestBody Map<String, Object> benhNhan) {
		Object[] params = new Object[]{
			benhNhan.get("MADT").toString(),
			benhNhan.get("MAXA").toString(),
			benhNhan.get("MANN").toString(),
			benhNhan.get("HOTEN").toString(),
			benhNhan.get("CMND").toString(),
			benhNhan.get("NGAYSINH").toString(),
			benhNhan.get("GIOITINH").toString(),
			benhNhan.get("SDT").toString(),
			benhNhan.get("NGUOILH").toString(),
			benhNhan.get("CMNDNLH").toString(),
			benhNhan.get("MAYTE").toString()
		};
		int rowAffected = benhNhanDAO.update(params);
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("Row affected", rowAffected);
		return new ResponseEntity(objectNode, HttpStatus.OK);
	}
	
}
