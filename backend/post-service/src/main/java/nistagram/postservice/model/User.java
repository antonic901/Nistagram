package nistagram.postservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	 
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "userProfile")
	 private Set<Post> posts = new HashSet<Post>();
	 
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "userProfile")
	 private Set<Collection> collections = new HashSet<Collection>();
	 
}
