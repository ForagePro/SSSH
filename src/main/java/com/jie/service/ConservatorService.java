package com.jie.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface ConservatorService {
    public List<Map> toLogin(String account, String password, HttpServletRequest request, String code);
}
