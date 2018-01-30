package sql;
import base.Baggage;
import engine.LogEngine;
import main.Database;

public class BaggageSQL {

    Database instance = new Database();

    public void dropTableBaggage(LogEngine logEngine) {
        String sqlStatement = "DROP TABLE baggage IF EXISTS";
        logEngine.write("main.Database", "dropTableBaggage", "-", sqlStatement);
        instance.update(sqlStatement);
    }

    public void createTableBaggage(LogEngine logEngine) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE baggage").append(" ( ");
        sqlStringBuilder.append("uuid VARCHAR(36) NOT NULL").append(",");
        sqlStringBuilder.append("content VARCHAR(50000) NOT NULL").append(",");
        sqlStringBuilder.append("weight INT NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (uuid)");
        sqlStringBuilder.append(" )");
        logEngine.write("main.Database", "createTableBaggage", "-", sqlStringBuilder.toString());
        instance.update(sqlStringBuilder.toString());
    }


    public String buildInsertSQLStatement(Baggage baggage) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("INSERT INTO baggage (uuid,content,weight) VALUES (");
        sqlStringBuilder.append("'").append(baggage.getUUID()).append("'").append(",");
        sqlStringBuilder.append("'").append(baggage.getContent()).append("'").append(",");
        sqlStringBuilder.append(baggage.getWeight()).append(",");
        sqlStringBuilder.append("'").append(baggage.getBaggageType()).append("'");
        sqlStringBuilder.append(")");
        return sqlStringBuilder.toString();
    }

    public void insert(Baggage baggage, LogEngine logEngine) {
        System.out.println(baggage.hashCode());
        logEngine.write("main.Database", "insert", "baggage = " + baggage.getUUID(), buildInsertSQLStatement(baggage));
        instance.update(buildInsertSQLStatement(baggage));
    }

    public String buildUpdateSQLStatement(Baggage baggage) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("UPDATE baggage SET content = '").append(baggage.getContent()).append("'").append(",");
        sqlStringBuilder.append("weight = ").append(baggage.getWeight()).append(",");
        sqlStringBuilder.append("WHERE uuid = '").append(baggage.getUUID()).append("'");
        return sqlStringBuilder.toString();
    }
}
