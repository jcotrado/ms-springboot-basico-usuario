package com.convergenciax.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.convergenciax.service.modelos.Moto;


/*con Feingclient, se define que se comunicara con  microservicio moto-service y su url indicada 
 * con @PostMapping se indica que con @RequestBody guarde un objeto Carro, en 
 * 
 * FeingCliente simplifica el uso con restControleer, se crea interface con nombre y url del microservicio.
 */

@FeignClient(name = "moto-service")
@RequestMapping("/moto")
public interface MotoFeignClient {
	//se agrego metodo en UsuarioService
		@PostMapping()
		public Moto	 save(@RequestBody Moto moto);
		
		@GetMapping("/usuario/{usuarioId}")
		public List <Moto> getMotos(@PathVariable("usuarioId") int usuarioId);
}
