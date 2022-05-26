package com.dray.facebookservice.repository.impl;

import com.dray.facebookservice.models.Post;
import com.dray.facebookservice.repository.PostRepository;

import java.util.*;
import java.util.stream.Collectors;

import static com.dray.facebookservice.constants.ServiceConstants.PAGE_SIZE;

public class PostRepositoryImpl implements PostRepository {



    Map<String, Post> postIdToPost = new HashMap<>();
    Map<String, Set<String>> userIdToSetOfPostIds= new HashMap<>();

    @Override
    public Post createPost(String userId, String postId) {
        Post post = new Post(postId, userId);
        postIdToPost.putIfAbsent(postId, post);
        userIdToSetOfPostIds.putIfAbsent(userId, new HashSet<>());
        Set<String> userPostSet = userIdToSetOfPostIds.get(userId);
        userPostSet.add(postId);
        return post;
    }

    @Override
    public Post getPost(String postId) {
        return postIdToPost.get(postId);
    }

    @Override
    public void deletePost(String userId, String postId) {
        postIdToPost.remove(postId);
        userIdToSetOfPostIds.putIfAbsent(userId, new HashSet<>());
        userIdToSetOfPostIds.get(userId).remove(postId);
    }

    @Override
    public List<String> getPostsForUsers(List<String> users) {
        TreeSet<Post> posts = new TreeSet<>((a, b) -> {
            int comp = Long.compare(b.getTimestamp(), a.getTimestamp());
            if (comp == 0) {
                comp = Integer.compare(b.hashCode(), a.hashCode());
            }
            return comp;
        });
        users.forEach(user -> {
            userIdToSetOfPostIds.putIfAbsent(user, new HashSet<>());
            posts.addAll(userIdToSetOfPostIds.get(user)
                    .stream()
                    .map(postIdToPost::get)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList())
            );
        });

        return posts.stream().map(Post::getPostId).collect(Collectors.toList());
    }

    @Override
    public List<String> getPostsForUsersPaginated(List<String> users, int pageNumber) {
        return getPostsForUsers(users).stream()
                .skip(pageNumber * PAGE_SIZE)
                .limit(PAGE_SIZE)
                .collect(Collectors.toList());
    }
}
