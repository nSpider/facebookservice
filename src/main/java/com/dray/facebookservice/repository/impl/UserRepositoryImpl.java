package com.dray.facebookservice.repository.impl;

import com.dray.facebookservice.models.User;
import com.dray.facebookservice.repository.UserRepository;

import java.util.*;

public class UserRepositoryImpl implements UserRepository {

    Map<String, User> userIdToUserMap = new HashMap<>();
    Map<String, Set<String>> followerIdToFolloweeId = new HashMap<>();

    @Override
    public User createUser(User user) {
        User userToBeCreated = new User();
        userToBeCreated.setFirstName(user.getFirstName());
        userToBeCreated.setLastName(user.getLastName());
        userToBeCreated.setEmailId(user.getEmailId());
        userToBeCreated.setId(UUID.randomUUID().toString());
        userIdToUserMap.put(userToBeCreated.getId(), userToBeCreated);
        return userToBeCreated;
    }

    @Override
    public User getUserById(String userId) {
        return userIdToUserMap.get(userId);
    }

    @Override
    public void follow(String followerId, String followeeId) {
        followerIdToFolloweeId.putIfAbsent(followerId, new HashSet<>());
        followerIdToFolloweeId.get(followerId).add(followeeId);
    }

    @Override
    public void unfollow(String followerId, String followeeId) {
        followerIdToFolloweeId.putIfAbsent(followerId, new HashSet<>());
        followerIdToFolloweeId.get(followerId).remove(followeeId);
    }

    @Override
    public List<String> getFollowee(String userId) {
        followerIdToFolloweeId.putIfAbsent(userId, new HashSet<>());
        return new ArrayList<>(followerIdToFolloweeId.get(userId));
    }
}
