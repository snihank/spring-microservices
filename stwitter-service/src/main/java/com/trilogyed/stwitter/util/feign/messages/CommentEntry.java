package com.trilogyed.stwitter.util.feign.messages;

public class CommentEntry {
    private Integer commentId;
    private Integer postId;
    private String commenterName;
    private String createDate;
    private String comment;

    public CommentEntry() {
    }

    public CommentEntry(Integer commentId, Integer postId, String createDate, String commenterName, String comment) {
        this.commentId = commentId;
        this.postId = postId;
        this.commenterName = commenterName;
        this.createDate = createDate;
        this.comment = comment;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentEntry{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", commenterName='" + commenterName + '\'' +
                ", createDate='" + createDate + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
