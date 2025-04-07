import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabasePopulateService {

    private static final Database DATABASE = Database.getInstance();
    private final PreparedStatement insertWorker;
    private final PreparedStatement insertClient;
    private final PreparedStatement insertProject;
    private final PreparedStatement insertProject_worker;

    public DatabasePopulateService() throws SQLException {
        insertWorker = DATABASE.getConnection().prepareStatement(
                "INSERT INTO worker (name, birthday, level, salary) VALUES (?, ?, ?, ?)"
        );

        insertClient = DATABASE.getConnection().prepareStatement(
                "INSERT INTO client (NAME) VALUES (?)"
        );

        insertProject = DATABASE.getConnection().prepareStatement(
                "INSERT INTO project(CLIENT_ID, START_DATE, FINISH_DATE) VALUES(?, ?, ?)"
        );

        insertProject_worker = DATABASE.getConnection().prepareStatement(
                "INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES(?, ?)"
        );
    }

    public void setInsertWorker(String name, LocalDate birthday, String level, int salary) {
        try {
            insertWorker.setString(1, name);
            insertWorker.setString(2, birthday.toString());
            insertWorker.setString(3, level);
            insertWorker.setInt(4, salary);

            insertWorker.addBatch();

            insertWorker.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setInsertWorker(String[] names, LocalDate[] birthdays, String[] levels, int[] salaries) {

        try {
            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                LocalDate birthday = birthdays[i];
                String level = levels[i];
                int salary = salaries[i];

                insertWorker.setString(1, name);
                insertWorker.setString(2, birthday.toString());
                insertWorker.setString(3, level);
                insertWorker.setInt(4, salary);

                insertWorker.addBatch();
            }
            insertWorker.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setInsertClient(String name) {
        try {
            insertClient.setString(1, name);

            insertClient.addBatch();
            insertClient.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setInsertClient(String[] names) {
        try {
            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                insertClient.setString(1, name);
                insertClient.addBatch();
            }
            insertClient.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setInsertProjects(int client_id, LocalDate start_date, LocalDate finish_date) {
        try {
            insertProject.setInt(1, client_id);
            insertProject.setString(2, start_date.toString());
            insertProject.setString(3, finish_date.toString());

            insertProject.addBatch();
            insertProject.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setInsertProjects(int[] clients_id, LocalDate[] start_dates, LocalDate[] finish_dates) {
        try {
            for (int i = 0; i < clients_id.length; i++) {
                int client_id = clients_id[i];
                LocalDate start_date = start_dates[i];
                LocalDate finish_date = finish_dates[i];
                insertProject.setInt(1, client_id);
                insertProject.setString(2, start_date.toString());
                insertProject.setString(3, finish_date.toString());

                insertProject.addBatch();
            }
            insertProject.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setInsertProjectWorker(int projectId, int workerId) {
        try {
            insertProject_worker.setInt(1, projectId);
            insertProject_worker.setInt(2, workerId);
            insertProject_worker.addBatch();
            insertProject_worker.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setInsertProjectWorker(int[] projectsId, int[] workersId) {
        try {
            for (int i = 0; i < projectsId.length; i++) {
                int projectId = projectsId[i];
                int workerId = workersId[i];
                insertProject_worker.setInt(1, projectId);
                insertProject_worker.setInt(2, workerId);
                insertProject_worker.addBatch();
            }
            insertProject_worker.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
