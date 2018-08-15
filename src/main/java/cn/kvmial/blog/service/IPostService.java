package cn.kvmial.blog.service;

import cn.kvmial.blog.pojo.Post;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 文章service的接口
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/15 0015 下午 12:12
 */

public interface IPostService {
    /**
     * 根据条件查询post
     * @return postl列表
     */
     List<Post> listPosts(Post post);

    /**
     * 批量删除文章
     * @param ids 要删除的id，用逗号分隔
     */
     void batchDeletePosts(List<Integer> ids);

    /**
     * 更新一篇post，包括文章信息和markdown
     * @param post 文章信息
     * @param markdown 文章主题内容
     * @throws IOException 文章文件读取错误
     */
     void updatePost(Post post,String markdown) throws IOException;

    /**
     * 通过上传方式新建一篇post
     * @param file 上传的文件
     * @param post 文章的信息
     * @return 是否成功信息
     */
     JSONObject uploadPost(MultipartFile file, Post post);

    /**
     * 插入文章
     * @param post 文章信息
     * @throws IOException 读取文章文件错误
     */
     void insertPost(Post post) throws IOException;

    /**
     * 获取一篇文章的markdown内容
     * @param id 文章id
     * @return 字符串形式的文章内容
     */
     JSONObject getMarkdown(Integer id);

    /**
     * 获得文章的信息
     * @param id 文章id
     * @return 文章信息
     */
     Post getPost(Integer id);

}
