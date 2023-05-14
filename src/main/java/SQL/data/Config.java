package SQL.data;

public class Config {


    private int budget = 500;
    private String SQL_Path;

    public Config() {
    }

    public Config(int budget, String SQL_Path) {
        this.budget = budget;
        this.SQL_Path = SQL_Path;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setSQL_Path(String SQL_Path) {
        this.SQL_Path = SQL_Path;
    }
    public int getBudget() {
        return budget;
    }

    public String getSQL_Path() {
        return SQL_Path;
    }
}
