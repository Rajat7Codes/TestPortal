package com.iceico.testportal.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 *
 */

@Entity
@Table(name = "tab_persistent_logins")
public class PersistentLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7238186336570259861L;

	@Id
	private String series;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "token", unique = true, nullable = false)
	private String token;

	private Date last_used;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLast_used() {
		return last_used;
	}

	public void setLast_used(Date last_used) {
		this.last_used = last_used;
	}

}
