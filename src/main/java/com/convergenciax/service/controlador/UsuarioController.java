package com.convergenciax.service.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.convergenciax.service.servicio.UsuarioService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import com.convergenciax.service.entidades.Usuario;
import com.convergenciax.service.modelos.Carro;
import com.convergenciax.service.modelos.Moto;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> usuarios = usuarioService.getAll();
		if (usuarios.isEmpty())
			return ResponseEntity.noContent().build();

		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id) {
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
		Usuario nuevoUsuario = usuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}

	@CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBackGetCarros")
	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<List<Carro>> getCarros(@PathVariable("usuarioId") int id) {
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario == null)
			return ResponseEntity.notFound().build();
		List<Carro> carros = usuarioService.getCarros(id);
		return ResponseEntity.ok(carros);
	}
	
	// Se agrega para utilizarlo en FeignCliente y UsuarioService.saveCarro,
	// saveMoto+/
	@CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBackSaveCarro")
	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro> saveCarro(@PathVariable("usuarioId") int usuarioId, @RequestBody Carro carro) {
		Carro nuevoCarro = usuarioService.saveCarro(usuarioId, carro);
		return ResponseEntity.ok(nuevoCarro);
	}

	@CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackGetMotos")
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId") int id) {
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario == null)
			return ResponseEntity.notFound().build();
		List<Moto> motos = usuarioService.getMotos(id);
		return ResponseEntity.ok(motos);
	}

	@CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackSaveMoto")
	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto) {
		Moto nuevaMoto = usuarioService.saveMoto(usuarioId, moto);
		return ResponseEntity.ok(nuevaMoto);
	}

	@CircuitBreaker(name = "todosCB", fallbackMethod = "fallBackGetTodos")
	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId) {

		Map<String, Object> resultado = usuarioService.getUsuarioAndVehiculos(usuarioId);
		return ResponseEntity.ok(resultado);
	};

	/*
	 * Implementa metodos fallback llamados desde circuit-breaker para tolerancia de
	 * fallos
	 */
	private ResponseEntity<List<Carro>> fallBackGetCarros(@PathVariable("usuarioId") int id,RuntimeException exception) {
		return new ResponseEntity("El usuario " + id + "Tiene los carros en el taller", HttpStatus.OK);
	}

	private ResponseEntity<Carro> fallBackSaveCarro(@PathVariable("usuarioId") int id, RuntimeException exception) {
		return new ResponseEntity("El usuario " + id + "No tiene dinero para el carro", HttpStatus.OK);
	}

	private ResponseEntity<List<Moto>> fallBackGetMotos(@PathVariable("usuarioId") int id, RuntimeException exception) {
		return new ResponseEntity("El usuario " + id + "Tiene los carros en el taller", HttpStatus.OK);
	}

	private ResponseEntity<Moto> fallBackSaveMoto(@PathVariable("usuarioId") int id, RuntimeException exception) {
		return new ResponseEntity("El usuario " + id + "No tiene dinero para la moto", HttpStatus.OK);
	}
	
    // Se usa <Map <String, Object>> para devolver objetos carros y motos en la misma respuesta
	private ResponseEntity<Map <String, Object>> fallBackGetTodos(@PathVariable("usuarioId") int id, RuntimeException exception) {
		return new ResponseEntity("El usuario " + id + "Tiene todos los vehiculos en el taller", HttpStatus.OK);
	}
}
