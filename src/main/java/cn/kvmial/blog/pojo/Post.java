package cn.kvmial.blog.pojo;

import cn.kvmial.blog.util.DateUtils;

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
    private Integer cid;
    private String category;
    private Date gmtCreate;
    private Date gmtModified;


    /**
     * 方便字段
     */
    private String gmtCreateStr;
    private String gmtModifiedStr;

    public String getGmtCreateStr() {
        return gmtCreateStr;
    }

    public void setGmtCreateStr(String gmtCreateStr) {
        this.gmtCreateStr = gmtCreateStr;
    }

    public String getGmtModifiedStr() {
        return gmtModifiedStr;
    }

    public void setGmtModifiedStr(String gmtModifiedStr) {
        this.gmtModifiedStr = gmtModifiedStr;
    }

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

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        if (gmtCreate != null) {
            gmtCreateStr = DateUtils.dateTimeToStr(gmtCreate);
        }
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
        if (gmtModified != null) {
            gmtModifiedStr = DateUtils.dateTimeToStr(gmtModified);
        }
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", markdownPath='" + markdownPath + '\'' +
                ", likeNum=" + likeNum +
                ", commentNum=" + commentNum +
                ", readNum=" + readNum +
                ", status=" + status +
                ", sticky=" + sticky +
                ", cid=" + cid +
                ", category='" + category + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", gmtCreateStr='" + gmtCreateStr + '\'' +
                ", gmtModifiedStr='" + gmtModifiedStr + '\'' +
                '}';
    }

}
