package com.donateknowledge.dto.user;

import static com.donateknowledge.utils.CheapestGadgetUtils.getDateTimeToday;
import static com.donateknowledge.utils.CheapestGadgetUtils.getEnumMappedList;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true, 
value={"firstName","middleName","lastName"})
@JsonInclude(Include.NON_NULL)
public class User {

	@JsonProperty("_id")
	private String email;
	private String firstName;
	private String fullName;
	private String insertedBy;
	private Date insertedDate;
	private Date lastLogin;
	private String lastName;
	private String middleName;
	private String modifiedBy;
	private Date modifiedDate;
	private List<String> oldPasswords;
	private List<UserPhone> oldPhone;
	private String password;
	private List<UserPhone> phone;
	private boolean registered;
	private Date registeredDate;
	private List<String> userRole;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFullName() {
		if (StringUtils.isNotEmpty(this.firstName)) {
			StringBuilder sb = new StringBuilder();
			sb.append(this.firstName);
			if (StringUtils.isNotEmpty(this.middleName)) {
				sb.append(" ").append(this.middleName);
			}
			if (StringUtils.isNotEmpty(this.lastName)) {
				sb.append(" ").append(this.lastName);
			}
			this.fullName = sb.toString();
		}
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
		if (StringUtils.isNotEmpty(this.fullName)) {
			String[] name = fullName.split(" ");
			setFirstName(name[0]);
			if (name.length == 1) {
				setFirstName(name[0]);
			}
			if (name.length == 2) {
				setFirstName(name[0]);
				setLastName(name[1]);
			}
			if (name.length == 3) {
				setFirstName(name[0]);
				setMiddleName(name[1]);
				setLastName(name[2]);
			}
			if (name.length > 3) {
				setFirstName(name[0]);
				StringBuilder sb = new StringBuilder();
				for (int i = 1; i < name.length - 2; i++) {
					sb.append(name[i]).append(" ");
				}

				setMiddleName(sb.toString().trim());
				setLastName(name[name.length - 1]);
			}
		}
	}
	public String getInsertedBy() {
		if (StringUtils.isEmpty(insertedBy)) {
			insertedBy = "System";
			// TODO : 
			setModifiedBy(insertedBy);
		}
		return insertedBy;
	}
	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}
	public Date getInsertedDate() {
		if (insertedDate == null) {
			insertedDate = getDateTimeToday();
			//TODO : 
			setModifiedDate(insertedDate);
		}
		return insertedDate;
	}
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRegistered() {
		return registered;
	}
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	public List<String> getOldPasswords() {
		if (oldPasswords == null) {
			oldPasswords = new ArrayList<String>() {

				private static final long serialVersionUID = 1L;

				@Override
				public boolean add(String str) {
					if (this.size() >= 5) {
						this.remove(0);
					}
					return super.add(str);
				}

			};
		}
		return oldPasswords;
	}
	public List<UserPhone> getOldPhone() {
		if (oldPhone == null) {
			oldPhone = new ArrayList<>();
		}
		return oldPhone;
	}
	public List<UserPhone> getPhone() {
		if (phone == null) {
			phone = new ArrayList<>();
		}
		return phone;
	}
	public List<String> getUserRole() {
		boolean isUserRoleSet = true;
		if (CollectionUtils.isEmpty(userRole)) {
			isUserRoleSet = false;
		}
		userRole = getEnumMappedList(userRole, asList(UserRole.values()));
		if (!isUserRoleSet) {
			userRole.add(UserRole.USER.toString());
		}
		return userRole;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", firstName=" + getFirstName() + ", fullName=" + fullName + ", insertedBy="
				+ insertedBy + ", insertedDate=" + insertedDate + ", lastLogin=" + getLastLogin() + ", lastName=" + lastName
				+ ", middleName=" + getMiddleName() + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", oldPasswords=" + oldPasswords + ", oldPhone=" + oldPhone + ", password=" + password + ", phone="
				+ phone + ", registered=" + registered + ", registeredDate=" + registeredDate + ", userRole=" + userRole
				+ "]";
	}
}
