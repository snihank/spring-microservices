package com.trilogyed.post.controller;

import com.trilogyed.post.exception.NotFoundException;
import com.trilogyed.post.model.Post;
import com.trilogyed.post.service.ServiceLayer;
import com.trilogyed.post.viewModel.PostViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@CacheConfig(cacheNames = {"posts"})
@RequestMapping("/posts")
public class PostController {

    @Autowired
    ServiceLayer service;


    @CachePut(key = "#result.getPostId()")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostViewModel createPost(@RequestBody @Valid PostViewModel pvm) {

        return service.createPost(pvm);

    }

    @Cacheable
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostViewModel getPost(@PathVariable("id") int id) {
        PostViewModel pvm = service.getPost(id);
        if (pvm == null)
            throw new NotFoundException("Cannot find id: " + id);
        return pvm;

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostViewModel> getAllPosts(){
        return service.getAllPosts();
    }

    @CacheEvict
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletePost(@PathVariable("id") int id){
        service.removePost(id);
        return "Post successfully deleted.";
    }


    @PutMapping("/{id}")
    @CacheEvict(key = "#pvm.getPostId()")
    @ResponseStatus(HttpStatus.OK)
    public String updatePost(@PathVariable("id") int id, @RequestBody @Valid PostViewModel pvm) {
        service.updatePost(pvm);
        return "Post successfully updated.";
    }


    @GetMapping("/user/{postername}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostViewModel> getPostByPosterName( @PathVariable("postername") String posterName) {

            List<PostViewModel> pvmList = service.getPostByPosterName(posterName);
            if (pvmList.size() == 0)
                throw new NotFoundException("Cannot find poster: " + posterName);
            return pvmList;
    }


}
