package cn.kvmial.blog.controller;

import cn.kvmial.blog.pojo.Post;
import cn.kvmial.blog.service.AdminService;
import cn.kvmial.blog.util.LayUIPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.plaf.LayerUI;
import java.util.List;

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
    AdminService service;

    @GetMapping
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

    @RequestMapping("listPosts")
    @ResponseBody
    public List<Post> listPosts() {
        List<Post> posts = service.listPosts();
        return posts;
    }
}
