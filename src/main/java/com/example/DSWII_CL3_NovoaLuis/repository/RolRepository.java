package com.example.DSWII_CL3_NovoaLuis.repository;

import com.example.DSWII_CL3_NovoaLuis.model.bd.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNomrol(String nombrerol);
}
