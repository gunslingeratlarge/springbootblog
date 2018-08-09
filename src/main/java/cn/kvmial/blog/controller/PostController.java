package cn.kvmial.blog.controller;

import cn.kvmial.blog.exception.TipException;
import cn.kvmial.blog.pojo.Post;
import cn.kvmial.blog.pojo.Result;
import cn.kvmial.blog.service.PostService;
import cn.kvmial.blog.util.LayUIPage;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/9 0009 上午 10:17
 */

@Controller
@RequestMapping("/admin/post")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping(value = "uploadPost", produces = {"application/json"})
    @ResponseBody
    public JSONObject uploadPost(MultipartFile file, Post post) {
        if (post.getSticky() == null) {
            post.setSticky(false);
        }
        JSONObject jsonObject = new JSONObject();
        if (file == null) {
            jsonObject.put("success",false);
            jsonObject.put("msg", "文件为空");
        }
        jsonObject = postService.uploadPost(file, post);
        return jsonObject;
    }

    @GetMapping("editor")
    public String editorView(Integer id,Model m) {
        m.addAttribute("id",id);
        return "admin/page/editor";
    }

    @GetMapping("postUpload")
    public String uploadPostView() {
        return "admin/page/postUpload";
    }

    @RequestMapping("listPosts")
    @ResponseBody
    public LayUIPage<Post> listPosts(Integer page, Integer limit) {
        PageHelper.startPage(page,limit,"id desc");
        List<Post> posts = postService.listPosts();
        PageInfo<Post> pageInfo = new PageInfo<>(posts);
        // 将使用pageHelper分页的结果转换为layui能接受的数据格式。
        LayUIPage<Post> layUIPage = new LayUIPage<>();
        layUIPage.setData(pageInfo.getList());
        layUIPage.setCount(pageInfo.getTotal());
        layUIPage.setLimit(limit);
        layUIPage.setPage(pageInfo.getPageNum());
        return layUIPage;
    }



    @RequestMapping("getMarkdown")
    @ResponseBody
    public JSONObject getMarkdown(Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (id == null) {
            jsonObject.put("success",false);
            jsonObject.put("msg", "id不能为空");
            return jsonObject;
        } else {
            return postService.getMarkdown(id);
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Result batchDeletePosts(@RequestParam(value = "ids") String ids) {
        String[] strs = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String str : strs) {
            idList.add(Integer.parseInt(str));
        }

        if (idList.size() == 0 ){
            return Result.fail("没有要删除的id");
        }

        try {
            postService.batchDeletePosts(idList);

        } catch (Exception e) {
            String msg = "删除失败";
            e.printStackTrace();
            if (e instanceof TipException) {
                msg = e.getMessage();
            }
            return Result.fail(msg);
        }
        return Result.ok();
    }
}
