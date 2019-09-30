package com.msg.pojo;

/**
 * @Description:    评论信息表t_comment的实体类Tcomment
 * @Author:         luoyhong
 * @CreateDate:     2019/6/26 10:53
 * @UpdateUser:     luoyhong
 * @UpdateDate:     2019/6/26 10:53
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class Tcomment {

    private Integer id;
    private String openid;
    private String content;
    private Integer productId;
    private String urls;
    private Long createTime;
    private Long updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
