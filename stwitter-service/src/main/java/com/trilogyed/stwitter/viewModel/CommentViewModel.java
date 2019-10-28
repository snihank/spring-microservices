package com.trilogyed.stwitter.viewModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class CommentViewModel {
    private int commentId;
    @NotNull(message = "post id cannot be empty")
    private int postId;
    @NotEmpty(message = "Please supply a value for commenter nane")
    private String commenterName;
    @NotEmpty(message = "Please supply a value for date")
    private String commentDate;
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

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
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
