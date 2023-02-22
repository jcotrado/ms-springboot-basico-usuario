package com.convergenciax.service.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.convergenciax.service.entidades.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Integer	> {

}
