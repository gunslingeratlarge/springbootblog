package cn.kvmial.blog.controller;

import cn.kvmial.blog.pojo.Post;
import cn.kvmial.blog.pojo.Result;
import cn.kvmial.blog.service.IPostService;
import cn.kvmial.blog.util.LayUIPage;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/1 0001 下午 15:59
 */


@Controller
public class IndexController {
    @Autowired
    private IPostService postService;

    /**
     * 这里只是找到视图，具体的数据请求使用ajax请求PostController
     * @return
     */
    @RequestMapping("/index")
    public String index(Integer currPage,Model model){
        if (currPage == null) {
            currPage = 1;
        }
        model.addAttribute("currPage", currPage);
        return "index";
    }

    @RequestMapping("/index/admin")
    public String admin() {
        return "admin/index";
    }

    @PostMapping("/listPosts")
    @ResponseBody
    public LayUIPage<Post> listPosts(Integer page, Integer limit) {
        if (page == null) {
            page = 1;
        }
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

    @RequestMapping("/getMarkdown")
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

}
