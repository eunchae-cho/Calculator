package com.example.demo.dao;

import java.util.List;
import java.util.Map;


public interface CalculatorDao {
    List<Map<String, String>> findAll()throws Exception;
}
