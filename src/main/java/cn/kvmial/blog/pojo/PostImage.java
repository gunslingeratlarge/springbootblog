package cn.kvmial.blog.pojo;

/**
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/30 0030 下午 13:45
 */


public class PostImage {
    private Integer id;
    private String path;
    private Integer postId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
