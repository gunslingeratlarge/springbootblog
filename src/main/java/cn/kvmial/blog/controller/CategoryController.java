package cn.kvmial.blog.controller;

import cn.kvmial.blog.exception.TipException;
import cn.kvmial.blog.pojo.Category;
import cn.kvmial.blog.pojo.Post;
import cn.kvmial.blog.pojo.Result;
import cn.kvmial.blog.service.ICategoryService;
import cn.kvmial.blog.util.LayUIPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 后台分类控制器
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/15 0015 下午 13:55
 */

@Controller
@RequestMapping("admin/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    public Result deleteCategory(Integer id) {
        if (id == null) {
            return Result.fail("id不能为空");
        }
        try {
            categoryService.deleteCategory(id);
        } catch (Exception e) {
            e.printStackTrace();
            // 设置需要回滚异常
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.fail(e.getMessage());
        }
        return Result.ok("删除分类成功");
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Result updateCategory(Category category) {
        try {
            if (category.getId() == null) {
                categoryService.insertCategory(category);
            } else {
                categoryService.updateCategory(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }

        return Result.ok("更新（插入）成功");
    }

    @RequestMapping("list")
    @ResponseBody
    public LayUIPage<Category> listCategories(@RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "10") Integer limit) {
        PageHelper.startPage(page,limit,"id desc");
        List<Category> categories = categoryService.listCategories(null);
        PageInfo<Category> pageInfo = new PageInfo<>(categories);

        // 将使用pageHelper分页的结果转换为layui能接受的数据格式。
        LayUIPage<Category> layUIPage = new LayUIPage<>();
        layUIPage.setData(pageInfo.getList());
        layUIPage.setCount(pageInfo.getTotal());
        layUIPage.setLimit(limit);
        layUIPage.setPage(pageInfo.getPageNum());
        return layUIPage;
    }
}
