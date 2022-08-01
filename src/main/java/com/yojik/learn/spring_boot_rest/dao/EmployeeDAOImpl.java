package com.yojik.learn.spring_boot_rest.dao;


import com.yojik.learn.spring_boot_rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
//    @Transactional
    public List<Employee> getAllEmployees() {
        Session session = entityManager.unwrap(Session.class);
        List<Employee> allEmployees =session.createQuery("SELECT emplyee from Employee emplyee", Employee.class).getResultList();
//        Query<Employee> query = session.createQuery("FROM Employee");
//        List<Employee> allEmployees = query.getResultList();
//        System.out.println(allEmployees);

        return allEmployees;
    }

    public List<String> getDepartments() {
        Session session = entityManager.unwrap(Session.class);
        List<String> departments = session.createQuery("Select distinct department from Employee",String.class).getResultList();
        return departments;
    }

    @Override
    public void saveOrUpdateEmployee(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Employee.class, id);
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
//        Employee employee = session.get(Employee.class, id);
//        session.delete(employee);
        Query<?> query= session.createQuery("delete from Employee " +
                "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
