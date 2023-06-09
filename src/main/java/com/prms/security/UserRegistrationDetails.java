package com.prms.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.prms.entity.User;

import lombok.Data;

/**
 * Represents the user details required for user registration and authentication.
 * Implements the Spring Security UserDetails interface.
 * 
 * <p>
 * The user details include the user's username, password, enabled status, and authorities.
 * The authorities are derived from the user's roles.
 * </p>
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see User
 */
@Data
public class UserRegistrationDetails implements UserDetails{

	/**
     * The username of the user.
     */
	private String userName;
	/**
     * The password of the user.
     */
	private String password;
	/**
     * Indicates whether the user account is enabled or disabled.
     */
	private boolean isChecked;
	/**
     * The authorities granted to the user.
     * These authorities determine the user's access privileges.
     */
	private List<GrantedAuthority> authorities;
	
	/**
     * Constructs a new UserRegistrationDetails object based on the provided User entity.
     *
     * @param user the User entity from which to extract the user details
     */
	public UserRegistrationDetails(User user) {
		super();
		this.userName = user.getEmail();
		this.password = user.getPassword();
		this.isChecked = user.isChecked();
		this.authorities = Arrays.stream(user.getRole()
				                 .split(","))
				                 .map(SimpleGrantedAuthority::new)
				                 .collect(Collectors.toList());
	}

	/**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	
	/**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	/**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	/**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isChecked;
	}
}
