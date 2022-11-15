package com.pruebaBackend.actividad.controller;

import com.pruebaBackend.actividad.entities.Actividad;
import com.pruebaBackend.actividad.entities.Employee;
import com.pruebaBackend.actividad.service.ActividadService;
import com.pruebaBackend.actividad.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexRestController {

    private EmpleadoService empleadoService;
    private ActividadService actividadService;

    @Autowired
    public IndexRestController(EmpleadoService empleadoService, ActividadService actividadService) {
        this.empleadoService = empleadoService;
        this.actividadService = actividadService;
    }

    @GetMapping("/init")
    public String index(){
        return "index";
    }

    @GetMapping("/newActivity")
    public String newActivity(){
        return "newActivity";
    }

    @GetMapping("/registerEmployee")
    public String registerEmployee(){
        return "registerEmployee";
    }

    @PostMapping("/registerEmployee")
    public String registerEmployeeFrom(Employee employee){
        empleadoService.create(employee);
        return "index";
    }

    @PostMapping("/newActivity")
    public String newActivityFrom(Actividad actividad){
        actividadService.create(actividad);
        return "index";
    }

    @RequestMapping(value = "/updateActivity/{id}")
    public String updateActivity(Model model, @PathVariable Integer id){
        model.addAttribute("id",id);
        model.addAttribute("command", actividadService.searchId(id));
        return "updateActivity";
    }

    @RequestMapping(value = "/updateActivity/{id}", method = RequestMethod.POST)
    public String updateActivityFrom(@PathVariable Integer id,@ModelAttribute Actividad actividad) {
        actividadService.update(id, actividad);
        return "redirect:/init";
    }

    @RequestMapping(value = "/deleteActivity/{id}")
    public String deleteActivity(@PathVariable Integer id) {
        actividadService.delect(id);
        return "redirect:/init";
    }


}
