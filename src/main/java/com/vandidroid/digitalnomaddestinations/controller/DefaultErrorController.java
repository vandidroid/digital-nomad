package com.vandidroid.digitalnomaddestinations.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Hidden
@RestController
@RequestMapping("/error")
public class DefaultErrorController implements ErrorController {
    @RequestMapping("")
    public ErrorObject handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Integer statusCode = Integer.valueOf(status.toString());

        return new ErrorObject(statusCode);
    }

    @Data
    class ErrorObject {
        private final Integer statusCode;
        private String message;

        public ErrorObject(Integer statusCode) {
            this.statusCode = statusCode;
            this.message = HttpStatus.getStatusText(statusCode);
        }
    }
}
