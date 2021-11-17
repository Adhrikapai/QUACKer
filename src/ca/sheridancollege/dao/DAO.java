package ca.sheridancollege.dao;

import java.util.List;

import ca.sheridancollege.beans.Comments;
import ca.sheridancollege.beans.Posts;
import ca.sheridancollege.beans.Users;

public interface DAO {
	
	public void populate();
	
	public void insertUsers(Users users);
	public List<Users> getUsersList();
	public List<Users> getUsersByName(String fnames);
	public List<Users> getUserEmailAndPass(String email, String password);
	public List<Users> getUserNameByEmail(String email);
	public List<Users> getUserById(Long id);
	public void updateUserLikes(Long id);
	public void updateUserDislikes(Long id);
	public void updateUserLikesDown(Long id);
	public void updateUserDislikesDown(Long id);
	public List<Users> rateByULikes();
	public List<Users> rateByUDislikes();
	
	public List<Posts> getPostsList();
	public void insertPosts(Posts posts);
	public void updateLikes(Long postId);
	public void updateDislikes(Long postId);
	public void updateLikesDown(Long postId);
	public void updateDislikesDown(Long postId);
	public List<Posts> rateByLikes();
	public List<Posts> rateByDislikes();
	public List<Posts> queryComments(Long postId);
	public List<Posts> getMusicList(String category);
	public List<Posts> getTechList(String category);
	public List<Posts> getHebeList(String category);
	public List<Posts> getPolList(String category);
	public List<Posts> getEduList(String category);
	public List<Posts> getRelList(String category);
	public List<Posts> searchByHeading(String heading);
	
	public void insertComments(Comments comments);
	public void deleteUser(Long id);
	
}
