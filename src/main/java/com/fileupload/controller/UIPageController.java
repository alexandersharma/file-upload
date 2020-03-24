package com.fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIPageController {

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }
    
    @GetMapping("/")
    public String index() {
        return "upload";
    }
    
    @GetMapping("/notifications")
    public String notifications() {
        return "notifications";
    }

}