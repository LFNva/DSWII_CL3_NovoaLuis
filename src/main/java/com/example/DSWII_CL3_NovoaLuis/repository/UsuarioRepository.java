package com.example.DSWII_CL3_NovoaLuis.repository;

import com.example.DSWII_CL3_NovoaLuis.model.bd.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNomusuario(String nomusuario);
}
