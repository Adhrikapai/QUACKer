package ca.sheridancollege.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.sheridancollege.beans.Comments;
import ca.sheridancollege.beans.Posts;
import ca.sheridancollege.beans.Users;

public class QuestionsDAO implements DAO{
	SessionFactory sessionFactory= new Configuration()
			.configure("ca/sheridancollege/config/hibernate.cfg.xml")
			.buildSessionFactory();
	
	public void populate() {
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		
		Users temp1 = new Users("Adhrika", "Pai", "paiadh", "123", 20, 40);
		session.save(temp1);
		/*
		 * Posts temp2 = new
		 * Posts("SPOILER ALERT! What happens to the night king in Game of Thrones",
		 * "The night king dies in the final battle making sure that Jon Snow would be the new night king ruling over all of Westeros"
		 * , "Music & Movies", 5000, 600); session.save(temp2);
		 */
		/*
		 * Posts temp4 = new Posts("HOMi app for property/rental managament",
		 * "Finally I came up with a brilliant idea for any capstone projects out there! An App for both landlords and tenants to make their lives easier"
		 * , "Education", 90, 50); session.save(temp4);
		 */
		Users temp5 = new Users("Samir", "Nasser", "nassersa", "123", 0, 0);
		session.save(temp5);
		Users temp6 = new Users("Sneh", "Singh", "snehsingh", "123", 400, 40);
		session.save(temp6);
		Users temp7 = new Users("Mika", "Icmat", "icmat", "123", 20, 0);
		session.save(temp7);
		
		session.getTransaction().commit();
		session.close();
	}

	public void insertUsers(Users users) {
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		session.saveOrUpdate(users);
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Users> getUsersList(){
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		Query query = session.createNamedQuery("Users.getUsersList");
		List<Users> usersList = query.getResultList();	
		session.getTransaction().commit();
		session.close();	
		return usersList;
	}
	
	public List<Users> getUsersByName(String fname){
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		Query query = session.createNamedQuery("Users.getUsersByName");
		query.setParameter("fname", fname);
		List<Users> usersList = query.getResultList();	
		session.getTransaction().commit();
		session.close();	
		return usersList;
	}
	
	public List<Users> getUserEmailAndPass(String email, String password) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("Users.getUserEmailAndPass");
		query.setParameter("email", email);
		query.setParameter("password", password);
		List<Users> usersList = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return usersList;
	}
	
	public List<Users> getUserNameByEmail(String email) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("Users.getUserNameByEmail");
		query.setParameter("email", email);
		List<Users> userEmail = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return userEmail;
	}
	
	public List<Users> getUserById(Long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("Users.getUserById");
		query.setParameter("id", id);
		List<Users> usersList = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return usersList;
	}
	
	public List<Posts> getPostsList(){
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		Query query = session.createNamedQuery("Posts.getPostsList");
		List<Posts> postsList = query.getResultList();	
		session.getTransaction().commit();
		session.close();	
		return postsList;
	}
	
	public void insertPosts(Posts posts) {
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		session.saveOrUpdate(posts);
		session.getTransaction().commit();
		session.close();
	}
	
	
	public void updateLikes(Long postId){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createNamedQuery("Posts.updateLikes");
		query.setParameter("postId", postId);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();	
	}
	
	public void updateLikesDown(Long postId){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createNamedQuery("Posts.updateLikesDown");
		query.setParameter("postId", postId);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();	
	}
	
	public void updateUserLikes(Long id){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createNamedQuery("Users.updateUserLikes");
		query.setParameter("id", id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();	
	}
	
	public void updateUserLikesDown(Long id){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createNamedQuery("Users.updateUserLikesDown");
		query.setParameter("id", id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();	
	}
	
	public void updateDislikes(Long postId){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createNamedQuery("Posts.updateDislikes");
		query.setParameter("postId", postId);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();	
	}
	
	public void updateDislikesDown(Long postId){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createNamedQuery("Posts.updateDislikesDown");
		query.setParameter("postId", postId);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();	
	}
	
	public void updateUserDislikes(Long id){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createNamedQuery("Users.updateUserDislikes");
		query.setParameter("id", id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();	
	}
	
	public void updateUserDislikesDown(Long id){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createNamedQuery("Users.updateUserDislikesDown");
		query.setParameter("id", id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();	
	}
	
	public void insertComments(Comments comments) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(comments);
		session.getTransaction().commit();
		session.close();
	}

	public List<Posts> queryComments(Long postId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query= session.getNamedQuery("Posts.CommentsById");
		query.setParameter("postId", postId);
		List<Posts> commentsList = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return commentsList;
	}
	
	public List<Posts> rateByLikes() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query= session.getNamedQuery("Posts.rateByLikes");
		List<Posts> postsLikes = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return postsLikes;
	}
	
	public List<Users> rateByULikes() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query= session.getNamedQuery("Users.rateByULikes");
		List<Users> usersLikes = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return usersLikes;
	}
	
	public List<Posts> rateByDislikes() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query= session.getNamedQuery("Posts.rateByDislikes");
		List<Posts> postsDislikes = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return postsDislikes;
	}
	
	public List<Users> rateByUDislikes() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query= session.getNamedQuery("Users.rateByUDislikes");
		List<Users> usersDislikes = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return usersDislikes;
	}
	
	public void deleteUser(Long id){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
		Users toDelete = session.get(Users.class, id);
		session.delete(toDelete);
				
		session.getTransaction().commit();
		session.close();

	}
	
	public List<Posts> getMusicList(String category){
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		Query query = session.createNamedQuery("Posts.getMusicList");
		query.setParameter("category", category);
		List<Posts> postsList = query.getResultList();	
		session.getTransaction().commit();
		session.close();	
		return postsList;
	}
	
	public List<Posts> getTechList(String category){
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		Query query = session.createNamedQuery("Posts.getTechList");
		query.setParameter("category", category);
		List<Posts> postsList = query.getResultList();	
		session.getTransaction().commit();
		session.close();	
		return postsList;
	}
	
	public List<Posts> getHebeList(String category){
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		Query query = session.createNamedQuery("Posts.getHebeList");
		query.setParameter("category", category);
		List<Posts> postsList = query.getResultList();	
		session.getTransaction().commit();
		session.close();	
		return postsList;
	}
	
	public List<Posts> getEduList(String category){
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		Query query = session.createNamedQuery("Posts.getEduList");
		query.setParameter("category", category);
		List<Posts> postsList = query.getResultList();	
		session.getTransaction().commit();
		session.close();	
		return postsList;
	}
	
	public List<Posts> getPolList(String category){
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		Query query = session.createNamedQuery("Posts.getPolList");
		query.setParameter("category", category);
		List<Posts> postsList = query.getResultList();	
		session.getTransaction().commit();
		session.close();	
		return postsList;
	}
	
	public List<Posts> getRelList(String category){
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		Query query = session.createNamedQuery("Posts.getRelList");
		query.setParameter("category", category);
		List<Posts> postsList = query.getResultList();	
		session.getTransaction().commit();
		session.close();	
		return postsList;
	}
 
	public List<Posts> searchByHeading(String heading){
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		Query query = session.createNamedQuery("Posts.searchByHeading");
		query.setParameter("heading", heading);
		List<Posts> postsList = query.getResultList();	
		session.getTransaction().commit();
		session.close();	
		return postsList;
	}

	
}
	
	

