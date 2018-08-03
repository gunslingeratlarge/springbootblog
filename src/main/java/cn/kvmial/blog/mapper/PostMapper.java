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
     * 获得所有的文章
     */
    //@Select("select * from blog_post")
    List<Post> listPosts();

}
