package com.trilogyed.stwitter.model;

import com.trilogyed.stwitter.viewModel.CommentViewModel;

import java.time.LocalDate;
import java.util.Objects;

public class Comment {
    private int commentId;
    private int postId;
    private String commenterName;
    private LocalDate commentDate;
    private String comment;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentViewModel)) return false;
        CommentViewModel that = (CommentViewModel) o;
        return getCommentId() == that.getCommentId() &&
                getPostId() == that.getPostId() &&
                getCommenterName().equals(that.getCommenterName()) &&
                getCommentDate().equals(that.getCommentDate()) &&
                getComment().equals(that.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommentId(), getPostId(), getCommenterName(), getCommentDate(), getComment());
    }
}
