package com.controller;

import com.service.JSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class JSController {

    private JSService jsService;

    @Autowired
    public JSController(JSService jsService) {
        this.jsService = jsService;
    }

    @RequestMapping("/*.js")
    public String getJSFileContent(HttpServletRequest request) {
        String fileName = request.getRequestURI().substring(1);
        return jsService.getJSContent(fileName);
    }
}
