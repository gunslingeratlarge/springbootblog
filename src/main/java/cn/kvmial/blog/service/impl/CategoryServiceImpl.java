package cn.kvmial.blog.service.impl;

import cn.kvmial.blog.exception.TipException;
import cn.kvmial.blog.mapper.CategoryMapper;
import cn.kvmial.blog.mapper.PostMapper;
import cn.kvmial.blog.pojo.Category;
import cn.kvmial.blog.service.ICategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/15 0015 下午 13:56
 */

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private PostMapper postMapper;

    @Override
    public void deleteCategory(Integer id) {
        List<Integer> sonIds = categoryMapper.listSubCategories(id);
        int size = sonIds.size();
        for (int i = 0; i < size; i++) {
            List<Integer> grandSonIds = categoryMapper.listSubCategories(sonIds.get(i));
            size += grandSonIds.size();
            sonIds.addAll(grandSonIds);
        }
        // 将id也加入到sonIds中方便一并删除了
        sonIds.add(id);

        int i = categoryMapper.batchDeleteCategories(sonIds);
        if (i != sonIds.size()) {
            throw new TipException("数据库删除条数与应删条数不一致");
        }

        Integer count = postMapper.countPostsByCids(sonIds);
        if (count > 0) {
            throw new TipException("不能够删除还有文章的分类");
        }
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    @Override
    public void insertCategory(Category category) {
        categoryMapper.insertCategory(category);
    }

    @Override
    public List<Category> listCategories(Category category) {
        List<Category> categories = categoryMapper.listCategories(category);
        return categories;
    }
}
