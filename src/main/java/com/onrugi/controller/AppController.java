package com.onrugi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onrugi.dao.DictionaryDao;
import com.onrugi.dao.UserDao;
import com.onrugi.entity.Dictionary;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	UserDao userDao;

	@Autowired
	DictionaryDao dictDao;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("message", "Spring MVC Java Configuration Example");
		return "index";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();

		Object role = session.getAttribute("role");
		if (role == null)
			return "login";
		else {
			model.addAttribute("select", "");
			model.addAttribute("seach", "");
			return "user";
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String showUser(HttpServletRequest request, @RequestParam("select") String select,
			@RequestParam("seach") String seach, Model model) {
		if (select.equals("1")) {
			Dictionary td = dictDao.dictionary(seach, 1);
			if (td != null) {
				String tieng_v = td.getTiengviet();
				String tieng_a = td.getTienganh();
				model.addAttribute("tiengv", tieng_v);
				model.addAttribute("tienga", tieng_a);
				model.addAttribute("stt", "ta");
			}
		} else if (select.equals("2")) {
			Dictionary td = dictDao.dictionary(seach, 2);
			if (td != null) {
				String tieng_v = td.getTiengviet();
				String tieng_a = td.getTienganh();
				model.addAttribute("tiengv", tieng_v);
				model.addAttribute("tienga", tieng_a);
				model.addAttribute("stt", "tv");
			}
		}
		return "user";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();

		Object role = session.getAttribute("role");
		if (role == null)
			return "login";
		else {
			model.addAttribute("selected", "");
			model.addAttribute("search", "");
			return "admin";
		}
	}

	@RequestMapping(value = "/listword", method = RequestMethod.GET)
	public String showAdmin(@RequestParam("selected") String selected, @RequestParam("search") String search,
			Model model) {
		if (selected.equals("1")) {
			List<Dictionary> listDic = dictDao.searchAdmin(search, 1);
			model.addAttribute("listDic", listDic);
			model.addAttribute("check", "1");
		} else if (selected.equals("2")) {
			List<Dictionary> listDic = dictDao.searchAdmin(search, 2);
			model.addAttribute("listDic", listDic);
			model.addAttribute("check", "2");
		}
		model.addAttribute("select", selected);
		model.addAttribute("search", search);
		return "admin";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id_word") int number, @RequestParam("selected") String selected,
			@RequestParam("search") String search, Model model) {
		boolean check = dictDao.deleteWordById(number);
		if (check) {
			model.addAttribute("mess", "Xoa thanh cong");
		} else {
			model.addAttribute("mess", "Xoa that bai");
		}
		System.out.println(number);
		return "redirect:/listword?selected=" + selected + "&search=" + search;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam int id, @RequestParam String selected,
			@RequestParam String search,@RequestParam String tieng, Model model) {
		System.out.println(id);
		System.out.println(selected);
		System.out.println(search);
		System.out.println(tieng);
		if(selected.equals("1")) {
			boolean temp = dictDao.updateWordMeaning(id, tieng, 1);
			if(temp) {
				model.addAttribute("update_success", "Cap nhat thanh cong!");
			}else {
				model.addAttribute("update_success", "Cap nhat that bai!");
			}
		}else {
			boolean temp = dictDao.updateWordMeaning(id, tieng, 2);
			if(temp) {
				model.addAttribute("update_success", "Cap nhat thanh cong!");
			}else {
				model.addAttribute("update_success", "Cap nhat that bai!");
			}
		}
		return "redirect:/listword?selected=" + selected + "&search=" + search;
	}

	@RequestMapping(value = "/popup", method = RequestMethod.GET)
	public String popup(@RequestParam("id_word") int number, @RequestParam("selected") String selected,
			@RequestParam("search") String search, Model model) {
		System.out.println(number);
		System.out.println(search);
		System.out.println(selected);
		Dictionary dictionary = dictDao.edit(number);
		if (selected.equals("1")) {
			model.addAttribute("before", dictionary.getTienganh());
			model.addAttribute("after", dictionary.getTiengviet());
		} else {
			model.addAttribute("before", dictionary.getTiengviet());
			model.addAttribute("after", dictionary.getTienganh());
		}
		return "redirect:/listword?selected=" + selected + "&search=" + search;
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();

		Object role = session.getAttribute("role");
		if (role == null)
			return "login";
		else {
			model.addAttribute("dict", new Dictionary());
			return "add";
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addDict(@ModelAttribute("dict") Dictionary dictionary, Model map) {
		String message = "";
		System.out.println(dictionary.getTienganh());
		dictDao.addWord(dictionary);
		message = "Tao moi thanh cong";
		map.addAttribute("contentMessage", message);
		return "add";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession();

		Object role = session.getAttribute("role");
		if (role == null)
			return "login";
		else {
			if (role.equals(1)) {
				return "user";
			} else {
				return "admin";
			}
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(@RequestParam("name") String name, @RequestParam("password") String password,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		Object currentRole = session.getAttribute("role");
		if (currentRole == null) {
			System.out.println(name + password);
			Integer role = userDao.loginUser(name, password);
			System.out.println(role);
			if (role == null) {
				String msgRole = new StringBuilder().append("Sorry ").append(name).append(". Incorrect password")
						.toString();
				session.setAttribute("mess", msgRole);
				return "login";
			} else {
				session.setAttribute("role", role);
				session.setAttribute("name", name);
				if (role == 1) {
					return "user";
				} else {
					return "admin";
				}
			}
		}
		Integer role = userDao.loginUser(name, password);
		if (role.equals("1"))
			return "user";
		return "admin";

	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("role");
		session.removeAttribute("name");
		session.removeAttribute("mess");
		return "login";
	}

}