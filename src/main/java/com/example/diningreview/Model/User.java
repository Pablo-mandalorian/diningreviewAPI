package com.example.diningreview.Model;


import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private Long Id;

    private String displayName;
    private String City;
    private String State;
    private String zipCode;
    private Boolean peanutAllergies;
    private Boolean eggAllergies;
    private Boolean dairyAllergies;
	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		Id = id;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return City;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		City = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return State;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		State = state;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the peanutAllergies
	 */
	public Boolean getPeanutAllergies() {
		return peanutAllergies;
	}
	/**
	 * @param peanutAllergies the peanutAllergies to set
	 */
	public void setPeanutAllergies(Boolean peanutAllergies) {
		this.peanutAllergies = peanutAllergies;
	}
	/**
	 * @return the eggAllergies
	 */
	public Boolean getEggAllergies() {
		return eggAllergies;
	}
	/**
	 * @param eggAllergies the eggAllergies to set
	 */
	public void setEggAllergies(Boolean eggAllergies) {
		this.eggAllergies = eggAllergies;
	}
	/**
	 * @return the dairyAllergies
	 */
	public Boolean getDairyAllergies() {
		return dairyAllergies;
	}
	/**
	 * @param dairyAllergies the dairyAllergies to set
	 */
	public void setDairyAllergies(Boolean dairyAllergies) {
		this.dairyAllergies = dairyAllergies;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
    
    
    
}
