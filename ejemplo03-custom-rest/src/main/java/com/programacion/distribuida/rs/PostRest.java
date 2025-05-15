package com.programacion.distribuida.rs;

import com.programacion.distribuida.db.Post;
import com.programacion.distribuida.repo.PostRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;

@Path("/posts")
public class PostRest {

    @Inject
    PostRepository postRepository;

    @GET
    public List<Post> findAll() {
        return postRepository.findAll();
    }
    @GET
    @Path("/{postId}")
    public Post findById(@PathParam("postId") Integer id) {
        return postRepository
                .findId(id)
                .orElse(null);
    }
}
