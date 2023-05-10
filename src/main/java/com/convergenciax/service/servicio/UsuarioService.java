package com.convergenciax.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.convergenciax.service.repositorio.UsuarioRepository;
import com.convergenciax.service.entidades.Usuario;
import com.convergenciax.service.feignclients.CarroFeignClient;
import com.convergenciax.service.feignclients.MotoFeignClient;
import com.convergenciax.service.modelos.Carro;
import com.convergenciax.service.modelos.Moto;

@Service
public class UsuarioService {

	@Autowired
	private RestTemplate restTemplate; //se inyecta lo que se guardo en Container
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	// Se agrega para utilizarlo en FeignCliente y UsuarioService.saveMoto
	@Autowired CarroFeignClient carroFeignClient; // Se agrega para utilizarlo en FeignCliente y UsuarioService.saveCarro
	
	@Autowired MotoFeignClient motoFeignClient; 
	
	public List <Carro> getCarros(int usuarioId){
		List <Carro> carros = restTemplate.getForObject("http://localhost:8002/carro/usuario/"+usuarioId, List.class);
		return carros;
	}
	
	public List <Moto> getMotos(int usuarioId){
		List <Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/"+usuarioId, List.class);
		return motos;
 	}
	
	// Se agrega para utilizarlo en FeignCliente y UsuarioService.saveCarro
	public Carro saveCarro(int usuarioId, Carro carro) {
		carro.setUsuarioId(usuarioId);
		Carro nuevoCarro = carroFeignClient.save(carro);
		return nuevoCarro;
	}
	
	// Se agrega para utilizarlo en FeignCliente y UsuarioService.savMoto

	public Moto saveMoto(int usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		Moto nuevaMoto = motoFeignClient.save(moto);
		return nuevaMoto;
		
	}
	
	public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
		
		Map <String, Object> resultado = new HashMap<>();
		
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
		if (usuario == null) {
			resultado.put("Mensaje","El usuario no existe");
			return resultado;
		}
		
		resultado.put("Usuario", usuario);
		List <Carro> carros = carroFeignClient.getCarros(usuarioId);
		if (carros.isEmpty() ) 
			resultado.put("Carros", "El usuario no tiene carros");
		else
			resultado.put("Carros",carros);
		
		List<Moto> motos = motoFeignClient.getMotos(usuarioId);
		if (motos.isEmpty())
			resultado.put("Motos", "El usuario no tiene motos");
		else
			resultado.put("Motos",motos);
		return resultado;
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
