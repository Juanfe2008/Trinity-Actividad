package com.pruebaBackend.actividad.service;

import com.pruebaBackend.actividad.entities.Employee;

import java.util.List;

public interface EmpleadoService {
    List<Employee> getAllActivities();
    Employee create(Employee employee);
    Employee update(int idEmployee, Employee employee);
    Employee searchId(int idEmployee);
    void delect(int idEmployee);
}
