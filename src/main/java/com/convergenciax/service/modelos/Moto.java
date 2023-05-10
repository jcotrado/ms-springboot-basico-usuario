package com.convergenciax.service.modelos;

public class Moto {
	
	private String marca;
	private String modelo;
	private int usuarioId; // Se agrega para utilizarlo en FeignCliente y UsuarioService.saveMoto
	
 
	public int getUsuarioId() { // Se agrega para utilizarlo en FeignCliente y UsuarioService.saveMoto
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {// Se agrega para utilizarlo en FeignCliente y UsuarioService.saveMoto
		this.usuarioId = usuarioId;
	}

	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public Moto() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
