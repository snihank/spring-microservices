package com.trilogyed.notequeueconsumer.util.message;

import java.time.LocalDate;

public class CommentEntry {

    private Integer commentId;
    private Integer postId;
    private LocalDate commentDate;
    private String commenterName;

    private String comment;



    public CommentEntry(){}

    public CommentEntry(Integer commentId, Integer postId, LocalDate commentDate, String commenterName, String comment) {
        this.commentId = commentId;
        this.postId = postId;
        this.commentDate = commentDate;
        this.commenterName = commenterName;
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

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
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
                ", commentDate=" + commentDate +
                ", commenterName='" + commenterName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
