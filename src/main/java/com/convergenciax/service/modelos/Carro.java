package com.convergenciax.service.modelos;

public class Carro {

	private String marca;
	private String modelo;
	private int usuarioId; // Se agrega para utilizarlo en FeignCliente y UsuarioService.saveCarro
	
	
	public int getUsuarioId() { // Se agrega para utilizarlo en FeignCliente y UsuarioService.saveCarro
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {// Se agrega para utilizarlo en FeignCliente y UsuarioService.saveCarro
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

	public Carro() {
		super();
		// TODO Auto-generated constructor stub
	}

}
