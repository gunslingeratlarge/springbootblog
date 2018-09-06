package cn.kvmial.blog.controller;

import cn.kvmial.blog.exception.TipException;
import cn.kvmial.blog.pojo.Post;
import cn.kvmial.blog.pojo.PostImage;
import cn.kvmial.blog.pojo.Result;
import cn.kvmial.blog.service.IPostService;
import cn.kvmial.blog.util.LayUIPage;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    IPostService postService;

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


    @PostMapping(value = "uploadImage", produces = {"application/json"})
    @ResponseBody
    public Result uploadPost(MultipartFile file, PostImage image) {

        if (file == null) {
            return Result.fail("文件为空");
        }
        //postService.uploadImage(file, image);
        return null;
    }



    @RequestMapping("insertPost")
    @ResponseBody
    public Result insertPost(Post post) {
        if(post.getTitle() == null) {
            return Result.fail("必填项为空");
        }
        try {
            postService.insertPost(post);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
        return Result.ok("插入成功");
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

    @GetMapping("postInsert")
    public String insertPostView() {
        return "admin/page/postInsert";
    }

    @RequestMapping("listPosts")
    @ResponseBody
    public LayUIPage<Post> listPosts(Integer page, Integer limit) {
        PageHelper.startPage(page,limit,"id desc");
        List<Post> posts = postService.listPosts(null);
        PageInfo<Post> pageInfo = new PageInfo<>(posts);
        // 将使用pageHelper分页的结果转换为layui能接受的数据格式。
        LayUIPage<Post> layUIPage = new LayUIPage<>();
        layUIPage.setData(pageInfo.getList());
        layUIPage.setCount(pageInfo.getTotal());
        layUIPage.setLimit(limit);
        layUIPage.setPage(pageInfo.getPageNum());
        return layUIPage;
    }


    /**y
     * 读取post的markdown文件，单独的接口
     * @param id 读取哪个post
     * @return
     */
    @RequestMapping("getMarkdown")
    @ResponseBody
    public Result getMarkdown(Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (id == null) {
            return Result.fail("id不能为空");
        } else {
            String markdown;
            try {
                markdown = postService.getMarkdown(id);
            } catch (Exception e) {
                return Result.fail(e.getMessage());
            }
            return Result.ok(markdown);
        }
    }

    @RequestMapping(value="getPost")
    @ResponseBody
    public Result getPost(Integer id) {
        Post post = null;
        try {
            post = postService.getPost(id);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof TipException) {
               return Result.fail(e.getMessage());
            } else {
                return Result.fail("获取post错误");
            }
        }
        return Result.ok(post);
    }


    @RequestMapping("updatePost")
    @ResponseBody
    public Result updatePost(Post post,String markdown) {
        try {
            postService.updatePost(post,markdown);
            return Result.ok("更新文章成功");
        } catch (IOException | TipException e) {
            e.printStackTrace();
            if (e instanceof TipException) {
                return Result.fail(e.getMessage());
            } else {
                return Result.fail("updatePost失败");
            }
        }
    }

    /**
     * 删除post
     * @param ids 需要删除的id，用逗号分隔
     * @return 结果
     */
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
