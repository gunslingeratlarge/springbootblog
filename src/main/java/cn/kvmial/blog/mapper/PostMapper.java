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
    List<Post> listPosts(Post post);

    /**
     * 算出有多少post的cid在ids中
     * @param ids
     * @return
     */
    Integer countPostsByCids(List<Integer> ids);

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
     * 插入文章信息到db
     * @param post 文章
     * @return 插入值
     */
    int insertPost(Post post);

    /**
     * 批量删除文章
     * @param ids 要删除的id值
     * @return 删除条数
     */
    int batchDeletePosts(List<Integer> ids);

    /**
     * 更新文章，将文章的数据库字段更新
     */
    int updatePost(Post post);
}
