package com.yychainsaw.pojo.entity;

import com.yychainsaw.anno.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String username;

    @NotEmpty
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @NotEmpty
    private String nickname;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Gender
    private String gender;

    @Column(name = "height_cm")
    private Integer height;

    @Column(name = "weight_kg")
    private BigDecimal weight;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    @CreationTimestamp // Hibernate 自动管理创建时间
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp // Hibernate 自动管理更新时间
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // --- 统计数据 (非数据库表字段，必须加 @Transient) ---
    @Transient
    private Long followers;
    @Transient
    private Long following;
    @Transient
    private Long totalMinutes;
    @Transient
    private Long totalCalories;
}
