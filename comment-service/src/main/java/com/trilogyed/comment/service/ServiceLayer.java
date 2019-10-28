package com.trilogyed.comment.service;


import com.trilogyed.comment.dao.CommentDao;
import com.trilogyed.comment.model.Comment;
import com.trilogyed.comment.viewModel.CommentViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    private CommentDao commentDao;

    @Autowired
    public ServiceLayer(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    private CommentViewModel buildCommentViewModel(Comment c) {
        CommentViewModel cvm = new CommentViewModel();
        cvm.setCommentId(c.getCommentId());
        cvm.setPostId(c.getPostId());
        cvm.setCreateDate(String.valueOf(c.getCreateDate()));
        cvm.setCommenterName(c.getCommenterName());
        cvm.setComment(c.getComment());

        return cvm;
    }


    public CommentViewModel createComment(CommentViewModel cvm) {
        Comment c = new Comment();
        c.setPostId(cvm.getPostId());
        c.setCreateDate(LocalDate.parse(cvm.getCreateDate()));
        c.setCommenterName(cvm.getCommenterName());
        c.setComment(cvm.getComment());
        c =  commentDao.createComment(c);

        cvm.setCommentId(c.getCommentId());
        return cvm;
    }

    public CommentViewModel getComment(int commentId) {
        Comment c =  commentDao.getComment(commentId);
        if(c==null){
            throw new NumberFormatException("Cannot find id " + commentId);
        }
        return buildCommentViewModel(c);
    }

    public List<CommentViewModel> getAllComments() {
        List<Comment> comment = commentDao.getAllComments();
        List<CommentViewModel> cList = new ArrayList<>();
        for (Comment c : comment) {
            CommentViewModel cvm = buildCommentViewModel(c);
            cList.add(cvm);
        }
        return cList;
    }

    public void deleteComment(int commentId)
    {
        commentDao.deleteComment(commentId);
    }

    public CommentViewModel updateComment(CommentViewModel cvm)
    {
        Comment comment = new Comment();
        comment.setCommentId(cvm.getCommentId());
        comment.setPostId(cvm.getPostId());
        comment.setCreateDate(LocalDate.parse(cvm.getCreateDate()));
        comment.setCommenterName(cvm.getCommenterName());
        comment.setComment(cvm.getComment());
        commentDao.updateComment(comment);

        return cvm;
    }

    public List<CommentViewModel> getCommentByPostId(int postId) {
        List<Comment> cList = commentDao.getCommentByPost(postId);
        List<CommentViewModel> cvmList = new ArrayList<>();

        for (Comment c : cList) {
            cvmList.add(buildCommentViewModel(c));
        }
        if (cList.size() == 0)
            return null;
        else
            return cvmList;
    }

}
