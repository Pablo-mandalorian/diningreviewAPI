package com.example.diningreview.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin  {
    
    private Boolean accepted;

	/**
	 * @return the accepted
	 */
	public Boolean getAccepted() {
		return accepted;
	}

	/**
	 * @param accepted the accepted to set
	 */
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
    
    

}
