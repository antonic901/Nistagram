package nistagram.postservice.controller;

import java.io.File;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "api/upload")
public class FileUploadConroller {
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		String[] split = file.getOriginalFilename().split("/");
		String saveTo = Paths.get(System.getProperty("user.dir")).getParent().getParent().toString() + "/frontend/src/assets/" + split[0];
		File directory = new File(saveTo);
		if(!directory.exists()) {
			directory.mkdir();
		}
		try {
			file.transferTo(new File(saveTo + "/" + split[1]));
		} catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>("failed", HttpStatus.OK);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
}
