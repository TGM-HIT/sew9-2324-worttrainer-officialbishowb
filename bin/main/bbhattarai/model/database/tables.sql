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
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    word_image_id INTEGER,
    FOREIGN KEY (word_image_id) REFERENCES word_image(id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Insert 10 dummy word-image pairs
INSERT INTO word_image (word, image_url) VALUES
 ('Google', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/2000px-Google_2015_logo.svg.png'),
 ('Apfel','https://freepngimg.com/thumb/apple/9-apple-png-image.png'),
 ('Android','https://heise.cloudimg.io/width/610/q70.png-lossy-70.webp-lossy-70.foil1/_www-heise-de_/imgs/18/2/4/1/3/3/9/6/android-02-65d6dc4ea19c78ee.jpeg'),
 ('Birne','https://banner2.cleanpng.com/20180418/xre/kisspng-d-anjou-crisp-fruit-asian-pear-food-pear-5ad6ee401f5813.8371886715240351361284.jpg'),
 ('Java','https://1000logos.net/wp-content/uploads/2020/09/Java-Emblem-500x313.jpg'),
 ('Monitor','https://img.freepik.com/vektoren-kostenlos/computer-design_1156-101.jpg?w=740&t=st=1696153560~exp=1696154160~hmac=b1cda8da3bc1e95892f706ed35a854bc0acaf79a0811d877b485be9134c1b1d3');

