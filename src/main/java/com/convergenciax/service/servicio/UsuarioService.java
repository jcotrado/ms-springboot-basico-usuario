package com.convergenciax.service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.convergenciax.service.repositorio.UsuarioRepository;
import com.convergenciax.service.entidades.Usuario;
import com.convergenciax.service.modelos.Carro;
import com.convergenciax.service.modelos.Moto;

@Service
public class UsuarioService {

	@Autowired
	private RestTemplate restTemplate; //se inyecta lo que se guardo en Container
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List <Carro> getCarros(int usuarioId){
		List <Carro> carros = restTemplate.getForObject("http://localhost:8004/carro/usuario/"+usuarioId, List.class);
		return carros;
	}
	
	public List <Moto> getMotos(int usuarioId){
		List <Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/"+usuarioId, List.class);
		return motos;
 	}
	
	
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
