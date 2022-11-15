package com.pruebaBackend.actividad.service.impl;


import com.pruebaBackend.actividad.entities.Actividad;
import com.pruebaBackend.actividad.exceptions.BadRequestException;
import com.pruebaBackend.actividad.exceptions.ObjetNotExistException;
import com.pruebaBackend.actividad.repository.ActividadRepository;
import com.pruebaBackend.actividad.service.ActividadService;
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
public class ActividadServiceImpl implements ActividadService {

    private ActividadRepository actividadRepository;
    private Validator validator;

    @Autowired
    public ActividadServiceImpl(ActividadRepository actividadRepository, Validator validator) {

        this.actividadRepository = actividadRepository;
        this.validator = validator;
    }

    @Override
    public List<Actividad> getAllActivities() {
        return actividadRepository.findAll();
    }

    @Override
    public Actividad create(Actividad actividad) {
        this.validate(actividad);
        return actividadRepository.save(actividad);
    }

    @Override
    public Actividad update(int idActivity, Actividad actividad) {
        this.validate(actividad);
        var getActivity = this.searchId(idActivity);
            if (getActivity.getId() == idActivity) {
                return this.actividadRepository.save(actividad);
            }
        throw new BadRequestException("Activity ID does not correspond to the path");
    }

    @Override
    public Actividad searchId(int idActivity) {
        return this.actividadRepository.findById(idActivity)
                .orElseThrow(() -> new ObjetNotExistException("There is no activity with the ID: " + idActivity));
    }

    @Override
    public void delect(int idActivity) {
        var activity = this.searchId(idActivity);
        actividadRepository.deleteById(activity.getId());
    }

    private void validate(Actividad actividad) {
        Set<ConstraintViolation<Actividad>> violations = validator.validate(actividad);
        if (!violations.isEmpty()) {
            var sb = new StringBuilder();
            for (ConstraintViolation<Actividad> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }
    }
}
