package com.pruebaBackend.actividad.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    protected Integer emp_id;

    @NotNull(message = "El numero de documento no puede estar vacio")
    private Long empNumeroDocumento;

    @NotNull(message = "El nombre no puede estar vacio")
    private String empNombre;

    @NotNull(message = "El apellido no puede estar vacio")
    private String empApellido;

    @NotNull(message = "El numero de telefono no puede estar vacio")
    private Long empTelefono;

}
