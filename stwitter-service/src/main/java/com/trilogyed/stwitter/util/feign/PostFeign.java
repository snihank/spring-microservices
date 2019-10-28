package com.trilogyed.stwitter.util.feign;

import com.trilogyed.stwitter.model.Post;
import com.trilogyed.stwitter.viewModel.PostCommentViewModel;
import com.trilogyed.stwitter.viewModel.PostViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "post-service")
public interface PostFeign {

    @PostMapping("/posts")
    public PostViewModel createPost(@RequestBody PostViewModel pvm);

    @GetMapping("/posts/{id}")
    public PostViewModel getPost(@PathVariable("id") int id);

    @GetMapping("/posts/user/{posterName}")
    public List<PostViewModel> getPostByPosterName(@PathVariable("posterName") String posterName);

}
