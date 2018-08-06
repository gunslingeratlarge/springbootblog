package cn.kvmial.blog.controller;

import cn.kvmial.blog.pojo.Post;
import cn.kvmial.blog.service.AdminService;
import cn.kvmial.blog.util.LayUIPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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

    @GetMapping("postUpload")
    public String uploadPostView() {
        return "admin/page/postUpload";
    }

    @RequestMapping("listPosts")
    @ResponseBody
    public LayUIPage<Post> listPosts(Integer page, Integer limit) {
        PageHelper.startPage(page,limit,"id desc");
        List<Post> posts = service.listPosts();
        PageInfo<Post> pageInfo = new PageInfo<>(posts);
        // 将使用pageHelper分页的结果转换为layui能接受的数据格式。
        LayUIPage<Post> layUIPage = new LayUIPage<>();
        layUIPage.setData(pageInfo.getList());
        layUIPage.setCount(pageInfo.getTotal());
        layUIPage.setLimit(limit);
        layUIPage.setPage(pageInfo.getPageNum());
        return layUIPage;
    }

    @PostMapping("uploadPost")
    @ResponseBody
    public HashMap<String,Object> uploadPost(MultipartFile file, Post post) {
        HashMap<String, Object> map = service.uploadPost(file, post);
        return map;
    }
}
