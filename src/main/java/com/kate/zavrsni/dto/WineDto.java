package com.kate.zavrsni.dto;

public class WineDto {

	private String winery;
	private String variety;
	private int year;
	private String type;
	private String position;
	private String sweetness;
	private Double rating = 0.0;
	
	public WineDto(String winery, String variety, int year, String type, String position, String sweetness, Double rating) {
		super();
		this.winery = winery;
		this.variety = variety;
		this.year = year;
		this.type = type;
		this.position = position;
		this.sweetness = sweetness;
		this.rating = rating;
	}

	public WineDto() {
		// TODO Auto-generated constructor stub
	}

	public String getWinery() {
		return winery;
	}

	public void setWinery(String winery) {
		this.winery = winery;
	}

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSweetness() {
		return sweetness;
	}

	public void setSweetness(String sweetness) {
		this.sweetness = sweetness;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	
}
