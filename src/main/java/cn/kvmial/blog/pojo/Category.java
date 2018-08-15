package cn.kvmial.blog.pojo;

/**
 * Category的实体类
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/15 0015 下午 13:53
 */


public class Category {
    private Integer id;
    private String name;
    private Integer pid;
    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", sort=" + sort +
                '}';
    }
}

