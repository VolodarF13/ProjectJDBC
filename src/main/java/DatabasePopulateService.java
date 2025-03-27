import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabasePopulateService {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        insertDb(database);
    }

    public static void insertDb(Database database) {
        String filePath = "sql/populate_db.sql";

        try {
            String sql = Files.readString(Paths.get(filePath));

            database.executeUpdate(sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
