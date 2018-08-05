package cn.kvmial.blog.controller;

import cn.kvmial.blog.pojo.Post;
import cn.kvmial.blog.service.AdminService;
import cn.kvmial.blog.util.LayUIPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.LayerUI;
import java.io.File;
import java.io.IOException;
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

    @GetMapping("uploadPost")
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
    public String uploadPost(MultipartFile file) {
        System.out.println(this.getClass().getClassLoader().getResource("").getPath());
        //File newFile = new File( this.getClass().getClassLoader().getResource("/").getPath() + "/" + file.getOriginalFilename());
//        try {
//            file.transferTo(newFile);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return "success";
    }
}
