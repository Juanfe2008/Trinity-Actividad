package com.pruebaBackend.actividad.service;

import com.pruebaBackend.actividad.entities.Actividad;

import java.util.List;

public interface ActividadService {
    List<Actividad> getAllActivities();
    Actividad create(Actividad actividad);
    Actividad update(int idActivity, Actividad actividad);
    Actividad searchId(int idActivity);
    void delect(int idActivity);
}
