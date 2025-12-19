package com.yychainsaw.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

@Configuration
public class MyBatisPlusConfig implements MetaObjectHandler {

    /**
     * 分页插件配置
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));
        return interceptor;
    }

    /**
     * 注册自定义的 UUID 处理器
     * 解决 "Type handler was null" 和 "无法解析符号" 的问题
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            // 注册我们下面定义的内部类 PostgresUUIDTypeHandler
            configuration.getTypeHandlerRegistry().register(UUID.class, new PostgresUUIDTypeHandler());
        };
    }

    // --- 自动填充配置 ---

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
        // 补充其他可能用到的自动填充字段
        this.strictInsertFill(metaObject, "sentAt", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "workoutDate", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * 内部类：自定义 PostgreSQL UUID 处理器
     * 既然找不到官方的 UUIDTypeHandler，我们就自己写一个，更安全
     */
    public static class PostgresUUIDTypeHandler extends BaseTypeHandler<UUID> {

        @Override
        public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
            // PostgreSQL 驱动通常喜欢 setObject 处理 UUID
            ps.setObject(i, parameter);
        }

        @Override
        public UUID getNullableResult(ResultSet rs, String columnName) throws SQLException {
            return (UUID) rs.getObject(columnName);
        }

        @Override
        public UUID getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
            return (UUID) rs.getObject(columnIndex);
        }

        @Override
        public UUID getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
            return (UUID) cs.getObject(columnIndex);
        }
    }
}
