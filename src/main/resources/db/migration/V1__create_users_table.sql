CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    version    BIGINT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    name       VARCHAR(100)             NOT NULL,
    email      VARCHAR(150)             NOT NULL UNIQUE
);