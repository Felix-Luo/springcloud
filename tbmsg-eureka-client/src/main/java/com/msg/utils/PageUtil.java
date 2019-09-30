package com.msg.utils;

/**
* @Description:    分页辅助类
* @Author:         luoyhong
* @CreateDate:     2019/7/11 11:38
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/7/11 11:38
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class PageUtil{
    public static int pageSize ;
    public static int pageNumber;

    public static int getPageSize() {
        return pageSize;
    }

    public static void setPageSize(int pageSize) {
        PageUtil.pageSize = pageSize;
    }

    public static int getPageNumber() {
        return pageNumber;
    }

    public static void setPageNumber(int pageNumber) {
        PageUtil.pageNumber = pageNumber;
    }
}
