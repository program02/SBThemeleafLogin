package com.exam.crud;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
	private UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@GetMapping("/login")
	public String userSearchShow(User user) {
		return "userLogin";
	}

	@PostMapping("/login")
	public String userSearch(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "error";
		}

		List<User> list = userRepository.findByUseridAndPassword(user.getUserid(),user.getPassword());
		if (list.size() < 1) {
			return "errorNoData";
		} else {
			model.addAttribute("users", list);
			return "welcome";
		}

	}
}
