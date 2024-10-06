DROP TABLE IF EXISTS USER_TABLE;
CREATE TABLE USER_TABLE(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO USER_TABLE (name, username, password) VALUES
    ('Alice', 'alice@mail.co', '$2a$10$Qldj64zlh4eDNm7oGfwmWOkoevSo.Uv1LjXao/Rwsc2jGwjIXKRii'),
    ('Bob', 'bob@mail.co', '$2a$10$Qldj64zlh4eDNm7oGfwmWOkoevSo.Uv1LjXao/Rwsc2jGwjIXKRii'),
    ('John', 'John.Doe@mail.co', '$2a$10$Qldj64zlh4eDNm7oGfwmWOkoevSo.Uv1LjXao/Rwsc2jGwjIXKRii');
