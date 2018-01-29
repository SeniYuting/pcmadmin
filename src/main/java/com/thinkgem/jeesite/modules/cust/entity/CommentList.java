package com.thinkgem.jeesite.modules.cust.entity;

import java.util.List;

/**
 * Created by julia on 12/17/16.
 */
public class CommentList {
    private List<Comment> Comment;

    public List<com.thinkgem.jeesite.modules.cust.entity.Comment> getComment() {
        return Comment;
    }

    public void setComment(List<com.thinkgem.jeesite.modules.cust.entity.Comment> comment) {
        Comment = comment;
    }
}
