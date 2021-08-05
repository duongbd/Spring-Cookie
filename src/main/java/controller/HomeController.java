package controller;

import dao.UserDao;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @GetMapping("")
    public String getHome(@CookieValue(value = "userEmail", defaultValue = "") String userEmail,@CookieValue(value = "userPass", defaultValue = "") String userPass, ModelMap modelMap) {
        User user =new User();
        user.setEmail(userEmail);
        user.setPassword(userPass);
        if (UserDao.checkLogin(user)) {
            modelMap.addAttribute("user", user);
            return "loginSuccess";
        }
        modelMap.addAttribute("user", new User());
        return "home";
    }

    @PostMapping("")
    public String login(@ModelAttribute("user") User user, HttpServletResponse response, ModelMap modelMap) {
        if (UserDao.checkLogin(user)) {
            Cookie cookie = new Cookie("userEmail", user.getEmail());
            cookie.setMaxAge(60);
            response.addCookie(cookie);
            Cookie cookie1 = new Cookie("userPass", user.getPassword());
            cookie1.setMaxAge(60);
            response.addCookie(cookie1);
            return "loginSuccess";
        } else
            modelMap.addAttribute("message", "Login failed. Try again.");
        return "home";
    }
}
