package cn.kvmial.blog.service;

import cn.kvmial.blog.pojo.Category;

import java.util.List;

/**
 *
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/15 0015 下午 13:56
        */


public interface ICategoryService {

    /**
     * 删除某一个分类和它下面的子分类，所有在此分类下的文章都改为其父分类
     * @param id 要删除的分类id
     */
    void deleteCategory(Integer id);

    void updateCategory(Category category);

    void insertCategory(Category category);

    List<Category> listCategories(Category category);
}
