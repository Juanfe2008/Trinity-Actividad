package com.pruebaBackend.actividad.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class ActividadEmpleadoDto {

    protected int id_act_emp;

    @NotNull(message = "El Id del empleado no puede estar vacio")
    private int id_empleado;

    @NotNull(message = "El Id de la actividad no puede estar vacia")
    private int id_actividad;

}
