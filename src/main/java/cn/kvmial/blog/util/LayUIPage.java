package cn.kvmial.blog.util;

import java.util.List;

/**
 * 与layui接收的参数相对应的page类
 * @param <R>
 */
public class LayUIPage<R>{

    /**
     * 页码，从1开始
     */
    private int page = 1;
    /**
     * 页面大小
     */
    private int limit = 10;
    /**
     * 总数
     */
    private long count;
    /**
     * 结果
     */
    private List<R> data;
    
    private int code=0;
    private String msg="请求成功!";
    
    public LayUIPage() {
        super();
    }

    public int getPage() {
        return page;
    }

    public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<R> getData() {
        return data;
    }

    public void setData(List<R> data) {
        this.data = data;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    
}
