CREATE TABLE tags (
  id         BIGINT PRIMARY KEY,
  tag        VARCHAR(100) NOT NULL,
  created_at TIMESTAMP   NOT NULL DEFAULT current_timestamp,
)

CREATE TABLE articles_tags (
  id BIGINT PRIMARY KEY,
  article_id BIGINT,
  tags_id BIGINT
);

ALTER TABLE articles_tags
ADD CONSTRAINT articles_fk FOREIGN KEY (article_id) REFERENCES articles (id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE articles_tags
ADD CONSTRAINT tags_fk FOREIGN KEY (tags_id) REFERENCES tags (id) ON UPDATE CASCADE ON DELETE CASCADE;