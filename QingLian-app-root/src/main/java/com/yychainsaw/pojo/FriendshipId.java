package com.yychainsaw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipId implements Serializable {
    private Long userId;
    private Long friendId;
}