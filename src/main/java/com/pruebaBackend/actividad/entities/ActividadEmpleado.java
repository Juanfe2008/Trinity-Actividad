package com.pruebaBackend.actividad.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "actividad_empleado")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE activity_employee SET deleted = true WHERE act_id=?")
@Where(clause = "deleted=false")
public class ActividadEmpleado extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "act_emp_id")
    private int id_act_emp;

    @Column(name = "act_emp_id_empleado")
    private int id_empleado;

    @Column(name = "act_emp_id_actividad")
    private int id_actividad;

    @Override
    public int getId() {
        return 0;
    }
}
