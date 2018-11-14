package sql;

        import base.CottonPad;
        import engine.LogEngine;
        import main.Database;

        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ArrayList;

public class CottonPadSQL {

    private Database instance;

    public CottonPadSQL(Database instance) {
        this.instance = instance;
    }

    public void dropTableCottonPad(LogEngine logEngine) {
        String sqlStatement = "DROP TABLE cottonpad IF EXISTS";
        logEngine.write("main.Database", "dropTableCottonPad", "-", sqlStatement);
        instance.update(sqlStatement);
    }

    public void createTableCottonPad(LogEngine logEngine) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE cottonpad").append(" ( ");
        sqlStringBuilder.append("uuid VARCHAR(36) NOT NULL").append(",");
        sqlStringBuilder.append("surface TEXT NOT NULL");
        sqlStringBuilder.append(")");
    }

    public String buildInsertSQLStatement(CottonPad cottonPad) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("INSERT INTO cottonpad (uuid,surface) VALUES (");
        sqlStringBuilder.append("'").append(cottonPad.getId()).append("'").append(",");
        sqlStringBuilder.append("'").append(cottonPad.getSurface()).append("'");
        sqlStringBuilder.append(")");
        return sqlStringBuilder.toString();
    }

    public void insert(CottonPad cottonPad, LogEngine logEngine) {
        logEngine.write("main.Database", "insert", "container = " + cottonPad.getId(), buildInsertSQLStatement(cottonPad));
        instance.update(buildInsertSQLStatement(cottonPad));
    }

    public String buildUpdateSQLStatement(CottonPad cottonPad) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("UPDATE container SET surface = '").append(cottonPad.getSurface()).append("'").append(",");
        sqlStringBuilder.append("WHERE uuid = '").append(cottonPad.getId()).append("'");
        return sqlStringBuilder.toString();
    }

    public ArrayList<CottonPad> buildSelectSQLStatement() throws SQLException {

        ArrayList<CottonPad> allbagages = new ArrayList<CottonPad>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM cottonpad");
        Statement statement = instance.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sb.toString());
        while (rs.next()) {
            String id = rs.getString("uuid");
            String surface = rs.getString("surface");
            allbagages.add(new CottonPad(id, surface));
        }

        statement.close();

        return allbagages;
    }
}
