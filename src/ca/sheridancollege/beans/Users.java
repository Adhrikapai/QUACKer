package ca.sheridancollege.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Users.getUsersList", query = "from Users")
@NamedQuery(name = "Users.getUsersByName", query = "from Users where fname=:fname")
@NamedQuery(name = "Users.getUserEmailAndPass", query = "from Users where email=:email and password=:password")
@NamedQuery(name = "Users.getUserNameByEmail", query = "from Users where email=:email")
@NamedQuery(name = "Users.getUserById", query = "from Users where id=:id")
@NamedQuery(name = "Users.updateUserLikes", query = "update Users set uLikes = uLikes + 1 where id=:id")
@NamedQuery(name = "Users.updateUserDislikes", query = "update Users set uDislikes = uDislikes + 1 where id=:id")
@NamedQuery(name = "Users.updateUserLikesDown", query = "update Users set uLikes = uLikes - 1 where id=:id")
@NamedQuery(name = "Users.updateUserDislikesDown", query = "update Users set uDislikes = uDislikes - 1 where id=:id")
@NamedQuery(name = "Users.rateByULikes", query = "from Users order by uLikes desc")
@NamedQuery(name = "Users.rateByUDislikes", query = "from Users order by uDislikes desc")
public class Users {

	@Id
	@GeneratedValue
	private Long id;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private int uLikes;
	private int uDislikes;

	public Users(String fname, String lname, String email, String password, int uLikes, int uDislikes) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.uLikes = uLikes;
		this.uDislikes = uDislikes;
	}

	  @OneToMany(fetch = FetchType.EAGER)
	  private List<Posts> posts = new ArrayList<Posts>(); 
	  
	 

}
