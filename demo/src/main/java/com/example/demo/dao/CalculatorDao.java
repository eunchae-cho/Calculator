package com.example.demo.dao;

import java.util.List;
import java.util.Map;


public interface CalculatorDao {
    List<Map<String, Object>> findAll()throws Exception;
    Map<String, Object> findByNo(int no) throws Exception;
    List<Map<String, Object>> findToday()throws Exception;
    int delete(int no) throws Exception;
    int add(Map<String, Object> map)throws Exception;
}
