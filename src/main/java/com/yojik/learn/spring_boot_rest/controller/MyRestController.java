package com.yojik.learn.spring_boot_rest.controller;

import com.yojik.learn.spring_boot_rest.entity.Employee;
import com.yojik.learn.spring_boot_rest.service.EmployeeService;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.codec.json.Jackson2SmileEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }



    @GetMapping(value = "/download",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody byte[] downloadAllEmployees() throws IOException {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        InputStream in = new FileInputStream("sometext.txt");
        return in.readAllBytes();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);

//        if (employee == null) throw new NoSuchEmployeeException("There no Employee with this " +
//                "ID = " + id + " in database");
        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){
        employeeService.saveOrUpdateEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveOrUpdateEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        if (getEmployee(id) != null) {
            employeeService.deleteEmployee(id);
        }
        return "Employee with id " + id + " was erased";
    }

}
