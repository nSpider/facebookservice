package com.dray.facebookservice.models;

import java.time.Instant;

public class Post {
    String postId;
    String userId;
    long timestamp;

    public Post(String postId, String userId) {
        this.postId = postId;
        this.userId = userId;
        timestamp = Instant.EPOCH.getEpochSecond();
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (timestamp != post.timestamp) return false;
        if (postId != null ? !postId.equals(post.postId) : post.postId != null) return false;
        return userId != null ? userId.equals(post.userId) : post.userId == null;
    }

    @Override
    public int hashCode() {
        int result = postId != null ? postId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }
}
