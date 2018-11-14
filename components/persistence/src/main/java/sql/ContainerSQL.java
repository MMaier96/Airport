package sql;


import base.*;
import engine.LogEngine;
import main.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ContainerSQL {

    private Database instance;

    public ContainerSQL(Database instance){
        this.instance = instance;
    }

    public void dropTableContainer(LogEngine logEngine) {
        String sqlStatement = "DROP TABLE container IF EXISTS";
        logEngine.write("main.Database", "dropTableContainer", "-", sqlStatement);
        instance.update(sqlStatement);
    }

    public void createTableContainer(LogEngine logEngine) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE container").append(" ( ");
        sqlStringBuilder.append("id VARCHAR(36) NOT NULL").append(",");
        sqlStringBuilder.append("type TEXT NOT NULL").append(",");
        sqlStringBuilder.append("category TEXT NOT NULL").append(",");
        sqlStringBuilder.append("profileid VARCHAR(36) NOT NULL").append(",");
        sqlStringBuilder.append("barCode TEXT NOT NULL").append(",");
        sqlStringBuilder.append("qrCode TEXT NOT NULL").append(",");
        sqlStringBuilder.append("maxLugage INT NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (id)");
        sqlStringBuilder.append(" )");
        logEngine.write("main.Database", "createTableContainer", "-", sqlStringBuilder.toString());
        instance.update(sqlStringBuilder.toString());
    }


    public String buildInsertSQLStatement(Container container) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("INSERT INTO container (id,type,category,profileid,barCode,qrCode,maxLugage) VALUES (");
        sqlStringBuilder.append("'").append(container.getId()).append("'").append(",");
        sqlStringBuilder.append("'").append(container.getType().toString()).append("'").append(",");
        sqlStringBuilder.append("'").append(container.getCategory().toString()).append("'").append(",");
        sqlStringBuilder.append("'").append(container.getProfile().getId()).append("'").append(",");
        sqlStringBuilder.append("'").append(container.getBarCodeIDCategory()).append("'").append(",");
        sqlStringBuilder.append("'").append(container.getQrCodeIDECategory()).append("'").append(",");
        sqlStringBuilder.append(container.getMaximumNumberOfBaggages());
        sqlStringBuilder.append(")");
        return sqlStringBuilder.toString();
    }

    public void insert(Container container, LogEngine logEngine) {
        logEngine.write("main.Database", "insert", "container = " + container.getId(), buildInsertSQLStatement(container));
        instance.update(buildInsertSQLStatement(container));
    }

    public String buildUpdateSQLStatement(Container container) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("UPDATE container SET type = '").append(container.getType().toString()).append("'").append(",");
        sqlStringBuilder.append("category = ").append(container.getCategory().toString()).append(",");
        sqlStringBuilder.append("profileid = ").append(container.getProfile().toString()).append(",");
        sqlStringBuilder.append("barCode = ").append(container.getBarCodeIDCategory()).append(",");
        sqlStringBuilder.append("qrCode = ").append(container.getQrCodeIDECategory()).append(",");
        sqlStringBuilder.append("maxLugage = ").append(container.getMaximumNumberOfBaggages()).append(",");
        sqlStringBuilder.append("WHERE id = '").append(container.getId()).append("'");
        return sqlStringBuilder.toString();
    }

    public Storage buildSelectSQLStatement() throws SQLException {

        ArrayList<Container> allbagages = new ArrayList<Container>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM container INNER JOIN containerprofile ON container.profileid=containerprofile.id");
        Statement statement = instance.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sb.toString());
        while (rs.next()) {
            String id = rs.getString("container.id");
            String type = rs.getString("type");
            String category = rs.getString("category");
            String barCode = rs.getString("barCode");
            String qrCode = rs.getString("qrCode");
            int maxluggage = rs.getInt("maxluggage");
            String profileid = rs.getString("containerprofile.id");
            String destination = rs.getString("destination");
            int numberofbaggages = rs.getInt("numberofbaggages");
            allbagages.add(new Container(ContainerType.valueOf(type),id, ContainerCategory.valueOf(category),new ContainerProfile(profileid,destination,numberofbaggages),barCode,qrCode,maxluggage));
        }

        statement.close();

        return new Storage(allbagages);

    }
}
