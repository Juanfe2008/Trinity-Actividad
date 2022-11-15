package com.pruebaBackend.actividad.service;

import com.pruebaBackend.actividad.entities.ActividadEmpleado;

import java.util.List;

public interface ActividadEmpleadoService {
    ActividadEmpleado assignActivity(ActividadEmpleado actividadEmpleado);
    List<ActividadEmpleado>getAllAssignActivity();
}
