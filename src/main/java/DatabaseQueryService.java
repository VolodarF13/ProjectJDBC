import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private static final Database INSTANCE = Database.getInstance();
    private static final String NAME = "NAME";

    public List<MaxProjectCountClient> findMaxProjectClient() {
        String filePath = "sql/find_max_projects_client.sql";
        List<MaxProjectCountClient> maxProjectCountClientList = new ArrayList<>();

        try (Statement st = INSTANCE.getConnection().createStatement()) {
            String sql = Files.readString(Paths.get(filePath));
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    String name = rs.getString(NAME);
                    int projectCount = rs.getInt("PROJECT_COUNT");
                    maxProjectCountClientList.add(new MaxProjectCountClient(name, projectCount));
                }
            }
            return maxProjectCountClientList;
        } catch (Exception e) {
            e.printStackTrace();
            return maxProjectCountClientList;
        }
    }

    public List<LongestProject> longestProjects() {
        String filePath = "sql/find_longest_project.sql";
        List<LongestProject> longestProjects = new ArrayList<>();

        try (Statement st = INSTANCE.getConnection().createStatement()) {
            String sql = Files.readString(Paths.get(filePath));
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    String name = rs.getString("client_ID");
                    int time = rs.getInt("TIMES");
                    longestProjects.add(new LongestProject(name, time));
                }
            }
            return longestProjects;
        } catch (Exception e) {
            e.printStackTrace();
            return longestProjects;
        }
    }

    public List<MaxWorkerCountSalary> findMaxSalaryWorker() {
        String filePath = "sql/find_max_salary_worker.sql";
        List<MaxWorkerCountSalary> workerCountSalaries = new ArrayList<>();

        try (Statement st = INSTANCE.getConnection().createStatement()) {
            String sql = Files.readString(Paths.get(filePath));
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    String name = rs.getString(NAME);
                    int time = rs.getInt("SALARY");
                    workerCountSalaries.add(new MaxWorkerCountSalary(name, time));
                }
            }
            return workerCountSalaries;
        } catch (Exception e) {
            e.printStackTrace();
            return workerCountSalaries;
        }
    }

    public List<YoungestEldestWorker> findYoungestEldestWorker() {
        String filePath = "sql/find_youngest_eldest_workers.sql";
        List<YoungestEldestWorker> youngestEldestWorkers = new ArrayList<>();

        try (Statement st = INSTANCE.getConnection().createStatement()) {
            String sql = Files.readString(Paths.get(filePath));
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    String age = rs.getString("TYPE");
                    String name = rs.getString(NAME);
                    String birthday = rs.getString("BIRTHDAY");
                    youngestEldestWorkers.add(new YoungestEldestWorker(age, name, birthday));
                }
            }
            return youngestEldestWorkers;
        } catch (Exception e) {
            e.printStackTrace();
            return youngestEldestWorkers;
        }
    }

    public List<PriceOfEachProject> priceOfEachProjects() {
        String filePath = "sql/print_project_prices.sql";
        List<PriceOfEachProject> priceOfEachProjects = new ArrayList<>();

        try (Statement st = INSTANCE.getConnection().createStatement()) {
            String sql = Files.readString(Paths.get(filePath));
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    String project = rs.getString("PROJECT_ID");
                    int price = rs.getInt("PRICE");
                    priceOfEachProjects.add(new PriceOfEachProject(project, price));
                }
            }
            return priceOfEachProjects;
        } catch (Exception e) {
            e.printStackTrace();
            return priceOfEachProjects;
        }
    }
}
