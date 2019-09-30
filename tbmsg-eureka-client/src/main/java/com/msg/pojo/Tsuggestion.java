package com.msg.pojo;

/**
 * @Description:    意见反馈表t_suggestion的实体类Tsuggestion
 * @Author:         luoyhong
 * @CreateDate:     2019/6/26 10:53
 * @UpdateUser:     luoyhong
 * @UpdateDate:     2019/6/26 10:53
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class Tsuggestion {

    private Integer id;
    private String openid;
    private String content;
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
