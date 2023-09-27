DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS word_image;
DROP TABLE IF EXISTS user_answers;


CREATE TABLE IF NOT EXISTS users (
 user_id INTEGER PRIMARY KEY AUTOINCREMENT,
 username TEXT,
 total_play INTEGER,
 wins INTEGER,
 losses INTEGER,
 last_played_date DATETIME
);
DELETE FROM sqlite_sequence WHERE name = 'users';

UPDATE SQLITE_SEQUENCE SET seq = (SELECT MAX(user_id) FROM users) WHERE name = 'users';


CREATE TABLE IF NOT EXISTS word_image (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    word TEXT,
    image_url TEXT
);


CREATE TABLE IF NOT EXISTS user_answers (
    user_correct_answer_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    word_image_id INTEGER,
    answered_at DATETIME,
    FOREIGN KEY (word_image_id) REFERENCES word_image(id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Insert 10 dummy word-image pairs
INSERT INTO word_image (word, image_url) VALUES
 ('Google', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/2000px-Google_2015_logo.svg.png');

