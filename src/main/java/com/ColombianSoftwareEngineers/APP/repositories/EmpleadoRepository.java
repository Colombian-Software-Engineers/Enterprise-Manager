package com.ColombianSoftwareEngineers.APP.repositories;

import com.ColombianSoftwareEngineers.APP.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
