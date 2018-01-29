package com.thinkgem.jeesite.modules.cust.entity;


public class TagEntity {
    private Long id;
    private String tag_content;
    private String tag_description;

    public TagEntity(Long id, String tag_content, String tag_description){
        this.id = id;
        this.tag_content = tag_content;
        this.tag_description = tag_description;
    }

    public TagEntity(String tag_content, String tag_description) {
        this.tag_content = tag_content;
        this.tag_description = tag_description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag_content() {
        return tag_content;
    }

    public void setTag_content(String tag_content) {
        this.tag_content = tag_content;
    }

    public String getTag_description() {
        return tag_description;
    }

    public void setTag_description(String tag_description) {
        this.tag_description = tag_description;
    }
}
