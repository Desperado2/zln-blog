package com.buss.entity;

import com.base.anno.Colnum;

import java.util.Date;

/**
 * 博客基本信息实体
 */
public class BlogBaseEntity {
    @Colnum("id")
    private String id;//id
    @Colnum("title")
    private String title;//标题
    @Colnum("key_word")
    private String keyWord;//关键词
    @Colnum("create_date")
    private Date createDate;//创建时间
    @Colnum("type")
    private String type; //类型
    @Colnum("comment_number")
    private String commentNumber; //评论数量
    @Colnum("")
    private String likeNumber; //点赞数量
    private String lookNumber; //查看数量
    private String authorId; //作者id
    private String status; //状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getLookNumber() {
        return lookNumber;
    }

    public void setLookNumber(String lookNumber) {
        this.lookNumber = lookNumber;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
