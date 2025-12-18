package com.yychainsaw.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "friendships")
@IdClass(FriendshipId.class) // 指定复合主键类
public class Friendship {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Id
    @Column(name = "friend_id")
    private UUID friendId;

    private String status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
