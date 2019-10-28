package com.trilogyed.comment.dao;

import com.trilogyed.comment.model.Comment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CommentDaoTest {

    @Autowired
    CommentDao commentDao;

    @Before
    public void setUp() throws Exception {
        List<Comment> comments = commentDao.getAllComments();
        for (Comment c : comments) {
            commentDao.deleteComment(c.getCommentId());
        }

    }


    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void addGetDeletePost() {
        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019,9,26));
        comment.setCommenterName("Test");
        comment.setComment("Test");

        comment = commentDao.createComment(comment);

        Comment c1 = commentDao.getComment(comment.getCommentId());
        assertEquals(c1,comment);

        commentDao.deleteComment(comment.getCommentId());
        c1 = commentDao.getComment(comment.getCommentId());
        assertNull(c1);
    }

    @Test
    public void getAllComments() {

        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019,9,26));
        comment.setCommenterName("Test");
        comment.setComment("Test");

        comment = commentDao.createComment(comment);

        comment = new Comment();
        comment.setPostId(2);
        comment.setCreateDate(LocalDate.of(2019,9,26));
        comment.setCommenterName("Test1");
        comment.setComment("Test1");

        commentDao.createComment(comment);

        List<Comment> cList = commentDao.getAllComments();
        assertEquals(2, cList.size());

    }

    @Test
    public void updateComment() {

        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019,9,26));
        comment.setCommenterName("Test");
        comment.setComment("Test");

        comment = commentDao.createComment(comment);

        comment.setCreateDate(LocalDate.of(2019,8,20));
        comment.setComment("Test updated");

        commentDao.updateComment(comment);

        Comment c1 = commentDao.getComment(comment.getCommentId());
        assertEquals(c1,comment);
    }


    @Test
    public void getCommentByPostId() {


        Comment c = new Comment();
        c.setPostId(1);
        c.setCreateDate(LocalDate.of(2019,8,20));
        c.setCommenterName("Test");
        c.setComment("Test");

        c = commentDao.createComment(c);

        c = new Comment();
        c.setPostId(1);
        c.setCreateDate(LocalDate.of(2019,8,20));
        c.setCommenterName("Test");
        c.setComment("Test");

        c = commentDao.createComment(c);

        List<Comment> cList = commentDao.getCommentByPost(1);
        assertEquals(2,cList.size());



    }
}