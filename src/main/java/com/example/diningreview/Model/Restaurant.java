package com.example.diningreview.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Restaurant {

	 
	 @Id
	 @GeneratedValue
     private Long id;

    private String restuarantName;
    private String overallScore;
    private String zipCode;
    private String peanutScore;
    private String eggScore;
    private String dairyScore;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the restuarantName
	 */
	public String getRestuarantName() {
		return restuarantName;
	}
	/**
	 * @param restuarantName the restuarantName to set
	 */
	public void setRestuarantName(String restuarantName) {
		this.restuarantName = restuarantName;
	}
	/**
	 * @return the overallScore
	 */
	public String getOverallScore() {
		return overallScore;
	}
	/**
	 * @param overallScore the overallScore to set
	 */
	public void setOverallScore(String overallScore) {
		this.overallScore = overallScore;
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
	 * @return the peanutScore
	 */
	public String getPeanutScore() {
		return peanutScore;
	}
	/**
	 * @param peanutScore the peanutScore to set
	 */
	public void setPeanutScore(String peanutScore) {
		this.peanutScore = peanutScore;
	}
	/**
	 * @return the eggScore
	 */
	public String getEggScore() {
		return eggScore;
	}
	/**
	 * @param eggScore the eggScore to set
	 */
	public void setEggScore(String eggScore) {
		this.eggScore = eggScore;
	}
	/**
	 * @return the dairyScore
	 */
	public String getDairyScore() {
		return dairyScore;
	}
	/**
	 * @param dairyScore the dairyScore to set
	 */
	public void setDairyScore(String dairyScore) {
		this.dairyScore = dairyScore;
	}

}
