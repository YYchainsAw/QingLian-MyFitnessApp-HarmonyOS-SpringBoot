package com.yychainsaw.repository;

import com.yychainsaw.pojo.entity.Friendship;
import com.yychainsaw.pojo.entity.FriendshipId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, FriendshipId> {

}