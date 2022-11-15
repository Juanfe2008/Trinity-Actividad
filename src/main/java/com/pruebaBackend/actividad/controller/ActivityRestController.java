package com.pruebaBackend.actividad.controller;

import com.pruebaBackend.actividad.dto.ActividadDto;
import com.pruebaBackend.actividad.entities.Actividad;
import com.pruebaBackend.actividad.exceptions.ObjetNotExistException;
import com.pruebaBackend.actividad.service.ActividadService;
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
@RequestMapping(value = "/api/v1/activities", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivityRestController {

    private ActividadService actividadService;
    private ModelMapper modelMapper;

    @Autowired
    public ActivityRestController(ActividadService actividadService, ModelMapper modelMapper) {
        this.actividadService = actividadService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ActividadDto>> list() {
        var listActivity = this.actividadService.getAllActivities()
                .stream()
                .map(activity -> modelMapper.map(activity, ActividadDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(listActivity, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActividadDto> getActivityId(@PathVariable("id") int id) {
        var activity = this.actividadService.searchId(id);
        return new ResponseEntity<>(this.modelMapperTransform(activity),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ActividadDto> saveActivity(@Valid @RequestBody ActividadDto actividadDto) {
        if (actividadDto == null){
            throw new ObjetNotExistException("Activity ID does not correspond to the path");
        }
        var activity = this.modelMapper.map(actividadDto, Actividad.class);
        activity = this.actividadService.create(activity);
        return new ResponseEntity<>(this.modelMapperTransform(activity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActividadDto> update(@Valid @RequestBody ActividadDto actividadDto,
                                               @PathVariable("id") int id){
        if (actividadDto == null && id == actividadDto.getId()){
            throw new ObjetNotExistException("Activity is empty");
        }
        var activity = this.modelMapper.map(actividadDto, Actividad.class);
        activity = this.actividadService.update(id,activity);
        return new ResponseEntity<>(this.modelMapperTransform(activity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable("id") int id){
        this.actividadService.delect(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ActividadDto modelMapperTransform(Actividad actividad){
        return modelMapper.map(actividad, ActividadDto.class);
    }
}
