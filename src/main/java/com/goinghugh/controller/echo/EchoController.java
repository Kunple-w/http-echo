package com.goinghugh.controller.echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 回声
 *
 * @author yongxu wang
 * @date 2019-05-16 10:04
 **/
@RestController
@RequestMapping
public class EchoController {

    private static final Logger logger = LoggerFactory.getLogger(EchoController.class);

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/**", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
    public ResponseEntity<Map<String, Object>> echoBack(@RequestBody(required = false) String rawBody) {

        Map<String, String> headers = new HashMap<>();
        for (String headerName : Collections.list(request.getHeaderNames())) {
            headers.put(headerName, request.getHeader(headerName));
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("protocol", request.getProtocol());
        responseMap.put("method", request.getMethod());
        responseMap.put("headers", headers);
        responseMap.put("cookies", request.getCookies());
        responseMap.put("parameters", request.getParameterMap());
        responseMap.put("path", request.getServletPath());
        responseMap.put("body", rawBody);
        logger.info("REQUEST: {}", request.getParameterMap());

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }
}
