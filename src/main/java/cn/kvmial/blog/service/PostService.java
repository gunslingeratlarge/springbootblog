package cn.kvmial.blog.service;

import cn.kvmial.blog.exception.TipException;
import cn.kvmial.blog.mapper.PostMapper;
import cn.kvmial.blog.pojo.Post;
import cn.kvmial.blog.pojo.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
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
public class PostService {

    @Resource
    private PostMapper postMapper;

    public List<Post> listPosts() {
        return postMapper.listPosts();
    }

    public void batchDeletePosts(List<Integer> ids) {
        // 这里应该对返回值进行一下检查
        int len = ids.size();
        int deleteInt = postMapper.batchDeletePosts(ids);
        if (deleteInt != len) {
            throw new TipException("数据库返回值与删除id数不一致");
        }
    }

    public JSONObject uploadPost(MultipartFile file, Post post) {

        String originalFilename = file.getOriginalFilename();
        String fileName = originalFilename.substring(0,originalFilename.indexOf('.'));
        if (postMapper.getPostByPath(fileName + ".md") != null) {
            fileName += "-copy-" + UUID.randomUUID();
        }
        String filePath = fileName + ".md";

        File newFile = new File(setUploadFileDirectory(),filePath);
        JSONObject jsonObject = new JSONObject();
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("success",false);
            jsonObject.put("msg","文件transfer异常");
            return jsonObject;
        }
        // 如果上传成功了
        post.setGmtCreate(new Date());
        post.setGmtModified(new Date());
        post.setMarkdownPath(filePath);
        if (post.getTitle() == null) {
            post.setTitle(fileName);
        }
        postMapper.insertPost(post);
        jsonObject.put("success",true);
        return jsonObject;

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

    public JSONObject getMarkdown(Integer id) {
        // 首先获得path，再通过path到文件系统中读取
        JSONObject jsonObject = new JSONObject();
        Post post = postMapper.getPostById(id);
        if(post == null) {
            jsonObject.put("msg","没有该id的文件");
            jsonObject.put("success",false);
            return jsonObject;
        }

        String path = post.getMarkdownPath();
        if (path == null) {
            jsonObject.put("msg","没有markdown路径");
            jsonObject.put("success",false);
            return jsonObject;
        }

        File file = new File(setUploadFileDirectory(),path);
        String encoding = "UTF-8";
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            BufferedInputStream bin = new BufferedInputStream(in);
            bin.read(fileContent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String markDown = new String(fileContent, encoding);
            jsonObject.put("success",true);
            jsonObject.put("data", markDown);
            return jsonObject;
        } catch (UnsupportedEncodingException e) {
            jsonObject.put("success",false);
            jsonObject.put("msg", "OS不支持的编码格式");
            e.printStackTrace();
            return jsonObject;
        }
    }


}
