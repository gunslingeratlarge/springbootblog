package cn.kvmial.blog.pojo;

import java.util.Date;

/**
 * 博客文章实体类
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/3 0003 下午 13:07
 */

public class Post {
    /**
     * id
     * title
     * markdown_path
     * like_num
     * comment_num
     * read_num
     * status
     * is_sticky
     * cid
     * gmt_create
     * gmt_modified
     */

    private Integer id;
    private String title;
    private String markdownPath;
    private Integer likeNum;
    private Integer commentNum;
    private Integer readNum;
    private Integer status;
    private Boolean sticky;
    private String category;
    private Date gmtCreate;
    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMarkdownPath() {
        return markdownPath;
    }

    public void setMarkdownPath(String markdownPath) {
        this.markdownPath = markdownPath;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getSticky() {
        return sticky;
    }

    public void setSticky(Boolean sticky) {
        this.sticky = sticky;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Post(Integer id, String title, String markdownPath, Integer likeNum, Integer commentNum, Integer readNum, Integer status, Boolean sticky, String category, Date gmtCreate, Date gmtModified) {
        super();
        this.id = id;
        this.title = title;
        this.markdownPath = markdownPath;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
        this.readNum = readNum;
        this.status = status;
        this.sticky = sticky;
        this.category = category;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Post() {
        super();
    }
}