package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import com.example.demo.dao.CalculatorDao;
import com.example.demo.service.CalcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCalcService implements CalcService{
    @Autowired CalculatorDao calculatorDao;

    @Autowired
    public DefaultCalcService(CalculatorDao calculatorDao){
        this.calculatorDao = calculatorDao;
    }

    @Override
    public List<Map<String, Object>> list() throws Exception { 
        return calculatorDao.findAll();
    }
    
    @Override
    public List<Map<String, Object>> listToday() throws Exception { 
        return calculatorDao.findToday();
    }
    
    @Override
    public int delete(int no) throws Exception {
        return calculatorDao.delete(no);
    }

    @Override
    public int add(Map<String, Object> map) throws Exception {
        return calculatorDao.add(map);
    }

}
