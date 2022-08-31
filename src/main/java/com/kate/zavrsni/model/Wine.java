package com.kate.zavrsni.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Wine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "wine")
	private Set<Review> reviews;
	
	@NotEmpty(message = "Must have a name.")
	@Column(length = 100, nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "owner")
	private User owner;
	
	@NotEmpty(message = "Must enter variety.")
	@Column(length = 50, nullable = false)
	private String variety;
	
	@NotNull(message = "Must enter year.")
	@Column(nullable = false)
	private int year;
	
	@NotEmpty(message = "Must enter type.")
	@Column(length = 50, nullable = false)
	private String type;
	
	@NotEmpty(message = "Must enter position.")
	@Column(length = 50, nullable = false)
	private String position;
	
	@NotEmpty(message = "Must enter region.")
	@Column(length = 50, nullable = false)
	private String region;
	
	@NotEmpty(message = "Must enter alcohol.")
	@Column(length = 50, nullable = false)
	private String alcohol;
	
	@NotEmpty(message = "Must enter acidity.")
	@Column(length = 50, nullable = false)
	private String acidity;
	
	@NotEmpty(message = "Must enter tannin.")
	@Column(length = 50, nullable = false)
	private String tannin;
	
	@NotEmpty(message = "Must enter body.")
	@Column(length = 50, nullable = false)
	private String body;
	
	@NotEmpty(message = "Must enter sweetness.")
	@Column(length = 50, nullable = false)
	private String sweetness;
	
	@NotEmpty(message = "Must enter notes.")
	@Column(length = 50, nullable = false)
	private String note;
	
	@NotEmpty(message = "Must enter food pairing.")
	@Column(length = 50, nullable = false)
	private String foodPairing;
	
	@NotEmpty
	@Column(nullable = false, length = 10000)
	private String picture;
	
	@Column(nullable = false)
	private Double rating = 0.0;
	
	public Wine() {
		super();
	}


	public Wine(int id, @NotEmpty(message = "Must have a name.") String name, User owner,
			@NotEmpty(message = "Must enter variety.") String variety,
			@NotNull(message = "Must enter year.") int year,
			@NotEmpty(message = "Must enter type.") String type,
			@NotEmpty(message = "Must enter position.") String position,
			@NotEmpty(message = "Must enter region.") String region,
			@NotEmpty(message = "Must enter alcohol.") String alcohol,
			@NotEmpty(message = "Must enter acidity.") String acidity,
			@NotEmpty(message = "Must enter tannin.") String tannin, 
			@NotEmpty(message = "Must enter body.") String body,
			@NotEmpty(message = "Must enter sweetness.") String sweetness,
			@NotEmpty(message = "Must enter notes.") String note,
			@NotEmpty(message = "Must enter food pairing.") String foodPairing,
			@NotEmpty String picture, Double rating) {
		super();
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.variety = variety;
		this.year = year;
		this.type = type;
		this.position = position;
		this.region = region;
		this.alcohol = alcohol;
		this.acidity = acidity;
		this.tannin = tannin;
		this.body = body;
		this.sweetness = sweetness;
		this.note = note;
		this.foodPairing = foodPairing;
		this.picture = picture;
		this.rating = rating;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public User getOwner() {
		return owner;
	}


	public void setOwner(User owner) {
		this.owner = owner;
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


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}

	public String getAlcohol() {
		return alcohol;
	}


	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}


	public String getAcidity() {
		return acidity;
	}


	public void setAcidity(String acidity) {
		this.acidity = acidity;
	}


	public String getTannin() {
		return tannin;
	}


	public void setTannin(String tannin) {
		this.tannin = tannin;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public String getSweetness() {
		return sweetness;
	}


	public void setSweetness(String sweetness) {
		this.sweetness = sweetness;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public String getFoodPairing() {
		return foodPairing;
	}


	public void setFoodPairing(String foodPairing) {
		this.foodPairing = foodPairing;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public Set<Review> getReviews() {
		return reviews;
	}


	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}


	public Double getRating() {
		return rating;
	}


	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	
	
}
