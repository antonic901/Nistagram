package nistagram.userservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nistagram.userservice.model.enums.GenderEnum;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private int phoneNumber;
	
	@Column(name = "usertype", nullable = false)
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;
	
	@Column(nullable = false)
	private Date birthdayDate;
	
	@Column(nullable = false)
	private String website;
	
	@Column(nullable = false)
	private String biography;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	public User(String name, String surname, String email, int phoneNumber, GenderEnum gender, Date birthdayDate,
			String website, String biography, String username, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.birthdayDate = birthdayDate;
		this.website = website;
		this.biography = biography;
		this.username = username;
		this.password = password;
	}
	
}
