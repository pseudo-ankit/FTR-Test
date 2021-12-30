package com.ftruser.ftr.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.ftruser.ftr.user.DTO.UserProfileDTO;

@Entity
@Table(name = "ftr_user")
public class User {

	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
	int userId;

    @Column(name = "first_name")
	String firstName;

    @Column(name = "last_name")
	String lastName;

    @Column(name = "email_id")
	String emailId;

    @Column(name = "mobile_number")
	long mobileNumber;
	
    @Column(name = "password")
	String password;
	
    @Column(name = "nationality")
	String nationality;
	
    @Column(name = "passport_number")
	String passportNumber;
	
    @Column(name = "permanent_address")
	String permanentAddress;
	
    @Column(name = "office_address")
	String officeAddress;
	
    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public Long getPersonalIdentificationNumber() {
		return personalIdentificationNumber;
	}

	public void setPersonalIdentificationNumber(Long personalIdentificationNumber) {
		this.personalIdentificationNumber = personalIdentificationNumber;
	}

	@Column(name = "personal_identification_number")
	Long personalIdentificationNumber; 
	
	public static UserProfileDTO createDTO(User user) {
		ModelMapper map= new ModelMapper();
		UserProfileDTO userProfileDTO = map.map(user, UserProfileDTO.class);
		return userProfileDTO;
	}

}