package com.example.myapp.entity;

import java.io.Serializable;
import java.util.List;

public class VideoListResponse implements Serializable {

    private String msg;
    private Integer code;
    private PageBean page;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class PageBean  {
        private Integer totalCount;
        private Integer pageSize;
        private Integer totalPage;
        private Integer currPage;
        private List<VideoEntity> list;

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(Integer totalPage) {
            this.totalPage = totalPage;
        }

        public Integer getCurrPage() {
            return currPage;
        }

        public void setCurrPage(Integer currPage) {
            this.currPage = currPage;
        }

        public List<VideoEntity> getList() {
            return list;
        }

        public void setList(List<VideoEntity> list) {
            this.list = list;
        }
    }
}
