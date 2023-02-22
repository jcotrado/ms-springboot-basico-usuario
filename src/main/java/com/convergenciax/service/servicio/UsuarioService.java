package com.convergenciax.service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.convergenciax.service.repositorio.UsuarioRepository;
import com.convergenciax.service.entidades.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List <Usuario> getAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuarioById(int id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return nuevoUsuario;
		
	}
}
