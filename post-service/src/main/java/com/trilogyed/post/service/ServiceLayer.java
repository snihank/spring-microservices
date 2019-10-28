package com.trilogyed.post.service;

import com.trilogyed.post.dao.PostDao;
import com.trilogyed.post.model.Post;
import com.trilogyed.post.viewModel.PostViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    private PostDao postDao;

    @Autowired
    public ServiceLayer(PostDao postDao){
        this.postDao = postDao;
    }

    private PostViewModel buildPostViewModel(Post post) {
        PostViewModel pvm = new PostViewModel();
        pvm.setPostId(post.getPostId());
        pvm.setPostDate(post.getPostDate());
        pvm.setPosterName(post.getPosterName());
        pvm.setPost(post.getPost());

        return pvm;
    }

    public PostViewModel createPost(PostViewModel pvm) {
        Post post = new Post();
        post.setPostDate(pvm.getPostDate());
        post.setPosterName(pvm.getPosterName());
        post.setPost(pvm.getPost());

        post =  postDao.createPost(post);

        pvm.setPostId(post.getPostId());
        return pvm;
    }

    public PostViewModel getPost(int id) {
        Post p =  postDao.getPost(id);
        if(p==null){
            throw new NumberFormatException("Cannot find id " + id);
        }
        return buildPostViewModel(p);
    }

    public List<PostViewModel> getAllPosts() {
        List<Post> post = postDao.getAllPosts();
        List<PostViewModel> pList = new ArrayList<>();
        for (Post p : post) {
            PostViewModel pvm = buildPostViewModel(p);
            pList.add(pvm);
        }
        return pList;
    }


    public void updatePost(PostViewModel pvm)
    {
        Post p = new Post();
        p.setPostId(pvm.getPostId());
        p.setPostDate(pvm.getPostDate());
        p.setPosterName(pvm.getPosterName());
        p.setPost(pvm.getPost());

        postDao.updatePost(p);
    }


    public void removePost(int postId)
    {
        postDao.deletePost(postId);
    }

    public List<PostViewModel> getPostByPosterName(String posterName) {
        List<Post> pList = postDao.getPostByPosterName(posterName);
        List<PostViewModel> pvmList = new ArrayList<>();

        for (Post p : pList) {
            pvmList.add(buildPostViewModel(p));
        }
        if (pList.size() == 0)
            return null;
        else
            return pvmList;
    }






}
