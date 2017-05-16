package com.xinguang.iot.common.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.github.pagehelper.Page;
import com.xinguang.iot.common.config.Global;

/**
 * Created by chuantou on 2016/12/23.
 */
public class PageWrap<T> {

    private static final String defaultPageSize = Global.getConfig("pageSize");

    Page<T> pageList = new Page<>();

    public PageWrap() {

    }

    public PageWrap(String pageNum, String pageSize, String orderBy) {
        init(pageNum, pageSize, orderBy);
    }

    public PageWrap(HttpServletRequest request) {
        init(request.getParameter("pageNum"), request.getParameter("pageSize"), request.getParameter("orderBy"));
    }

    public PageWrap(Map<String, String> map) {
        init(map.get("pageNum"), map.get("pageSize"), map.get("orderBy"));
    }

    private void init(String pageNum, String pageSize, String orderBy) {
        String no = pageNum;
        if (null != no) {
            try {
                int num = Integer.valueOf(no);
                if (num <= 0)
                    num = 1;
                pageList.setPageNum(num);
            } catch (Exception e) {
                e.printStackTrace();
                pageList.setPageNum(1);
            }
        } else {
            pageList.setPageNum(1);
        }
        String size = pageSize;
        if (null != size) {
            try {
                pageList.setPageSize(Integer.valueOf(size));
            } catch (Exception e) {
                e.printStackTrace();
                pageList.setPageSize(Integer.valueOf(defaultPageSize));
            }
        } else {
            pageList.setPageSize(Integer.valueOf(defaultPageSize));
        }

        if (StringUtils.isNotBlank(orderBy)){
            pageList.setOrderBy(orderBy);
        }
        pageList.setCount(true);
    }


    public void setPageList(List<T> list) {
        for (T object :
                list) {
            this.pageList.add(object);
        }
    }

    public int getPageNum() {
        return this.pageList.getPageNum();
    }

    public int getPageSize() {
        return this.pageList.getPageSize();
    }

    public boolean isCount() {
        return this.pageList.isCount();
    }

    public void setPages(int pages) {
        this.pageList.setPages(pages);
    }

    public int getPages() {
        return this.pageList.getPages();
    }

    public void setTotals(long totals) {
        this.pageList.setTotal(totals);
    }
    public long getTotal() {
        return this.pageList.getTotal();
    }

    public Page<T> getPageList() {
        return pageList;
    }
}
