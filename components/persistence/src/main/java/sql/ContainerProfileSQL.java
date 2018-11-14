package sql;

import base.ContainerProfile;
import engine.LogEngine;
import main.Database;

public class ContainerProfileSQL {

    private Database instance;

    public ContainerProfileSQL(Database instance) {
        this.instance = instance;
    }

    public void dropTableContainerProfile(LogEngine logEngine) {
        String sqlStatement = "DROP TABLE containerprofile IF EXISTS";
        logEngine.write("main.Database", "dropTableContainerProfile", "-", sqlStatement);
        instance.update(sqlStatement);
    }

    public void createTableContainerProfile(LogEngine logEngine) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE containerprofile").append(" ( ");
        sqlStringBuilder.append("uuid VARCHAR(36) NOT NULL").append(",");
        sqlStringBuilder.append("destination TEXT NOT NULL").append(",");
        sqlStringBuilder.append("numberofbaggages INT NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (uuid)");
        sqlStringBuilder.append(")");
        logEngine.write("main.Database", "createTableContainerProfile", "-", sqlStringBuilder.toString());
        instance.update(sqlStringBuilder.toString());
    }

    public String buildInsertSQLStatement(ContainerProfile containerProfile) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("INSERT INTO containerprofile (uuid,destination,numberofbaggages) VALUES (");
        sqlStringBuilder.append("'").append(containerProfile.getId()).append("'").append(",");
        sqlStringBuilder.append("'").append(containerProfile.getDestination()).append("'").append(",");
        sqlStringBuilder.append("'").append(containerProfile.getNumberOfBaggages()).append("'");
        sqlStringBuilder.append(")");
        return sqlStringBuilder.toString();
    }

    public void insert(ContainerProfile containerProfile, LogEngine logEngine) {
        System.out.println(containerProfile.hashCode());
        logEngine.write("main.Database", "insert", "baggage = " + containerProfile.getId(), buildInsertSQLStatement(containerProfile));
        instance.update(buildInsertSQLStatement(containerProfile));
    }

    public String buildUpdateSQLStatement(ContainerProfile containerProfile) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("UPDATE containerprofile SET destination = '").append(containerProfile.getDestination()).append("'").append(",");
        sqlStringBuilder.append("numberofbaggages = '").append(containerProfile.getNumberOfBaggages()).append("'").append(",");
        sqlStringBuilder.append("WHERE uuid = '").append(containerProfile.getId()).append("'");
        return sqlStringBuilder.toString();
    }

}
