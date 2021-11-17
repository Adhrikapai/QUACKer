package ca.sheridancollege.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comments {

	@Id
	@GeneratedValue
	private Long commentId;
	private String comment;
	private String userName;
	
	public Comments(String comment, String userName) {
		super();
		this.comment = comment;
		this.userName = userName;
	}
	
	
}
