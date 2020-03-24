package com.fileupload.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.exception.InternalServerException;
import com.fileupload.exception.InvalidArgumentException;
import com.fileupload.model.Notification;
import com.fileupload.services.NotificationService;

@RestController
public class FileUploadController {

    private static final String INTERNAL_SERVER_ERROR = "Internal server error..!!";

	private static final String SINGLE_FILE_UPLOAD = "Single file upload!";

	private static final String MULTIPLE_FILE_UPLOAD = "Multiple file upload!";

	private static final String PLEASE_SELECT_A_FILE = "Please select a file!";

	private static final String SUCCESSFULLY_UPLOADED = "Successfully uploaded - ";

	private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    private static String UPLOADED_FOLDER = "/files/";
    
    @Autowired
    private NotificationService notificationService;


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/api/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {
        logger.debug(SINGLE_FILE_UPLOAD);
        validateFileContent(uploadfile);
        saveUploadedFiles(Arrays.asList(uploadfile));
        notificationService.notify(new Notification(SUCCESSFULLY_UPLOADED + uploadfile.getOriginalFilename()));
        return new ResponseEntity(SUCCESSFULLY_UPLOADED + uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }


	private void validateFileContent(MultipartFile uploadfile) {
		if (uploadfile.isEmpty()) {
			throw new InvalidArgumentException(PLEASE_SELECT_A_FILE);
        }
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping("/api/upload/multi")
    public ResponseEntity<?> uploadFileMulti(@RequestParam("files") MultipartFile[] uploadfiles) {

        logger.debug(MULTIPLE_FILE_UPLOAD);

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        validateFiles(uploadedFileName);
        saveUploadedFiles(Arrays.asList(uploadfiles));
        notificationService.notify(new Notification(SUCCESSFULLY_UPLOADED + uploadedFileName));
        return new ResponseEntity(SUCCESSFULLY_UPLOADED + uploadedFileName, HttpStatus.OK);

    }
    
    private void validateFiles(String uploadedFileName) {
		if (StringUtils.isEmpty(uploadedFileName)) {
			throw new InvalidArgumentException(PLEASE_SELECT_A_FILE);
        }
	}

	private void saveUploadedFiles(List<MultipartFile> files) {
    	try {
    		for (MultipartFile file : files) {

                if (file.isEmpty()) {
                    continue; //next pls
                }
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
            }
    	} catch (IOException e) {
    		throw new InternalServerException(INTERNAL_SERVER_ERROR);
        }
    }
}
