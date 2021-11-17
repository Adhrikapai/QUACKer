package ca.sheridancollege.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = "Posts.getPostsList", query = "from Posts")
@NamedQuery(name = "Posts.CommentsById", query = "from Posts where postId =:postId")
@NamedQuery(name = "Posts.updateLikes", query = "update Posts set pLikes = pLikes + 1 where postId= :postId")
@NamedQuery(name = "Posts.updateDislikes", query = "update Posts set pDislikes = pDislikes + 1 where postId=:postId")
@NamedQuery(name = "Posts.updateLikesDown", query = "update Posts set pLikes = pLikes - 1 where postId= :postId")
@NamedQuery(name = "Posts.updateDislikesDown", query = "update Posts set pDislikes = pDislikes - 1 where postId=:postId")
@NamedQuery(name = "Posts.rateByLikes", query = "from Posts order by pLikes desc")
@NamedQuery(name = "Posts.rateByDislikes", query = "from Posts order by pDislikes desc")
@NamedQuery(name = "Posts.getMusicList", query = "from Posts where category =:category")
@NamedQuery(name = "Posts.getTechList", query = "from Posts where category =:category")
@NamedQuery(name = "Posts.getHebeList", query = "from Posts where category =:category")
@NamedQuery(name = "Posts.getPolList", query = "from Posts where category =:category")
@NamedQuery(name = "Posts.getEduList", query = "from Posts where category =:category")
@NamedQuery(name = "Posts.getRelList", query = "from Posts where category =:category")
@NamedQuery(name="Posts.searchByHeading", query = "from Posts where heading like concat('%',:heading,'%')")

public class Posts {

	@Id
	@GeneratedValue
	private Long postId;

	private String heading;
	private String quack;
	private String category;
	private int pLikes;
	private int pDislikes;
	private String userName;

	public Posts(String heading, String quack, String category, int pLikes, int pDislikes, String userName) {
		super();
		this.heading = heading;
		this.quack = quack;
		this.category = category;
		this.pLikes = pLikes;
		this.pDislikes = pDislikes;
		this.userName = userName;
	}
	
	@OneToMany(fetch = FetchType.EAGER) 
	 private List<Comments> commentsList = new ArrayList<Comments>();

}
