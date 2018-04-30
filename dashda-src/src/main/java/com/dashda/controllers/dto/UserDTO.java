/**
 * 
 */
package com.dashda.controllers.dto;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * @author mhanafy
 *
 */
public class UserDTO implements AbstractDTO{

	private int id;
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotEmpty
	@Digits(fraction = 0, integer = 6)
	private String accountId;
	@NotEmpty
	@Digits(fraction = 0, integer = 6)
	private String userRoleId;
	@NotEmpty
	private String firstName;
	private String lastName;
	private String address;
	@NotEmpty
	@Digits(fraction = 0, integer = 3)
	private String districtId;
	@NotEmpty
	@Digits(fraction = 0, integer = 2)
	private String governorateId;
	@NotEmpty
	private String phone1;
	private String phone2;
	private String phone3;
	private String phone4;
	private String phone5;
	private String phone6;
	private List<ClaimDTO> claims;
	
	
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


	
	public List<ClaimDTO> getClaims() {
		return claims;
	}

	public void setClaims(List<ClaimDTO> claims) {
		this.claims = claims;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the userRoleId
	 */
	public String getUserRoleId() {
		return userRoleId;
	}

	/**
	 * @param userRoleId the userRoleId to set
	 */
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the districtId
	 */
	public String getDistrictId() {
		return districtId;
	}

	/**
	 * @param districtId the districtId to set
	 */
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	/**
	 * @return the governorateId
	 */
	public String getGovernorateId() {
		return governorateId;
	}

	/**
	 * @param governorateId the governorateId to set
	 */
	public void setGovernorateId(String governorateId) {
		this.governorateId = governorateId;
	}

	/**
	 * @return the phone1
	 */
	public String getPhone1() {
		return phone1;
	}

	/**
	 * @param phone1 the phone1 to set
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	/**
	 * @return the phone2
	 */
	public String getPhone2() {
		return phone2;
	}

	/**
	 * @param phone2 the phone2 to set
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	/**
	 * @return the phone3
	 */
	public String getPhone3() {
		return phone3;
	}

	/**
	 * @param phone3 the phone3 to set
	 */
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	/**
	 * @return the phone4
	 */
	public String getPhone4() {
		return phone4;
	}

	/**
	 * @param phone4 the phone4 to set
	 */
	public void setPhone4(String phone4) {
		this.phone4 = phone4;
	}

	/**
	 * @return the phone5
	 */
	public String getPhone5() {
		return phone5;
	}

	/**
	 * @param phone5 the phone5 to set
	 */
	public void setPhone5(String phone5) {
		this.phone5 = phone5;
	}

	/**
	 * @return the phone6
	 */
	public String getPhone6() {
		return phone6;
	}

	/**
	 * @param phone6 the phone6 to set
	 */
	public void setPhone6(String phone6) {
		this.phone6 = phone6;
	}
	
	
	

}
