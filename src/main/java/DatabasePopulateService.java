import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabasePopulateService {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        insertDb(database);
    }

    public static void insertDb(Database database){
        String filepath = "D:/DevJavaCourses/ProjectJDBC/src/main/java/sql/populate_db.sql";

        try {
            String sql = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(filepath))
            );
            database.executeUpdate(sql);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
