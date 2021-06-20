package nistagram.postservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nistagram.postservice.service.StorageService;

@RestController
@RequestMapping(value = "api/upload")
public class FileUploadController {
	
	private StorageService storageService;
	
	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		return new ResponseEntity<String>(storageService.uploadFile(file), HttpStatus.OK);
		
		
		
		
		
		
		
		
		
		
//		String[] split = file.getOriginalFilename().split("/");
//		String saveTo = Paths.get(System.getProperty("user.dir")).getParent().getParent().toString() + "/frontend/src/assets/" + split[0];
//		File directory = new File(saveTo);
//		if(!directory.exists()) {
//			directory.mkdir();
//		}
//		try {
//			file.transferTo(new File(saveTo + "/" + split[1]));
//		} catch(Exception e) {
//			System.out.println(e);
//			return new ResponseEntity<String>("failed", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
}
