CREATE TABLE categories (
  id         BIGINT PRIMARY KEY,
  name       VARCHAR(100) NOT NULL,
  slug       VARCHAR(100) NOT NULL,
  parent_id  BIGINT,
  created_at TIMESTAMP   NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP
);

CREATE TABLE articles_categories (
  id BIGINT PRIMARY KEY,
  article_id BIGINT,
  category_id BIGINT
);

ALTER TABLE articles_categories
ADD CONSTRAINT articles_fk FOREIGN KEY (article_id) REFERENCES articles (id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE articles_categories
ADD CONSTRAINT categories_fk FOREIGN KEY (category_id) REFERENCES categories (id) ON UPDATE CASCADE ON DELETE CASCADE;