package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name="Title")
	private String title;
	@Column(name="showTime")
	private String showTime;
	@Column(name="screen")
	private String screen;
	@Column(name="seats")
	private SeatingMap seats;
	
	
	public Movie() {
		
	}
	
	public Movie(String title, String showTime, String screen) {
		super();
		this.title = title;
		this.showTime = showTime;
		this.screen = screen;
		this.seats = new SeatingMap();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}
	
	

}
