package cn.kvmial.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping
    public String indexView() {
        return "admin/index";
    }

    @GetMapping(value = "login")
    public String loginView() {
        return "admin/login";
    }
}
