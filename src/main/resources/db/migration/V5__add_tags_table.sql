CREATE TABLE tags
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE user_tags
(
    user_id BIGINT NOT NULL,
    tag_id  INT    NOT NULL,
    PRIMARY KEY (user_id, tag_id),
    FOREIGN KEY (user_id) references users (id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) references tags (id) ON DELETE CASCADE
);