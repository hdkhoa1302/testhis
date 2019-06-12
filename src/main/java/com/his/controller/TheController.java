package com.his.controller;

// @author ndanhkhoi
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.his.dao.TheDAO;
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
@RequestMapping("/api/the")
public class TheController {

	@Autowired
	private TheDAO theDAO;

	private final ObjectMapper mapper = new ObjectMapper();

	@RequestMapping("/all")
	public ResponseEntity getAll() {
		return new ResponseEntity(theDAO.getAll(), HttpStatus.OK);
	}

	@PostMapping
	@RequestMapping("/write")
	public ResponseEntity write(@RequestBody Map<String, Object> map) {
		long MAYTE = Long.parseLong(map.get("MAYTE").toString());
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("Row affected", theDAO.write(MAYTE));
		return new ResponseEntity(objectNode, HttpStatus.OK);
	}

	@RequestMapping("/read/{MATHE}")
	public ResponseEntity read(@PathVariable long MATHE) {
		Map<String, Object> benhNhan = theDAO.read(MATHE);
		if (!benhNhan.keySet().isEmpty()) {
			return new ResponseEntity(benhNhan, HttpStatus.OK);
		}
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("Error", HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity(objectNode, HttpStatus.NOT_FOUND);
	}

	@RequestMapping("/history/{MATHE}")
	public ResponseEntity history(@PathVariable long MATHE) {
		return new ResponseEntity(theDAO.history(MATHE), HttpStatus.OK);
	}

	@RequestMapping("/statistic/{NAM}")
	public ResponseEntity statistic(@PathVariable long NAM) {
		return new ResponseEntity(theDAO.statistic(NAM), HttpStatus.OK);
	}

	@RequestMapping("/info/{MATHE}")
	public ResponseEntity info(@PathVariable long MATHE) {
		Map<String, Object> benhNhan = theDAO.info(MATHE);
		if (!benhNhan.keySet().isEmpty()) {
			return new ResponseEntity(benhNhan, HttpStatus.OK);
		}
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("Error", HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity(objectNode, HttpStatus.NOT_FOUND);
	}

	@PostMapping
	@RequestMapping("/disabled")
	public ResponseEntity disabled(@RequestBody Map<String, Object> map) {
		Object[] params = new Object[]{
			map.get("MATHE").toString(),
			map.get("LYDO").toString()
		};
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("Row affected", theDAO.disabled(params));
		return new ResponseEntity(objectNode, HttpStatus.OK);
	}
}
