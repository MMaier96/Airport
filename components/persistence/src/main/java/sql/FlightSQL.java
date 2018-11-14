package sql;

import base.*;
import engine.LogEngine;
import main.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FlightSQL {

    private Database instance;

    public FlightSQL(Database instance){
        this.instance = instance;
    }

    public void dropTableFlight(LogEngine logEngine) {
        String sqlStatement = "DROP TABLE flight IF EXISTS";
        logEngine.write("main.Database", "dropTableFlight", "-", sqlStatement);
        instance.update(sqlStatement);
    }

    public void createTableFlight(LogEngine logEngine) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE flight").append(" ( ");
        sqlStringBuilder.append("id VARCHAR(36) NOT NULL").append(",");
        sqlStringBuilder.append("carrier VARCHAR(5000) NOT NULL").append(",");
        sqlStringBuilder.append("passenger VARCHAR(5000) NOT NULL").append(",");
        sqlStringBuilder.append("baggageid VARCHAR(36) NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (id)");
        sqlStringBuilder.append(")");
        logEngine.write("main.Database", "createTableFlight", "-", sqlStringBuilder.toString());
        instance.update(sqlStringBuilder.toString());
    }

    public String buildInsertSQLStatement(Flight flight) {

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("INSERT INTO flight (id,carrier,passenger,baggageid) VALUES (");
        sqlStringBuilder.append("'").append(flight.getId()).append("'").append(",");
        sqlStringBuilder.append("'").append(flight.getCarrier().toString()).append("'").append(",");
        sqlStringBuilder.append("'").append(getStringfromList(flight)).append("'").append(",");
        sqlStringBuilder.append("'").append(flight.getBaggageList().getUUID()).append("'");
        sqlStringBuilder.append(")");
        return sqlStringBuilder.toString();
    }

    public void insert(Flight flight, LogEngine logEngine) {
        System.out.println(flight.hashCode());
        logEngine.write("main.Database", "insert", "Flight = " + flight.getId(), buildInsertSQLStatement(flight));
        instance.update(buildInsertSQLStatement(flight));
    }

    public String buildUpdateSQLStatement(Flight flight) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("UPDATE flight SET carrier = '").append(flight.getCarrier().toString()).append("'").append(",");
        sqlStringBuilder.append("passenger = '").append(getStringfromList(flight)).append("'").append(",");
        sqlStringBuilder.append("baggageid = '").append(flight.getBaggageList().getUUID()).append("'").append(",");
        sqlStringBuilder.append("WHERE id = '").append(flight.getId()).append("'");
        return sqlStringBuilder.toString();
    }

    private String getStringfromList(Flight flight){
        boolean processedFirst = false;
        PassengerSQL passengerSQL = new PassengerSQL(instance);
        StringBuilder listbuilder = new StringBuilder();
        for (Passenger record: flight.getPassengerList()){
            if (processedFirst)
                listbuilder.append(";");
            listbuilder.append(passengerSQL.getCSVfromObject(record));
            processedFirst = true;
        }
        return listbuilder.toString();
    }

    public ArrayList<Flight> buildSelectSQLStatement() throws SQLException {
        ArrayList<Flight> allbagages = new ArrayList<Flight>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM flight INNER JOIN baggage ON flight.baggageid=baggage.uuid");
        Statement statement = instance.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sb.toString());
        while (rs.next()) {
            String id = rs.getString("id");
            String carrier = rs.getString("carrier");
            String passenger = rs.getString("passenger");
            String uuid = rs.getString("uuid");
            String content = rs.getString("content");
            int weight = rs.getInt("weight");
            BaggageType type = BaggageType.valueOf(rs.getString("type"));
            allbagages.add(new Flight(id, Carrier.valueOf(carrier),PassengerSQL.getObjectfromCSV(passenger),new Baggage(uuid,content,weight,type)));
        }

        statement.close();

        return allbagages;
    }
}
