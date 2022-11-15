package com.pruebaBackend.actividad.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ActividadDto {

    protected Integer id;

    @NotNull(message = "La descripcion no puede estar vacia")
    private String descripcion;

    @NotNull(message = "El estatus no puede estar vacio")
    private String estado;

    @NotNull(message = "La fecha final no puede estar vacia")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaFinal;

}
