package sql;

import base.*;
import engine.LogEngine;
import main.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BoardingPassSQL {

    private Database instance;

    public BoardingPassSQL(Database instance) {
        this.instance = instance;
    }

    public void dropTableBoardingPass(LogEngine logEngine) {
        String sqlStatement = "DROP TABLE boardingpass IF EXISTS";
        logEngine.write("main.Database", "dropTableBoardingPass", "-", sqlStatement);
        instance.update(sqlStatement);
    }

    public void createTableBoardingPass(LogEngine logEngine) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE boardingpass").append(" ( ");
        sqlStringBuilder.append("uuid VARCHAR(36) NOT NULL").append(",");
        sqlStringBuilder.append("carrier TEXT NOT NULL").append(",");
        sqlStringBuilder.append("flight TEXT NOT NULL").append(",");
        sqlStringBuilder.append("passengername TEXT NOT NULL").append(",");
        sqlStringBuilder.append("ticketclass TEXT NOT NULL").append(",");
        sqlStringBuilder.append("source TEXT NOT NULL").append(",");
        sqlStringBuilder.append("destination TEXT NOT NULL").append(",");
        sqlStringBuilder.append("date TEXT NOT NULL").append(",");
        sqlStringBuilder.append("gate TEXT NOT NULL").append(",");
        sqlStringBuilder.append("boardingtime TEXT NOT NULL").append(",");
        sqlStringBuilder.append("seat TEXT NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (uuid)");
        sqlStringBuilder.append(" )");
        logEngine.write("main.Database", "createTableBoardingPass", "-", sqlStringBuilder.toString());
        instance.update(sqlStringBuilder.toString());
    }

    public String buildInsertSQLStatement(BoardingPass boardingPass){
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("INSERT INTO boardingpass (uuid,carrier,flight,passengername,ticketclass,source,destination,date,gate,boardingtime,seat) VALUES (");
        sqlStringBuilder.append("'").append(boardingPass.getId()).append("'").append(",");
        sqlStringBuilder.append("'").append(boardingPass.getCarrier().toString()).append("'").append(",");
        sqlStringBuilder.append("'").append(boardingPass.getFlight()).append("'").append(",");
        sqlStringBuilder.append("'").append(boardingPass.getPassengerName()).append("'").append(",");
        sqlStringBuilder.append("'").append(boardingPass.getTicketClass().toString()).append("'").append(",");
        sqlStringBuilder.append("'").append(boardingPass.getSource().toString()).append("'").append(",");
        sqlStringBuilder.append("'").append(boardingPass.getDestination().toString()).append("'").append(",");
        sqlStringBuilder.append("'").append(boardingPass.getDate()).append("'").append(",");
        sqlStringBuilder.append("'").append(boardingPass.getGate()).append("'").append(",");
        sqlStringBuilder.append("'").append(boardingPass.getBoardingTime()).append("'").append(",");
        sqlStringBuilder.append("'").append(boardingPass.getSeat()).append("'").append(",");
        sqlStringBuilder.append(")");
        return sqlStringBuilder.toString();
    }

    public void insert(BoardingPass boardingPass, LogEngine logEngine) {
        System.out.println(boardingPass.hashCode());
        logEngine.write("main.Database", "insert", "baggage = " + boardingPass.getId(), buildInsertSQLStatement(boardingPass));
        instance.update(buildInsertSQLStatement(boardingPass));
    }

    public String buildUpdateSQLStatement(BoardingPass boardingPass){
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("UPDATE boardingpass SET carrier = '").append(boardingPass.getCarrier().toString()).append("'").append(",");
        sqlStringBuilder.append("flight = '").append(boardingPass.getFlight()).append("'").append(",");
        sqlStringBuilder.append("passengername = '").append(boardingPass.getPassengerName()).append("'").append(",");
        sqlStringBuilder.append("ticketclass = '").append(boardingPass.getTicketClass().toString()).append("'").append(",");
        sqlStringBuilder.append("source = '").append(boardingPass.getSource().toString()).append("'").append(",");
        sqlStringBuilder.append("destination = '").append(boardingPass.getDestination().toString()).append("'").append(",");
        sqlStringBuilder.append("date = '").append(boardingPass.getDate()).append("'").append(",");
        sqlStringBuilder.append("gate = '").append(boardingPass.getGate()).append("'").append(",");
        sqlStringBuilder.append("boardingtime = '").append(boardingPass.getBoardingTime()).append("'").append(",");
        sqlStringBuilder.append("seat = '").append(boardingPass.getSeat()).append("'").append(",");
        sqlStringBuilder.append("WHERE uuid = '").append(boardingPass.getId()).append("'");
        return sqlStringBuilder.toString();
    }

    public ArrayList<BoardingPass> buildSelectSQLStatement() throws SQLException {

        ArrayList<BoardingPass> allbagages = new ArrayList<BoardingPass>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM boardingpass INNER JOIN passenger ON boardingpass.passengerid=passenger.id");
        Statement statement = instance.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sb.toString());
        while (rs.next()) {
            String uuid = rs.getString("uuid");
            String carrier = rs.getString("carrier");
            String flight = rs.getString("flight");
            String passengername = rs.getString("passengername");
            String ticketclass = rs.getString("ticketclass");
            String source = rs.getString("source");
            String destination = rs.getString("destination");
            String date = rs.getString("date");
            String gate = rs.getString("gate");
            String boardingtime = rs.getString("boardingtime");
            String seat = rs.getString("seat");

            BoardingPass boardingPass = new BoardingPass(uuid,Carrier.valueOf(carrier),flight,passengername,TicketClass.valueOf(ticketclass),Source.valueOf(source),Destination.valueOf(destination),date,gate,boardingtime,seat);
            allbagages.add(boardingPass);

        }
        statement.close();

        return allbagages;
    }

}
