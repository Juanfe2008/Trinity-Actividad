package com.pruebaBackend.actividad.repository;

import com.pruebaBackend.actividad.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Employee, Integer> {
}
