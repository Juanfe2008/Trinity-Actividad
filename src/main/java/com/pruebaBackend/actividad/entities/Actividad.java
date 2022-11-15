package com.pruebaBackend.actividad.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "actividades")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE activities SET deleted = true WHERE act_id=?")
@Where(clause = "deleted=false")
public class Actividad extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "act_id")
    private int id;

    @Column(name = "act_descripcion")
    private String descripcion;

    @Column(name = "act_estado")
    private String estado;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "act_fecha_final")
    private Date fechaFinal;
}
