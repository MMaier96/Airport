package sql;

import base.Employee;
import engine.LogEngine;
import main.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeSQL {

    private Database instance;

    public EmployeeSQL(Database instance){
        this.instance = instance;
    }

    public void dropTableBaggage(LogEngine logEngine) {
        String sqlStatement = "DROP TABLE employee IF EXISTS";
        logEngine.write("main.Database", "dropTableBaggage", "-", sqlStatement);
        instance.update(sqlStatement);
    }

    public void createTableBaggage(LogEngine logEngine) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE employee").append(" ( ");
        sqlStringBuilder.append("id VARCHAR(36) NOT NULL").append(",");
        sqlStringBuilder.append("name TEXT NOT NULL").append(",");
        sqlStringBuilder.append("idcard TEXT NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (id)");
        sqlStringBuilder.append(")");
        logEngine.write("main.Database", "createTableEmployee", "-", sqlStringBuilder.toString());
        instance.update(sqlStringBuilder.toString());
    }

    public String buildInsertSQLStatement(Employee employee) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("INSERT INTO employee (id,name,idcard) VALUES (");
        sqlStringBuilder.append("'").append(employee.getId()).append("'").append(",");
        sqlStringBuilder.append("'").append(employee.getName()).append("'").append(",");
        sqlStringBuilder.append("'").append(employee.getIdCard()).append("'");
        sqlStringBuilder.append(")");
        return sqlStringBuilder.toString();
    }

    public void insert(Employee employee, LogEngine logEngine) {
        System.out.println(employee.hashCode());
        logEngine.write("main.Database", "insert", "employee = " + employee.getId(), buildInsertSQLStatement(employee));
        instance.update(buildInsertSQLStatement(employee));
    }

    public String buildUpdateSQLStatement(Employee employee) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("UPDATE employee SET name = '").append(employee.getName()).append("'").append(",");
        sqlStringBuilder.append("idcard = '").append(employee.getIdCard()).append("'").append(",");
        sqlStringBuilder.append("WHERE id = '").append(employee.getId()).append("'");
        return sqlStringBuilder.toString();
    }

    public ArrayList<Employee> buildSelectSQLStatement() throws SQLException {

        ArrayList<Employee> allemployees = new ArrayList<Employee>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM employee");
        Statement statement = instance.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sb.toString());
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String idcard = rs.getString("idcard");
            allemployees.add(new Employee(id, name, idcard));
        }

        statement.close();

        return allemployees;
    }
}
