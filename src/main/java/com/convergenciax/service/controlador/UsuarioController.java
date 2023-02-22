package com.convergenciax.service.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.convergenciax.service.servicio.UsuarioService;
import com.convergenciax.service.entidades.Usuario;
import com.convergenciax.service.modelos.Carro;
import com.convergenciax.service.modelos.Moto;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios(){
		List<Usuario> usuarios = usuarioService.getAll();
		if (usuarios.isEmpty())
			return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if ( usuario == null)  
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
		Usuario nuevoUsuario = usuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}
	
	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity <List <Carro>> listarCarros(@PathVariable("usuarioId") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario == null)
			return ResponseEntity.notFound().build();
		
		List <Carro> carros =  usuarioService.getCarros(id);
		return ResponseEntity.ok(carros);	
	}
	
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity <List <Moto>> listarMotos(@PathVariable("usuarioId") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario == null)
			return ResponseEntity.notFound().build();
		
		List <Moto> motos =  usuarioService.getMotos(id);
		return ResponseEntity.ok(motos);	
	}
		

}
