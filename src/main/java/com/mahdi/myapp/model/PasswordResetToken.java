package com.mahdi.myapp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken {
 
    private static final int EXPIRATION = 60 * 24; 
  
   
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
 
    private String token;
 
    @OneToOne(targetEntity = UserProfile.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserProfile userProfile;
 
    private Date expiryDate;

    private Date CreatedTime;
    
    private Date ModifiedTime;
    
    public PasswordResetToken(){
    	CreatedTime = new Date();
    	ModifiedTime = new Date();
    	expiryDate= new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
    }
    
    public PasswordResetToken(UserProfile userProfile, String token){
    	this.userProfile = userProfile;
    	this.token = token;
    	CreatedTime = new Date();
    	ModifiedTime = new Date();
    	expiryDate= new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
    }
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public static int getExpiration() {
		return EXPIRATION;
	}

	public Date getCreatedTime() {
		if(CreatedTime==null)
			CreatedTime=new Date();
		return CreatedTime;
	}

	public void setCreatedTime(Date createdTime) {
		CreatedTime = createdTime;
	}

	public Date getModifiedTime() {
		if(ModifiedTime==null)
			ModifiedTime=new Date();
		return ModifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		ModifiedTime = modifiedTime;
	}
    
    
}