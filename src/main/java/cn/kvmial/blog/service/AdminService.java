package cn.kvmial.blog.service;

import cn.kvmial.blog.mapper.PostMapper;
import cn.kvmial.blog.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/3 0003 下午 14:53
 */

@Service
public class AdminService {

    @Resource
    PostMapper postMapper;

    public List<Post> listPosts() {
        return postMapper.listPosts();
    }

}
