package cn.kvmial.blog.mapper;

import cn.kvmial.blog.pojo.Category;
import cn.kvmial.blog.pojo.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/15 0003 下午 13:43
 */

@Mapper
public interface CategoryMapper {

    /**
     * 获得某个分类下的直接子分类
     * @param id 分类的id
     * @return 直接子分类id的列表
     */
    List<Integer> listSubCategories(Integer id);

    /**
     * 批量删除分类
     * @param ids 分类的list
     * @return 删除的条数
     */
    int batchDeleteCategories(List<Integer> ids);

    /**
     * 添加分类
     * @param category 分类信息，本身不带id
     */
    void insertCategory(Category category);

    /**
     * 更新分类
     * @param category 分类信息，本身带id
     */
    void updateCategory(Category category);

    /**
     * 获取所有分类信息
     * @return 分类
     */
    List<Category> listCategories(Category category);


}
