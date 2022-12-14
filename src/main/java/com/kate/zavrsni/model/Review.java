package com.kate.zavrsni.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "wine_id")
	private Wine wine;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@NotNull
	private Double rating;
	
	@Column(nullable = false)
	private LocalDate date;
	
	private Boolean fruit;
	
	private Boolean herbal;
	
	private Boolean floral;
	
	private Boolean butter;
	
	private Boolean nuts;
	
	private Boolean yeast;
	
	private Boolean vanilla;
	
	private Boolean coconut;
	
	private Boolean spices;
	
	private Boolean toasted;
	
	private Boolean cigarBox;
	
	private Boolean tobacco;
	
	private Boolean leather;
	
	private Boolean driedFruit;

	public Review(int id, User user, Wine wine, Double rating, LocalDate date, Boolean fruit, Boolean herbal, Boolean floral, Boolean butter,
			Boolean nuts, Boolean yeast, Boolean vanilla, Boolean coconut, Boolean spices, Boolean toasted,
			Boolean cigarBox, Boolean tobacco, Boolean leather, Boolean driedFruit) {
		super();
		this.id = id;
		this.user = user;
		this.wine = wine;
		this.rating = rating;
		this.date = date;
		this.fruit = fruit;
		this.herbal = herbal;
		this.floral = floral;
		this.butter = butter;
		this.nuts = nuts;
		this.yeast = yeast;
		this.vanilla = vanilla;
		this.coconut = coconut;
		this.spices = spices;
		this.toasted = toasted;
		this.cigarBox = cigarBox;
		this.tobacco = tobacco;
		this.leather = leather;
		this.driedFruit = driedFruit;
	}

	public Review() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Wine getWine() {
		return wine;
	}

	public void setWine(Wine wine) {
		this.wine = wine;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Boolean getFruit() {
		return fruit;
	}

	public void setFruit(Boolean fruit) {
		this.fruit = fruit;
	}

	public Boolean getHerbal() {
		return herbal;
	}

	public void setHerbal(Boolean herbal) {
		this.herbal = herbal;
	}

	public Boolean getFloral() {
		return floral;
	}

	public void setFloral(Boolean floral) {
		this.floral = floral;
	}

	public Boolean getButter() {
		return butter;
	}

	public void setButter(Boolean butter) {
		this.butter = butter;
	}

	public Boolean getNuts() {
		return nuts;
	}

	public void setNuts(Boolean nuts) {
		this.nuts = nuts;
	}

	public Boolean getYeast() {
		return yeast;
	}

	public void setYeast(Boolean yeast) {
		this.yeast = yeast;
	}

	public Boolean getVanilla() {
		return vanilla;
	}

	public void setVanilla(Boolean vanilla) {
		this.vanilla = vanilla;
	}

	public Boolean getCoconut() {
		return coconut;
	}

	public void setCoconut(Boolean coconut) {
		this.coconut = coconut;
	}

	public Boolean getSpices() {
		return spices;
	}

	public void setSpices(Boolean spices) {
		this.spices = spices;
	}

	public Boolean getToasted() {
		return toasted;
	}

	public void setToasted(Boolean toasted) {
		this.toasted = toasted;
	}

	public Boolean getCigarBox() {
		return cigarBox;
	}

	public void setCigarBox(Boolean cigarBox) {
		this.cigarBox = cigarBox;
	}

	public Boolean getTobacco() {
		return tobacco;
	}

	public void setTobacco(Boolean tobacco) {
		this.tobacco = tobacco;
	}

	public Boolean getLeather() {
		return leather;
	}

	public void setLeather(Boolean leather) {
		this.leather = leather;
	}

	public Boolean getDriedFruit() {
		return driedFruit;
	}

	public void setDriedFruit(Boolean driedFruit) {
		this.driedFruit = driedFruit;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	
}
