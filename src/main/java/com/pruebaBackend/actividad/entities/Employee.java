package com.pruebaBackend.actividad.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE employee SET deleted = true WHERE emp_id=?")
@Where(clause = "deleted=false")
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int emp_id;

    @Column(name = "emp_numero_documento")
    private Long empNumeroDocumento;

    @Column(name = "emp_nombre")
    private String empNombre;

    @Column(name = "emp_apellido")
    private String empApellido;

    @Column(name = "emp_telefono")
    private Long empTelefono;

    @Override
    public int getId() {
        return 0;
    }
}
