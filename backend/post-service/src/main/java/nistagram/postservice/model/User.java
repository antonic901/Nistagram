package nistagram.postservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	private Long id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Post> posts = new HashSet<Post>();
	 
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Collection> collections = new HashSet<Collection>();
	 
}
