package cn.kvmial.blog.service;

import cn.kvmial.blog.mapper.PostMapper;
import cn.kvmial.blog.pojo.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
    private PostMapper postMapper;

    public List<Post> listPosts() {
        return postMapper.listPosts();
    }

    public HashMap<String,Object> uploadPost(MultipartFile file, Post post) {
        String originalFilename = file.getOriginalFilename();
        String fileName = originalFilename.substring(0,originalFilename.indexOf('.'));
        if (postMapper.getPostByPath(fileName + ".md") != null) {
            fileName += "-copy-" + UUID.randomUUID();
        }
        String filePath = fileName + ".md";

        File newFile = new File(setUploadFileDirectory(),filePath);
        HashMap<String,Object> map = new HashMap<>(3);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("success",false);
            map.put("msg","文件transfer异常");
            return map;
        }
        // 如果上传成功了
        post.setGmtCreate(new Date());
        post.setGmtModified(new Date());
        post.setMarkdownPath(filePath);
        if (post.getTitle() == null) {
            post.setTitle(fileName);
        }
        postMapper.insertPost(post);
        map.put("success",true);
        return map;

    }

    private static String setUploadFileDirectory() {
        String directory = getDefaultUploadFileDirectory(System.getProperty("os.name"));
        File file = new File(directory);
        if (!file.exists()) {
            file.mkdir();
        }
        return directory;
    }
    private static String getDefaultUploadFileDirectory(String osName1) {
        String osName = osName1.toLowerCase();
        if (osName.startsWith("mac")) {
            return String.format(
                    "%s%sLibrary%skvmialBlog", System.getProperty("user.home"), File.separator,
                    File.separator);
        } else if (osName.startsWith("win")) {
            return String.format("%s%skvmialBlog", System.getenv("APPDATA"), File.separator);
        } else {
            return String.format("%s%s.kvmialBlog", System.getProperty("user.home"), File.separator);
        }
    }

}
