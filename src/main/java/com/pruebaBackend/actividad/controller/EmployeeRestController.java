package com.pruebaBackend.actividad.controller;

import com.pruebaBackend.actividad.dto.EmployeeDto;
import com.pruebaBackend.actividad.entities.Employee;
import com.pruebaBackend.actividad.exceptions.BadRequestException;
import com.pruebaBackend.actividad.service.EmpleadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeRestController {
    private EmpleadoService empleadoService;
    private ModelMapper modelMapper;

    @Autowired
    public EmployeeRestController(EmpleadoService empleadoService, ModelMapper modelMapper) {
        this.empleadoService = empleadoService;
        this.modelMapper = modelMapper;
    }

    private EmployeeDto modelMapperTransform(Employee employee){
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> list() {
        var listEmployee = this.empleadoService.getAllActivities()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(listEmployee, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getActivityId(@PathVariable("id") int id) {
        var employee = this.empleadoService.searchId(id);
        return new ResponseEntity<>(this.modelMapperTransform(employee),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> saveActivity(@Valid @RequestBody EmployeeDto employeeDto) {
        if (employeeDto == null){
            throw new BadRequestException("The employee cannot be empty");
        }
        var employee = this.modelMapper.map(employeeDto, Employee.class);
        employee = this.empleadoService.create(employee);
        return new ResponseEntity<>(this.modelMapperTransform(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@Valid @RequestBody EmployeeDto  employeeDto,
                                              @PathVariable("id") int id){

        var employee = this.modelMapper.map(employeeDto, Employee.class);
        employee = this.empleadoService.update(id,employee);
        return new ResponseEntity<>(this.modelMapperTransform(employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable("id") int id){
        this.empleadoService.delect(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
