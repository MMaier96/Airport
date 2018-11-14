package sql;

import base.*;
import engine.LogEngine;
import main.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaggageIdentificationTagSQL {
    Database instance;

    public BaggageIdentificationTagSQL(Database instance) {
        this.instance = instance;
    }

    public void dropTableBaggageIdentificationTag(LogEngine logEngine) {
        String sqlStatement = "DROP TABLE BaggageIdentificationTag IF EXISTS";
        logEngine.write("main.Database", "dropTableBaggageIdentificationTag", "-", sqlStatement);
        instance.update(sqlStatement);
    }

    public void createTableBaggageIdentificationTag(LogEngine logEngine) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE baggageIdentificationTag").append(" ( ");
        sqlStringBuilder.append("id TEXT NOT NULL").append(",");
        sqlStringBuilder.append("boardingPassid VARCHAR(36) NOT NULL").append(",");
        sqlStringBuilder.append("carrier TEXT NOT NULL").append(",");
        sqlStringBuilder.append("sequence INT NOT NULL").append(",");
        sqlStringBuilder.append("via01 TEXT NOT NULL").append(",");
        sqlStringBuilder.append("via02 TEXT NOT NULL").append(",");
        sqlStringBuilder.append("via03 TEXT NOT NULL").append(",");
        sqlStringBuilder.append("baggageid TEXT NOT NULL").append(",");
        sqlStringBuilder.append("isLocal TEXT NOT NULL").append(",");
        sqlStringBuilder.append("isTransfer TEXT NOT NULL").append(",");
        sqlStringBuilder.append("isTransferFirst TEXT NOT NULL").append(",");
        sqlStringBuilder.append("barCode TEXT NOT NULL");
        sqlStringBuilder.append("PRIMARY KEY (id)");
        sqlStringBuilder.append(" )");
        logEngine.write("main.Database", "createTableBaggageIdentificationTag", "-", sqlStringBuilder.toString());
        instance.update(sqlStringBuilder.toString());
    }


    public String buildInsertSQLStatement(BaggageIdentificationTag baggageIdentificationTag) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("INSERT INTO baggageIdentificationTag (id,boardingPassid,carrier,sequence,via01,via02,via03,baggageid,specialGood,isLocal,isTransfer,isTransferFirst,barCode) VALUES (");
        sqlStringBuilder.append("'").append(baggageIdentificationTag.getId()).append("'").append(",");
        sqlStringBuilder.append("'").append(baggageIdentificationTag.getBoardingPass().getId()).append("'").append(",");
        sqlStringBuilder.append("'").append(baggageIdentificationTag.getCarrier().toString()).append("'").append(",");
        sqlStringBuilder.append("'").append(baggageIdentificationTag.getSequence()).append("'").append(",");
        sqlStringBuilder.append("'").append(baggageIdentificationTag.getVia01()).append("'").append(",");
        sqlStringBuilder.append("'").append(baggageIdentificationTag.getVia02()).append("'").append(",");
        sqlStringBuilder.append("'").append(baggageIdentificationTag.getVia03()).append("'").append(",");
        sqlStringBuilder.append("'").append(baggageIdentificationTag.getBaggage().getUUID()).append("'").append(",");
        sqlStringBuilder.append("'").append(baggageIdentificationTag.getSpecialGood().getSpecialGoodType()).append("'").append(",");
        sqlStringBuilder.append("'").append(baggageIdentificationTag.isLocal()).append("'").append(",");
        sqlStringBuilder.append("'").append(baggageIdentificationTag.isTransfer()).append("'").append(",");
        sqlStringBuilder.append("'").append(baggageIdentificationTag.isTransferFirst()).append("'").append(",");
        sqlStringBuilder.append("'").append(baggageIdentificationTag.getBarCode()).append("'");
        sqlStringBuilder.append(")");
        return sqlStringBuilder.toString();
    }

    public void insert(BaggageIdentificationTag baggageIdentificationTag, LogEngine logEngine) {
        logEngine.write("main.Database", "insert", "BaggageIdentificationTag = " + baggageIdentificationTag.getId(), buildInsertSQLStatement(baggageIdentificationTag));
        instance.update(buildInsertSQLStatement(baggageIdentificationTag));
    }

    public String buildUpdateSQLStatement(BaggageIdentificationTag baggageIdentificationTag) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("UPDATE baggageIdentificationTag SET boardingPassid = '").append(baggageIdentificationTag.getBoardingPass().getId()).append("'").append(",");
        sqlStringBuilder.append("carrier = ").append(baggageIdentificationTag.getCarrier().toString()).append(",");
        sqlStringBuilder.append("sequence = ").append(baggageIdentificationTag.getSequence()).append(",");
        sqlStringBuilder.append("via01 = ").append(baggageIdentificationTag.getVia01()).append(",");
        sqlStringBuilder.append("via02 = ").append(baggageIdentificationTag.getVia02()).append(",");
        sqlStringBuilder.append("via03 = ").append(baggageIdentificationTag.getVia03()).append(",");
        sqlStringBuilder.append("baggageid = ").append(baggageIdentificationTag.getBaggage().getUUID()).append(",");
        sqlStringBuilder.append("specialGood = ").append(baggageIdentificationTag.getSpecialGood().getSpecialGoodType()).append(",");
        sqlStringBuilder.append("isLocal = ").append(baggageIdentificationTag.isLocal()).append(",");
        sqlStringBuilder.append("isTransfer = ").append(baggageIdentificationTag.isTransfer()).append(",");
        sqlStringBuilder.append("isTransferFirst = ").append(baggageIdentificationTag.isTransferFirst()).append(",");
        sqlStringBuilder.append("barCode = ").append(baggageIdentificationTag.getBarCode()).append(",");
        sqlStringBuilder.append("WHERE id = '").append(baggageIdentificationTag.getId()).append("'");
        return sqlStringBuilder.toString();
    }


    public BaggageIdentificationTag buildSelectSQLStatement (Baggage baggage) throws SQLException {

        BaggageIdentificationTag baggageIdentificationTag = null;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM baggageIdentificationTag WHERE baggageid=").append("'").append(baggage.getUUID()).append("'").append("INNER JOIN boardingpass ON baggageIdentificationTag.boardingpassid=boardingpass.id INNER JOIN baggage ON baggageIdentificationTag.baggageid=baggage.uuid");
        Statement statement = instance.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sb.toString());
        while (rs.next()){

            String id = rs.getString("id");
            String carrier  = rs.getString("baggageIdentificationTag.carrier");
            int sequence  = rs.getInt("sequence");
            String via1  = rs.getString("via1");
            String via2  = rs.getString("via2");
            String via3  = rs.getString("via3");
            String specialgood  = rs.getString("specialgood");
            String isLocal  = rs.getString("isLocal");
            String isTransfer  = rs.getString("isTransfer");
            String isTransferFirst  = rs.getString("isTransferFirst");
            String barCode  = rs.getString("barCode");
            String boardingpassid = rs.getString("boardingpass.uuid");
            String boardingpasscarrier = rs.getString("boardingpass.carrier");
            String flight = rs.getString("flight");
            String passengername = rs.getString("passengername");
            String ticketclass = rs.getString("ticketclass");
            String source = rs.getString("source");
            String destination = rs.getString("destination");
            String date = rs.getString("date");
            String gate = rs.getString("gate");
            String boardingtime = rs.getString("boardingtime");
            String seat = rs.getString("seat");
            String baggageid = rs.getString("baggage.uuid");
            String content = rs.getString("content");
            int weight = rs.getInt("weight");
            String type = rs.getString("baggage");

            baggageIdentificationTag = new BaggageIdentificationTag(id,new BoardingPass(boardingpassid, Carrier.valueOf(boardingpasscarrier),flight,passengername, TicketClass.valueOf(ticketclass),Source.valueOf(source),Destination.valueOf(destination),date,gate,boardingtime,seat),Carrier.valueOf(carrier),sequence,via1,via2,via3,new Baggage(baggageid,content,weight,BaggageType.valueOf(type)),new SpecialGood(SpecialGoodType.valueOf(specialgood)),Boolean.getBoolean(isLocal),Boolean.getBoolean(isTransfer),Boolean.getBoolean(isTransferFirst),barCode);

        }

        return baggageIdentificationTag;

    }

}

