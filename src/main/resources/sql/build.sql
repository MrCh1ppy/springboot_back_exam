CREATE TABLE IF NOT EXISTS t_user
(
    id           int          NOT NULL INVISIBLE COMMENT '主键，基本上使用自增，分布式情况下考虑雪花算法'
        CONSTRAINT `PRIMARY`
        PRIMARY KEY,
    user_name    varchar(255) NOT NULL COMMENT '用户名',
    password_md5 varchar(255) NOT NULL COMMENT '使用摘要算法处理后的密码，切忌使用密码原本存',
    is_delete    int          NOT NULL COMMENT '一般使用假删除，通过修改一个flag位代表是否删除，0代表否，1代表是'
);
