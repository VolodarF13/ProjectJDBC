import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseInitService {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        initDb(database);

    }

    public static void initDb(Database database) {
        String filePath = "sql/init_db.sql";

        try {
            String sql = Files.readString(Paths.get(filePath));
            database.executeUpdate(sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
