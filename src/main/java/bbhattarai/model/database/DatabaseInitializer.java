package bbhattarai.model.database;

import bbhattarai.model.PersistentExpection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private Connection connection;
    private DatabaseHandler databaseHandler;

    public DatabaseInitializer(Connection connection, DatabaseHandler databaseHandler) {
        this.connection = connection;
        this.databaseHandler = databaseHandler;
    }

    public void initializeDatabase() throws PersistentExpection {
        createTables();
        try {
            insertDummyDataForWordImage();
        } catch (SQLException e) {
            throw new PersistentExpection(e.getMessage());
        }
    }

    private void createTables() {
        createUsersTable();
        createWordImageTable();
        createUserAnswersTable();
    }

    private void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String createUserTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT," +
                    "total_play INTEGER," +
                    "wins INTEGER," +
                    "losses INTEGER," +
                    "last_played_date DATETIME" +
                    ")";
            statement.execute(createUserTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createWordImageTable() {
        try (Statement statement = connection.createStatement()) {
            String createWordImageTableSQL = "CREATE TABLE IF NOT EXISTS word_image (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "word TEXT," +
                    "image_url TEXT" +
                    ")";
            statement.execute(createWordImageTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUserAnswersTable() {
        try (Statement statement = connection.createStatement()) {
            String createUserAnswersTableSQL = "CREATE TABLE IF NOT EXISTS user_answers (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_id INTEGER," +
                    "word_image_id INTEGER," +
                    "FOREIGN KEY (word_image_id) REFERENCES word_image(id)," +
                    "FOREIGN KEY (user_id) REFERENCES users(user_id)" +
                    ")";
            statement.execute(createUserAnswersTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void insertDummyDataForWordImage() throws SQLException, PersistentExpection {
            if(databaseHandler.dummyDataExist()){
                return;
            }
            try (Statement statement = connection.createStatement()) {
                String insertDummyDataSQL = "INSERT INTO word_image (word, image_url) VALUES " +
                        "('Google', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/2000px-Google_2015_logo.svg.png')," +
                        "('Apfel', 'https://freepngimg.com/thumb/apple/9-apple-png-image.png')," +
                        "('Android', 'https://heise.cloudimg.io/width/610/q70.png-lossy-70.webp-lossy-70.foil1/_www-heise-de_/imgs/18/2/4/1/3/3/9/6/android-02-65d6dc4ea19c78ee.jpeg')," +
                        "('Birne', 'https://banner2.cleanpng.com/20180418/xre/kisspng-d-anjou-crisp-fruit-asian-pear-food-pear-5ad6ee401f5813.8371886715240351361284.jpg')," +
                        "('Java', 'https://1000logos.net/wp-content/uploads/2020/09/Java-Emblem-500x313.jpg')," +
                        "('Monitor', 'https://img.freepik.com/vektoren-kostenlos/computer-design_1156-101.jpg')";
                statement.execute(insertDummyDataSQL);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

