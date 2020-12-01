package com.mercury.SpringBootRestDemo.bean;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="MSI_USER")
public class User implements UserDetails{
	@Id
	@SequenceGenerator(name = "msi_user_seq_gen", sequenceName = "MSI_USER_SEQ", allocationSize = 1)
    @GeneratedValue(generator="msi_user_seq_gen", strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.DETACH)
	@JoinTable(
			name ="MSI_USER_MSI_USER_PROFILE",
			joinColumns = {
					@JoinColumn(name ="USER_ID", referencedColumnName ="ID")
			},
			inverseJoinColumns = {
					@JoinColumn(name ="USER_PROFILE_ID", referencedColumnName ="ID")
			}
			)
	private List<Profile> profiles;

	public User(int id, String username, String password, List<Profile> profiles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.profiles = profiles;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", profiles=" + profiles + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return profiles;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
