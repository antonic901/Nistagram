package nistagram.postservice.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
	String uploadFile(MultipartFile file);
	byte[] downloadFile(String fileName);
	String deleteFile(String fileName);
}