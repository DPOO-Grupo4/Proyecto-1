package System;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Usuarios.Usuario;

public class Actividad {
	private String creator;
	public int id;
	public boolean mandatory;
	public String descripcion;
	public String difficulty;
	public int duration;
	//public boolean started;
	public LocalDateTime dateLimit;
	private HashMap<String, String[]> state;
	//public HashMap<String, String> reviews;
	
	public Actividad(String creator, int id, boolean mandatory, String descripcion, String difficulty, int duration, LocalDateTime dateLimit, HashMap<String, String[]> states) {
		this.creator = creator;
		this.id = id;
		this.mandatory = mandatory;
		this.descripcion = descripcion;
		this.difficulty = difficulty;
		this.duration = duration;
		//this.started = started;
		this.dateLimit = dateLimit;
		this.state = states;
	}
	public int getID() {
		return this.id;
	}
	public String getDescripcion() {
		return this.descripcion;
	}
	public String getDifficulty() {
		return this.difficulty;
		
	}
	
	public int getDuration() {
		return this.duration;
	}
	public LocalDateTime getDateLimit() {
		return this.dateLimit;
	}
	public HashMap<String, String[]> getState(){
		return this.state;
	}
	public boolean getMandatory (){
		return mandatory;
	}
	public void setState(HashMap<String, String[]> state) {
		this.state = state;
	}
	public void setDescrition(String description) {
		this.descripcion = description;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	public void setDifficulty(String newDifficulty) {
		this.difficulty = newDifficulty;
	}
	public void setDuration(int newDuration) {
		this.duration = newDuration;
	}
	public void setDateLimit (LocalDateTime newDateLimit) {
		this.dateLimit = newDateLimit;
	}
}
