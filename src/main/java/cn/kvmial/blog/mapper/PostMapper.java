package cn.kvmial.blog.mapper;

import cn.kvmial.blog.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * TODO
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/3 0003 下午 13:19
 */

@Mapper
public interface PostMapper {

    /**
     * 查询所有的文章
     * @return 所有的文章
     */
    List<Post> listPosts();

    /**
     * 根据文章的名称完全匹配查询
     * @param title 文章名
     * @return 文章
     */
    Post getPostByTitle(String title);

    /**
     * 根据文章的markdown路径完全匹配查询
     * @param path 文章的markdown路径
     * @return 文章
     */
    Post getPostByPath(String path);

    /**
     * 根据文章id匹配
     * @param id 文章id
     * @return 文章
     */
    Post getPostById(Integer id);

    /**
     * 查询文章，根据多个字段模糊查询
     * @param post 文章
     * @return 文章列表
     */
    List<Post> listPost(Post post);

    /**
     * 插入文章信息到db
     * @param post 文章
     * @return 插入值
     */
    int insertPost(Post post);
}
