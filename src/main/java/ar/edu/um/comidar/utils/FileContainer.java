package ar.edu.um.comidar.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileContainer {
	private MultipartFile file;
	
	public String getExtension() {
		return FilenameUtils.getExtension(this.file.getOriginalFilename());
	}
	
}
