package com.trilogyed.post.dao;

import com.trilogyed.post.model.Post;
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
public class PostDaoTest {

    @Autowired
    PostDao postDao;

    @Before
    public void setUp() throws Exception {
        List<Post> post = postDao.getAllPosts();
        for (Post p : post) {
            postDao.deletePost(p.getPostId());
        }

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void addGetDeletePost() {
        Post post = new Post();
        post.setPostDate(LocalDate.of(2019,9,26));
        post.setPosterName("Test");
        post.setPost("Test");

        post = postDao.createPost(post);

        Post p1 = postDao.getPost(post.getPostId());
        assertEquals(p1,post);

        postDao.deletePost(post.getPostId());
        p1 = postDao.getPost(post.getPostId());
        assertNull(p1);
    }


    @Test
    public void getPostByPosterName() {


        Post post = new Post();
        post.setPostDate(LocalDate.of(2019,9,26));
        post.setPosterName("Test");
        post.setPost("Test");

        post = postDao.createPost(post);

        post = new Post();
        post.setPostDate(LocalDate.of(2019,9,26));
        post.setPosterName("Test");
        post.setPost("Test");

        post = postDao.createPost(post);

        List<Post> pList = postDao.getPostByPosterName("Test");
        assertEquals(2,pList.size());



    }

    @Test
    public void getAllPosts() {

        Post post = new Post();
        post.setPostDate(LocalDate.of(2019,9,26));
        post.setPosterName("Test");
        post.setPost("Test");

        post = postDao.createPost(post);

        post = new Post();
        post.setPostDate(LocalDate.of(2019,8,24));
        post.setPosterName("Test1");
        post.setPost("Test1");

        postDao.createPost(post);

        List<Post> pList = postDao.getAllPosts();
        assertEquals(2, pList.size());
    }

    @Test
    public void updatePost() {

        Post post = new Post();
        post.setPostDate(LocalDate.of(2019,9,26));
        post.setPosterName("Test");
        post.setPost("Test");
        post = postDao.createPost(post);

        post.setPost("Test updated");
        postDao.updatePost(post);

        Post p1 = postDao.getPost(post.getPostId());
        assertEquals(p1,post);
    }

}