package com.example.demo.controller;

public class Response {
	
	private String username;
	private String ime;
	private String prezime;
	private int iduserType;
	
	public int getIduserType() {
		return iduserType;
	}

	public void setIduserType(int idUloga) {
		this.iduserType = idUloga;
	}

	public Response() {
		this.username="";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	@Override
	public String toString() {
		return  "username=" + username;
	}

}
