CREATE TABLE users (
  id         BIGINT PRIMARY KEY,
  username   VARCHAR(50) NOT NULL,
  password   VARCHAR(60) NOT NULL,
  full_name  VARCHAR(100),
  created_at TIMESTAMP   NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP
);