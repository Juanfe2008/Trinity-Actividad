package com.pruebaBackend.actividad.service.impl;

import com.pruebaBackend.actividad.entities.Employee;
import com.pruebaBackend.actividad.exceptions.BadRequestException;
import com.pruebaBackend.actividad.exceptions.ObjetNotExistException;
import com.pruebaBackend.actividad.repository.EmpleadoRepository;
import com.pruebaBackend.actividad.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {
    private EmpleadoRepository empleadoRepository;
    private Validator validator;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository, Validator validator) {
        this.empleadoRepository = empleadoRepository;
        this.validator = validator;
    }

    private void validate(Employee employee) {
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        if (!violations.isEmpty()) {
            var sb = new StringBuilder();
            for (ConstraintViolation<Employee> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }
    }

    @Override
    public List<Employee> getAllActivities() {
        return this.empleadoRepository.findAll();
    }

    @Override
    public Employee create(Employee employee) {
        this.validate(employee);
        return this.empleadoRepository.save(employee);
    }

    @Override
    public Employee update(int idEmployee, Employee employee) {
        this.validate(employee);
        this.searchId(idEmployee);
        if (idEmployee == employee.getEmp_id()) {
            return this.empleadoRepository.save(employee);
        }
        throw new BadRequestException("Employee ID does not correspond to the path");
    }

    @Override
    public Employee searchId(int idEmployee) {
        return this.empleadoRepository.findById(idEmployee)
                .orElseThrow(() -> new ObjetNotExistException("There is no employee with the ID: " + idEmployee));
    }

    @Override
    public void delect(int idEmployee) {
        var employee = this.searchId(idEmployee);
        empleadoRepository.deleteById(idEmployee);
    }
}
