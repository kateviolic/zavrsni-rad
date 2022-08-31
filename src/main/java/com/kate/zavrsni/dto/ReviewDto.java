package com.kate.zavrsni.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReviewDto {

	@NotNull
	@NotEmpty
	private int wineId;
	
	@NotNull
	@NotEmpty
	private Double rating;
	
	@Column(nullable = false)
	private LocalDate date;
	
	private Boolean fruit = false;
	
	private Boolean herbal = false;

	private Boolean floral = false;

	private Boolean butter = false;

	private Boolean nuts = false;

	private Boolean yeast = false;

	private Boolean vanilla = false;

	private Boolean coconut = false;

	private Boolean spices = false;

	private Boolean toasted = false;
	
	private Boolean cigarBox = false;

	private Boolean tobacco = false;

	private Boolean leather = false;

	private Boolean driedFruit = false;

	public ReviewDto(@NotNull @NotEmpty int wineId, @NotNull @NotEmpty Double rating, LocalDate date, Boolean fruit, Boolean herbal,
			Boolean floral, Boolean butter, Boolean nuts, Boolean yeast, Boolean vanilla, Boolean coconut,
			Boolean spices, Boolean toasted, Boolean cigarBox, Boolean tobacco, Boolean leather, Boolean driedFruit) {
		super();
		this.wineId = wineId;
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

	public ReviewDto() {
		// TODO Auto-generated constructor stub
	}

	public int getWineId() {
		return wineId;
	}

	public void setWineId(int wineId) {
		this.wineId = wineId;
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
