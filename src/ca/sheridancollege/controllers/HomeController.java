package ca.sheridancollege.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.dao.DAO;
import ca.sheridancollege.dao.QuestionsDAO;
import ca.sheridancollege.beans.*;

@Controller
public class HomeController {

	private DAO dao;
	private Questions questions = new Questions();

	@Autowired
	public HomeController(QuestionsDAO questionsDAO) {
		super();
		this.dao = questionsDAO;
		dao.populate();
	}

	@GetMapping("/")
	public String login(Model model) {
		Users users = new Users();
		model.addAttribute("users", users);
		return "login";

	}

	@PostMapping("saveUsers")
	public String saveUsers(Model model, @ModelAttribute Users users, @RequestParam String email) {
		dao.insertUsers(users);
		model.addAttribute("usersList", dao.getUsersList());
		List<Users> userName = dao.getUserNameByEmail(email);
		model.addAttribute("userName", userName);
		System.out.println(userName);
		model.addAttribute("posts", dao.getPostsList());
		return "home";
	}

	@GetMapping("editUser/{id}")
	public String editUser(Model model, @PathVariable Long id, @ModelAttribute Users users) {

		List<Users> user = dao.getUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("usersList", dao.getUsersList());
		return "editProfile";
	}

	@GetMapping("deleteUser/{id}")
	public String deleteUser(Model model, @PathVariable Long id, @ModelAttribute Users users) {

		List<Users> usersList = dao.getUsersList();
		List<Users> user = dao.getUserById(id);
		dao.deleteUser(id);
		model.addAttribute("usersList", usersList);
		model.addAttribute("user", user);
		return "redirect:/logout";
	}

	@PostMapping("signInUsers")
	public String signInUsers(Model model, @RequestParam String email, @RequestParam String password,
			@ModelAttribute Users usersList) {
		List<Users> user = dao.getUserEmailAndPass(email, password);
		List<Users> userName = dao.getUserNameByEmail(email);
		model.addAttribute("userName", userName);
		model.addAttribute("user", user);

		if (userName.size() == 0) {
			model.addAttribute("passwordError", "WrongPassword");
			return "login";
		}
		
		for (int i = 0; i < userName.size(); i++) {
			String dbEmail = userName.get(i).getEmail();
			String dbPass = userName.get(i).getPassword();
			System.out.println("Test getters " + dbEmail + " " + dbPass);
			System.out.println("Error message: \n");

			if (!email.equals(dbEmail) || !password.equals(dbPass)) {
				System.out.println("Form email = " + email + "\t DB Email = " + dbEmail);
				System.out.println("Form pass = " + password + "\t DB Password = " + dbPass);
				model.addAttribute("passwordError", "WrongPassword");
			} else {
				model.addAttribute("posts", dao.getPostsList());
				System.out.println("LOGIN TO HOME");
				System.out.println("Test getters " + userName.get(i).getEmail());
				return "home";
			}
		}

		return "login";
	}

	@GetMapping("home/{email}")
	public String homePage(HttpServletRequest request, Model model, @PathVariable String email) {
		List<Users> userName = dao.getUserNameByEmail(email);
		model.addAttribute("userName", userName);
		model.addAttribute("posts", dao.getPostsList());
		return "home";
	}

	@GetMapping("profile/{id}")
	public String profile(HttpServletRequest request, Model model, @PathVariable Long id) {
		System.out.println(id);
		List<Users> usersList = dao.getUserById(id);
		model.addAttribute("usersList", usersList);
		System.out.println(usersList);
		return "profile";
	}

	@GetMapping("logout")
	public String logout(Model model) {
		Users users = new Users();
		model.addAttribute("users", users);
		return "login";
	}

	@GetMapping("voteLike/{postId}/{id}")
	public String voteLike(Model model, @PathVariable Long postId, @PathVariable Long id) {
		System.out.println("Upvote clicked");
		dao.updateLikes(postId);
		dao.updateUserLikes(id);
		dao.updateDislikesDown(postId);
		dao.updateUserDislikesDown(id);
		model.addAttribute("userName", dao.getUserById(id));
		model.addAttribute("posts", dao.getPostsList());
		model.addAttribute("liked", true);
		return "home";
	}

	@GetMapping("voteDislike/{postId}/{id}")
	public String voteDislike(Model model, @PathVariable Long id, @PathVariable Long postId) {
		System.out.println("Downvote clicked");
		dao.updateDislikes(postId);
		dao.updateUserDislikes(id);
		dao.updateLikesDown(postId);
		dao.updateUserLikesDown(id);
		model.addAttribute("userName", dao.getUserById(id));
		model.addAttribute("posts", dao.getPostsList());
		model.addAttribute("disliked", true);
		return "home";
	}

	@GetMapping("addComment/{postId}/{id}/{userName}")
	public String addComment(Model model, @PathVariable Long postId, @PathVariable Long id,
			@PathVariable String userName) {
		Comments comments = new Comments();
		System.out.println(postId);
		List<Users> usersList = dao.getUserById(id);
		model.addAttribute("userName", userName);
		model.addAttribute("usersList", usersList);
		model.addAttribute("posts", dao.queryComments(postId));
		model.addAttribute("com", comments);
		return "comment";
	}

	@GetMapping("addPost/{userName}")
	public String addPost(Model model, @PathVariable String userName) {
		List<Users> users = dao.getUsersByName(userName);
		if (!users.isEmpty()) {
			model.addAttribute("userName", userName);
		}
		Posts posts = new Posts();
		model.addAttribute("posts", posts);
		model.addAttribute("users", users);
		return "post";
	}

	@ModelAttribute("categoryList")
	public Map<String, String> getCountryList() {
		Map<String, String> categoryList = new HashMap<String, String>();
		categoryList.put("Music & Movies", "Music & Movies");
		categoryList.put("Technology", "Technology");
		categoryList.put("Health & Beauty", "Health & Beauty");
		categoryList.put("Politics", "Politics");
		categoryList.put("Education", "Education");
		categoryList.put("Relationships", "Relationships");
		return categoryList;
	}

	@PostMapping("savePost/{userName}")
	public String savePost(Model model, @ModelAttribute Posts posts, HttpSession session,
			@PathVariable String userName) {

		List<Users> users = dao.getUsersList();
		Long userId = null;
		if (userName != null) {
			for (int i = 0; i < users.size(); i++) {
				if (userName.equals(users.get(i).getFname())) {
					dao.insertPosts(posts);
					users.get(i).getPosts().add(posts);
					dao.insertUsers(users.get(i));
					userId = users.get(i).getId();
				}
			}
		}
		model.addAttribute("userName", dao.getUserById(userId));
		model.addAttribute("posts", dao.getPostsList());
		return "home";
	}

	@PostMapping("insertComments/{postId}/{id}/{userName}")
	public String insertComments(Model model, @ModelAttribute Comments comments, HttpSession session,
			@PathVariable Long postId, @PathVariable Long id, @PathVariable String userName) {
		System.out.println(postId);
		List<Posts> posts = dao.getPostsList();
		if (postId != 0) {
			for (int i = 0; i < posts.size(); i++) {
				if (postId == posts.get(i).getPostId()) {
					dao.insertComments(comments);
					posts.get(i).getCommentsList().add(comments);
					dao.insertPosts(posts.get(i));
				}
			}
		}
		model.addAttribute("usersList", dao.getUserById(id));
		model.addAttribute("userName", userName);
		model.addAttribute("posts", dao.queryComments(postId));
		model.addAttribute("com", comments);
		return "comment";
	}

	@GetMapping("scores/{id}")
	public String scores(Model model, @PathVariable Long id) {
		model.addAttribute("userName", dao.getUserById(id));
		model.addAttribute("postsLikes", dao.rateByLikes());
		model.addAttribute("postsDislikes", dao.rateByDislikes());
		model.addAttribute("usersLikes", dao.rateByULikes());
		model.addAttribute("usersDislikes", dao.rateByUDislikes());
		return "score";
	}

	@GetMapping("musicSearch/{id}")
	public String musicSearch(Model model, @PathVariable Long id) {
		String category = "Music & Movies";
		model.addAttribute("userName", dao.getUserById(id));
		model.addAttribute("posts", dao.getMusicList(category));
		return "home";
	}

	@GetMapping("techSearch/{id}")
	public String techSearch(Model model, @PathVariable Long id) {
		String category = "Technology";
		model.addAttribute("userName", dao.getUserById(id));
		model.addAttribute("posts", dao.getTechList(category));
		return "home";
	}

	@GetMapping("hebeSearch/{id}")
	public String hebeSearch(Model model, @PathVariable Long id) {
		String category = "Health & Beauty";
		model.addAttribute("userName", dao.getUserById(id));
		model.addAttribute("posts", dao.getHebeList(category));
		return "home";
	}

	@GetMapping("polSearch/{id}")
	public String polSearch(Model model, @PathVariable Long id) {
		String category = "Politics";
		model.addAttribute("userName", dao.getUserById(id));
		model.addAttribute("posts", dao.getPolList(category));
		return "home";
	}

	@GetMapping("eduSearch/{id}")
	public String eduSearch(Model model, @PathVariable Long id) {
		String category = "Education";
		model.addAttribute("userName", dao.getUserById(id));
		model.addAttribute("posts", dao.getEduList(category));
		return "home";
	}

	@GetMapping("relSearch/{id}")
	public String relSearch(Model model, @PathVariable Long id) {
		String category = "Relationships";
		model.addAttribute("userName", dao.getUserById(id));
		model.addAttribute("posts", dao.getRelList(category));
		return "home";
	}

	@GetMapping("search/{id}")
	public String Search(Model model, @PathVariable Long id, HttpServletRequest request) {
		String heading = request.getParameter("searchHeading");
		System.out.println(heading);
		if (heading != null) {
			System.out.println(heading);
			model.addAttribute("posts", dao.searchByHeading(heading));
			System.out.println(dao.searchByHeading(heading));
			model.addAttribute("userName", dao.getUserById(id));

		}
		return "home";
	}

}
