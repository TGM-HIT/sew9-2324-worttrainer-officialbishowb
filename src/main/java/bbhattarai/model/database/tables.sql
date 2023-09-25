CREATE TABLE IF NOT EXISTS users (
 user_id INTEGER PRIMARY KEY AUTOINCREMENT,
 total_play INTEGER,
 wins INTEGER,
 losses INTEGER,
 last_played_date DATETIME
);

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
    FOREIGN KEY (word_image_id) REFERENCES word_image(id)
);

-- Insert 10 dummy word-image pairs
INSERT INTO word_image (word, image_url) VALUES
 ('Apple', 'image1.jpg'),
 ('Banana', 'image2.jpg'),
 ('Cat', 'image3.jpg'),
 ('Dog', 'image4.jpg'),
 ('Elephant', 'image5.jpg'),
 ('Fish', 'image6.jpg'),
 ('Giraffe', 'image7.jpg'),
 ('Horse', 'image8.jpg'),
 ('Ice Cream', 'image9.jpg'),
 ('Jellyfish', 'image10.jpg');

