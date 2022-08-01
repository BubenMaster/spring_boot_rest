package com.yojik.learn.spring_boot_rest.service;


import com.yojik.learn.spring_boot_rest.dao.EmployeeDAO;
import com.yojik.learn.spring_boot_rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    @Transactional
    public List<String> getDepartments() {return employeeDAO.getDepartments();}

    @Override
    @Transactional
    public void deleteEmployee(int id) {
    employeeDAO.deleteEmployee(id);
    }

    @Override
    @Transactional
    public void saveOrUpdateEmployee(Employee employee) {
        employeeDAO.saveOrUpdateEmployee(employee);
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }
}
