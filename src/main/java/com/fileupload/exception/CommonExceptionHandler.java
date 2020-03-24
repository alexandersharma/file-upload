package com.fileupload.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

	@SuppressWarnings({ "unchecked", "rawtypes" })
    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {

        HttpStatus status = getStatus(request);
        return new ResponseEntity(new CustomError("0x000123", "Attachment size exceeds the allowable limit! (10MB)"), status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.valueOf(statusCode);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(InvalidArgumentException.class)
    @ResponseBody
    ResponseEntity<?> handleInvalidArgumentException(HttpServletRequest request, Throwable ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(InternalServerException.class)
    @ResponseBody
    ResponseEntity<?> handleInternalServerException(HttpServletRequest request, Throwable ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
