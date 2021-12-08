package com.zoran.demo.dto;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface UsersListDTO {
	
	public String getUserId();
	public String getFirstName();
	public String getLastName();
	public String getUsername();
	public String getEmail();
	public Date getLastLoginDate();
	public Date getLastLoginDateDisplay();
	public Date getJoinDate();
	public String getProfileImageUrl();
	public Boolean getIsActive();
	public String getRole();
	public Boolean geIsNotLocked();
	public String getAuthorities();
	
	
	/*
	@Query(nativeQuery=true, value="SELECT user_id, first_name, last_name, username, " + 
    		"email, last_login_date, last_login_date_display, join_date, profile_image_url, " +
    		"is_active, role, is_not_locked FROM `full-stack-ecommerce`.user;")
    public List<UsersList> findSiteUsers();
*/
}


