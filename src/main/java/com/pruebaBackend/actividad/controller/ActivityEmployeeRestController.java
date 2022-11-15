package com.pruebaBackend.actividad.controller;

import com.pruebaBackend.actividad.dto.ActividadEmpleadoDto;
import com.pruebaBackend.actividad.entities.ActividadEmpleado;
import com.pruebaBackend.actividad.service.ActividadEmpleadoService;
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
@RequestMapping(value = "/api/v1/assign", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivityEmployeeRestController {
    private ActividadEmpleadoService actividadEmpleadoService;
    private ModelMapper modelMapper;

    @Autowired
    public ActivityEmployeeRestController(ActividadEmpleadoService actividadEmpleadoService, ModelMapper modelMapper) {
        this.actividadEmpleadoService = actividadEmpleadoService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ActividadEmpleadoDto> saveActivity(@Valid @RequestBody ActividadEmpleadoDto actividadEmpleadoDto) {
        var assignActivity = this.modelMapper.map(actividadEmpleadoDto, ActividadEmpleado.class);
        assignActivity = this.actividadEmpleadoService.assignActivity(assignActivity);
        return new ResponseEntity<>(modelMapper.map(assignActivity, ActividadEmpleadoDto.class), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ActividadEmpleadoDto>> list() {
        var listAssign = this.actividadEmpleadoService.getAllAssignActivity()
                .stream()
                .map(assign -> modelMapper.map(assign, ActividadEmpleadoDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(listAssign, HttpStatus.OK);
    }
}
