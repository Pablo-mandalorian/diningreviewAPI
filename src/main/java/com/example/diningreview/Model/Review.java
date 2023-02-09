package com.example.diningreview.Model;


import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Review {
    
    @Id
    @GeneratedValue
    private Long Id;

    private String SubmittedBy;
    private Long restaurantId;
    private Integer peanutScoreOp;
    private Integer eggScoreOp;
    private Integer dairyScoreOp;
    private String commentaryOp;
    private Status status;
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
	 * @return the submittedBy
	 */
	public String getSubmittedBy() {
		return SubmittedBy;
	}
	/**
	 * @param submittedBy the submittedBy to set
	 */
	public void setSubmittedBy(String submittedBy) {
		SubmittedBy = submittedBy;
	}
	/**
	 * @return the restaurantId
	 */
	public Long getRestaurantId() {
		return restaurantId;
	}
	/**
	 * @param restaurantId the restaurantId to set
	 */
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	/**
	 * @return the peanutScoreOp
	 */
	public Integer getPeanutScoreOp() {
		return peanutScoreOp;
	}
	/**
	 * @param peanutScoreOp the peanutScoreOp to set
	 */
	public void setPeanutScoreOp(Integer peanutScoreOp) {
		this.peanutScoreOp = peanutScoreOp;
	}
	/**
	 * @return the eggScoreOp
	 */
	public Integer getEggScoreOp() {
		return eggScoreOp;
	}
	/**
	 * @param eggScoreOp the eggScoreOp to set
	 */
	public void setEggScoreOp(Integer eggScoreOp) {
		this.eggScoreOp = eggScoreOp;
	}
	/**
	 * @return the dairyScoreOp
	 */
	public Integer getDairyScoreOp() {
		return dairyScoreOp;
	}
	/**
	 * @param dairyScoreOp the dairyScoreOp to set
	 */
	public void setDairyScoreOp(Integer dairyScoreOp) {
		this.dairyScoreOp = dairyScoreOp;
	}
	/**
	 * @return the commentaryOp
	 */
	public String getCommentaryOp() {
		return commentaryOp;
	}
	/**
	 * @param commentaryOp the commentaryOp to set
	 */
	public void setCommentaryOp(String commentaryOp) {
		this.commentaryOp = commentaryOp;
	}
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
    
    

}
