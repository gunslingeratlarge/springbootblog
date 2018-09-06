package cn.kvmial.blog.controller;

import cn.kvmial.blog.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * TODO
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/2 0002 下午 15:07
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    PostServiceImpl service;

    @GetMapping(value={"/","/index",""})
    public String indexView() {
        return "admin/index";
    }

    @GetMapping(value = "login")
    public String loginView() {
        return "admin/login";
    }

    @GetMapping(value="posts")
    public String postView() {
        return "admin/page/posts";
    }

    @GetMapping(value="category")
    public String categoryView() {
        return "admin/page/category";
    }

    @PostMapping("login")
    public String doLogin(@RequestParam String username, @RequestParam String password,
                          HttpServletRequest request, HttpServletResponse response) {
        if ("".equals(username) && "".equals(password)) {
            request.getSession().setAttribute("adminUser",username);
            return "redirect:/admin/index";
        } else {
            request.setAttribute("msg", "用户名或密码错误");
            return "admin/login";
        }
    }


}
