package sql;

import base.*;
import engine.LogEngine;
import main.Database;
import org.junit.experimental.theories.internal.ParameterizedAssertionError;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InvoiceSQL {

    private Database instance;

    public InvoiceSQL(Database instance) {
        this.instance = instance;
    }

    public void dropTableInvoice(LogEngine logEngine) {
        String sqlStatement = "DROP TABLE invoice IF EXISTS";
        logEngine.write("main.Database", "dropTableInvoice", "-", sqlStatement);
        instance.update(sqlStatement);
    }

    public void createTableInvoice(LogEngine logEngine) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE invoice").append(" ( ");
        sqlStringBuilder.append("id VARCHAR(36) NOT NULL").append(",");
        sqlStringBuilder.append("date TEXT NOT NULL").append(",");
        sqlStringBuilder.append("passengerid VARCHAR(36) NOT NULL").append(",");
        sqlStringBuilder.append("numberofgoods INT NOT NULL").append(",");
        sqlStringBuilder.append("amount INT NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (id)");
        sqlStringBuilder.append(")");
        logEngine.write("main.Database", "createTableInvoice", "-", sqlStringBuilder.toString());
        instance.update(sqlStringBuilder.toString());
    }

    public String buildInsertSQLStatement(Invoice invoice) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("INSERT INTO baggage (id,date,passengerid,numberofgoods,amount) VALUES (");
        sqlStringBuilder.append("'").append(invoice.getId()).append("'").append(",");
        sqlStringBuilder.append("'").append(invoice.getDate()).append("'").append(",");
        sqlStringBuilder.append("'").append(invoice.getPassenger().getId()).append("'").append(",");
        sqlStringBuilder.append("'").append(invoice.getNumberOfGoods()).append("'").append(",");
        sqlStringBuilder.append("'").append(invoice.getAmount()).append("'");
        sqlStringBuilder.append(")");
        return sqlStringBuilder.toString();
    }

    public void insert(Invoice invoice, LogEngine logEngine) {
        System.out.println(invoice.hashCode());
        logEngine.write("main.Database", "insert", "baggage = " + invoice.getId(), buildInsertSQLStatement(invoice));
        instance.update(buildInsertSQLStatement(invoice));
    }

    public String buildUpdateSQLStatement(Invoice invoice) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("UPDATE invoice SET date = '").append(invoice.getDate()).append("'").append(",");
        sqlStringBuilder.append("passengerid = '").append(invoice.getPassenger().getId()).append("'").append(",");
        sqlStringBuilder.append("numberofgoods = '").append(invoice.getNumberOfGoods()).append("'").append(",");
        sqlStringBuilder.append("amount = '").append(invoice.getAmount()).append("'").append(",");
        sqlStringBuilder.append("WHERE id = '").append(invoice.getId()).append("'");
        return sqlStringBuilder.toString();
    }

    public ArrayList<Invoice> buildSelectSQLStatement() throws SQLException {

        ArrayList<Invoice> invoices = new ArrayList<Invoice>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM invoice, passenger INNER JOIN passenger ON invoice.passengerid=passenger.id INNER JOIN boardingpass ON passenger.boardingpassid=boardingpass.id");
        Statement statement = instance.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sb.toString());
        while (rs.next()) {

            String id = rs.getString("invoice.id");
            String date = rs.getString("date");
            int numberofgoods = rs.getInt("numberofgoods");
            int amount = rs.getInt("amount");
            String boardingpassid = rs.getString("boardingpass.uuid");
            String boardingpasscarrier = rs.getString("boardingpass.carrier");
            String flight = rs.getString("flight");
            String passengername = rs.getString("passengername");
            String ticketclass = rs.getString("ticketclass");
            String source = rs.getString("source");
            String destination = rs.getString("destination");
            String boardingdate = rs.getString("date");
            String gate = rs.getString("gate");
            String boardingtime = rs.getString("boardingtime");
            String seat = rs.getString("seat");
            String uuid = rs.getString("passenger.id");
            String name = rs.getString("name");
            String content = rs.getString("content");
            String birthdate = rs.getString("birthdate");
            String street = rs.getString("street");
            String postcode = rs.getString("postcode");
            String city = rs.getString("city");
            String citizenship = rs.getString("citizenship");
            String gender = rs.getString("gender");
            String baggage = rs.getString("baggage");
            String picture = rs.getString("picture");
            String visa = rs.getString("visa");

            invoices.add(new Invoice(id,date,new Passenger(uuid,name,content,birthdate,street,postcode,city,picture,visa, CitizenshipCode.valueOf(citizenship), Gender.valueOf(gender),BaggageSQL.getObjectfromCSV(baggage),new BoardingPass(boardingpassid,Carrier.valueOf(boardingpasscarrier),flight,passengername,TicketClass.valueOf(ticketclass),Source.valueOf(source),Destination.valueOf(destination),boardingdate,gate,boardingtime,seat)),numberofgoods,amount));

        }

        return invoices;

    }

}
