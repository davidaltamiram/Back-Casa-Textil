package com.proyecto_integrador.casa_textil.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto_integrador.casa_textil.entities.Usuario;
 
public interface UserRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> getByEmail(String email);
}
