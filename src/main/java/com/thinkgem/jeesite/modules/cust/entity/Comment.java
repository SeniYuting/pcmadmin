package com.thinkgem.jeesite.modules.cust.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * Created by julia on 12/18/16.
 */
public class Comment extends DataEntity<Comment> {
    Long user_id;
    String content;
    Integer star;
    private String createdate;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
