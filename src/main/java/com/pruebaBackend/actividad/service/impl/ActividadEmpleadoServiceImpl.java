package com.pruebaBackend.actividad.service.impl;

import com.pruebaBackend.actividad.entities.Actividad;
import com.pruebaBackend.actividad.entities.ActividadEmpleado;
import com.pruebaBackend.actividad.entities.Employee;
import com.pruebaBackend.actividad.repository.ActividadEmpleadoRepository;
import com.pruebaBackend.actividad.service.ActividadEmpleadoService;
import com.pruebaBackend.actividad.service.ActividadService;
import com.pruebaBackend.actividad.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ActividadEmpleadoServiceImpl implements ActividadEmpleadoService {

    private EmpleadoService empleadoService;
    private ActividadService actividadService;
    private ActividadEmpleadoRepository actividadEmpleadoRepository;

    @Autowired
    public ActividadEmpleadoServiceImpl(EmpleadoService empleadoService, ActividadService actividadService, ActividadEmpleadoRepository actividadEmpleadoRepository) {
        this.empleadoService = empleadoService;
        this.actividadService = actividadService;
        this.actividadEmpleadoRepository = actividadEmpleadoRepository;
    }

    @Override
    public ActividadEmpleado assignActivity(ActividadEmpleado actividadEmpleado) {
        var activity = this.actividadService.searchId(actividadEmpleado.getId_actividad());
        var employee = this.empleadoService.searchId(actividadEmpleado.getId_empleado());
        this.validateDate(activity.getFechaFinal());
        var newAssignActivity = this.mapperObject(activity, employee);
        return this.actividadEmpleadoRepository.save(newAssignActivity);

    }

    @Override
    public List<ActividadEmpleado> getAllAssignActivity() {

        return this.actividadEmpleadoRepository.findAll();
    }

    private ActividadEmpleado mapperObject(Actividad actividad, Employee employee) {
        var newAssigActivity = new ActividadEmpleado();
        newAssigActivity.setId_empleado(employee.getEmp_id());
        newAssigActivity.setId_actividad(actividad.getId());
        return newAssigActivity;
    }

    private void validateDate(Date endDate) {
        var startDate = new Date();
        if (startDate.after(endDate)) {
            var startTime = startDate.getTime();
            var endTime = endDate.getTime();
            var daysSince = (long) Math.floor(startTime / (1000 * 60 * 60 * 24));
            var daysUntil = (long) Math.floor(endTime / (1000 * 60 * 60 * 24));
            System.out.println("Days with delay period:" + (daysSince - daysUntil));
        }
    }
}
