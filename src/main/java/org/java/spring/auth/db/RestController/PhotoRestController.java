package org.java.spring.auth.db.RestController;

import java.util.List;

import org.java.spring.pojo.Photo;
import org.java.spring.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/photos/")
public class PhotoRestController {
	
	@Autowired
	private PhotoService photoServ;
	
	@GetMapping
	public ResponseEntity<List<Photo>> routeIndex(){
		
		List<Photo> photos = photoServ.findAllVisible();
		
		return new ResponseEntity<>(photos, HttpStatus.OK);
		
	}

}
