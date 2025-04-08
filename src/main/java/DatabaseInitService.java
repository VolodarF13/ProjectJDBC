import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitService {
    private static final Database database = Database.getInstance();

    public static void main(String[] args) {
        initDb();
    }

    private static void initDb() {
        String filePath = "sql/init_db.sql";

        try (Connection connection = database.getConnection()) {
            String sql = Files.readString(Paths.get(filePath));
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.executeUpdate();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
