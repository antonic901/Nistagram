package nistagram.storyservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("UserProfile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "userProfile")
	 private Set<Story> stories = new HashSet<Story>();
	 
	 @OneToMany(fetch = FetchType.LAZY)
	 private Set<HighLight> highLights = new HashSet<HighLight>();
	 
}
