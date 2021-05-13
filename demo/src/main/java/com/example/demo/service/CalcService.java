package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface CalcService {
    List<Map<String, Object>> list() throws Exception;
    List<Map<String, Object>> listToday() throws Exception;
    Map<String, Object> oneByNo(int no) throws Exception;
    int delete(int no) throws Exception;
    int add(Map<String, Object> map) throws Exception;
}