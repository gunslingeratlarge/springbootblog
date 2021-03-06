package cn.kvmial.blog.service.impl;

import cn.kvmial.blog.exception.TipException;
import cn.kvmial.blog.mapper.PostMapper;
import cn.kvmial.blog.pojo.Post;
import cn.kvmial.blog.pojo.PostImage;
import cn.kvmial.blog.service.IPostService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
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
public class PostServiceImpl implements IPostService {

    @Resource
    private PostMapper postMapper;

    @Override
    public List<Post> listPosts(Post post) {
        return postMapper.listPosts(post);
    }

    @Override
    public void batchDeletePosts(List<Integer> ids) {
        // 这里应该对返回值进行一下检查
        int len = ids.size();
        // 先删文件，要不然都不知道文件在哪儿
        for (int id:ids){
            Post postById = postMapper.getPostById(id);
            if (postById == null) {
                throw new TipException("该id的post不存在，可能是重复提交了删除请求, id = " + id );
            }
            String fileName = postById.getMarkdownPath();
            File file = new File(getDefaultUploadFileDirectory(), fileName);
            if (file.exists() && file.isFile()) {
                boolean isDeleted = file.delete();
            }
        }

        int deleteInt = postMapper.batchDeletePosts(ids);

        if (deleteInt != len) {
             throw new TipException("数据库返回值与删除id数不一致");
        }

    }

    @Override
    public void updatePost(Post post, String markdown) throws IOException {
        if (post.getId() == null) {
            throw new TipException("id不能为空");
        }
        post.setGmtModified(new Date());
        // 写内容
        String markdownPath = getPost(post.getId()).getMarkdownPath();
        File file = new File(getDefaultUploadFileDirectory(), markdownPath);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(markdown,0,markdown.length());
        bufferedWriter.flush();
        bufferedWriter.close();

        postMapper.updatePost(post);
    }

    @Override
    public JSONObject uploadPost(MultipartFile file, Post post) {

        String originalFilename = file.getOriginalFilename();
        String fileName = originalFilename.substring(0,originalFilename.indexOf('.'));
        if (postMapper.getPostByPath(fileName + ".md") != null) {
            fileName += "-copy-" + UUID.randomUUID();
        }
        String filePath = fileName + ".md";

        File newFile = new File(getDefaultUploadFileDirectory(),filePath);
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

    @Override
    // TODO 上传文件涉及到数据库操作，先把数据库部分写完 abandoned 使用了图床，直接在前端完成了图片的上传操作
    public void uploadImage(MultipartFile file, PostImage image) {
        String originalFilename = file.getOriginalFilename();
        String fileName = originalFilename.substring(0,originalFilename.indexOf('.'));
        if (postMapper.getPostByPath(fileName + ".md") != null) {
            fileName += "-copy-" + UUID.randomUUID();
        }
        String filePath = fileName + ".md";

        File newFile = new File(getDefaultUploadFileDirectory(),filePath);
    }

    @Override
    public void insertPost(Post post) throws IOException {
        post.setGmtCreate(new Date());
        post.setGmtModified(new Date());
        String filename = post.getTitle() + ".md";
        File file = new File(getDefaultUploadFileDirectory(), filename);
        if (file.exists()) {
            throw new TipException("文件已经存在，无法创建");
        } else {
            file.createNewFile();
        }
        post.setMarkdownPath(filename);
        postMapper.insertPost(post);
    }


    private static String getDefaultUploadFileDirectory() {
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
                    "%s%sLibrary%skvmialBlog%sposts", System.getProperty("user.home"), File.separator,
                    File.separator, File.separator);
        } else if (osName.startsWith("win")) {
            return String.format("%s%skvmialBlog%sposts", System.getenv("APPDATA"), File.separator, File.separator);
        } else {
            return String.format("%s%skvmialBlog%sposts", System.getProperty("user.home"), File.separator,File.separator);
        }
    }

    @Override
    public String getMarkdown(Integer id) {
        // 首先获得path，再通过path到文件系统中读取
        Post post = postMapper.getPostById(id);
        if(post == null) {
            throw new TipException("没有该id的文章");
        }

        String path = post.getMarkdownPath();
        if (path == null) {
            throw new TipException("没有markdown路径");
        }

        File file = new File(getDefaultUploadFileDirectory(),path);
        String encoding = "UTF-8";
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];

        try {
            FileInputStream in = new FileInputStream(file);
            BufferedInputStream bin = new BufferedInputStream(in);
            bin.read(fileContent);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new TipException("文件读取异常");
        }


        String markDown = "";
        try {
            markDown = new String(fileContent, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new TipException("不支持的文件类型");
        }
        return markDown;
    }

    @Override
    public Post getPost(Integer id) {
        Post postById = postMapper.getPostById(id);
        if (postById == null) {
            throw new TipException("没有该post");
        }
        return postById;
    }


}
