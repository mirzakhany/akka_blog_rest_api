CREATE TABLE articles (
  id         BIGINT PRIMARY KEY,
  title      VARCHAR(255) NOT NULL,
  author_id  BIGINT,
  body       VARCHAR,
  status     VARCHAR(10),
  created_at TIMESTAMP   NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP
);

ALTER TABLE articles
ADD CONSTRAINT author_fk FOREIGN KEY (author_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE;