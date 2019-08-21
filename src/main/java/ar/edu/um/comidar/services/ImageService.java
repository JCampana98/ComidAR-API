package ar.edu.um.comidar.services;

import java.io.IOException;
import java.io.InputStream;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DeleteResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.GetTemporaryLinkResult;
import com.dropbox.core.v2.files.UploadErrorException;

@org.springframework.stereotype.Service
public class ImageService {
	
    private static final String ACCESS_TOKEN = "Mt5grMVKopAAAAAAAAAAVTnhikFaKMyvzJRsxm8mzOTbXVI5iY9jYhHAeLGmbhrn";

	// Create Dropbox client
    DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
	DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
	
	public void uploadImage(InputStream image, String imageName) throws UploadErrorException, DbxException, IOException {
		FileMetadata metadata = client.files()
		    	.uploadBuilder(imageName)
		        .uploadAndFinish(image);
	}
	
	public void deleteImage(String url) throws UploadErrorException, DbxException, IOException {
			DeleteResult metadata = client.files()
				.deleteV2(url);
	}
	
	public String getImageURL(String url) throws UploadErrorException, DbxException, IOException {
		String link = client.files()
			    	.getTemporaryLink(url).getLink();
		return link;
	    
	}

}
