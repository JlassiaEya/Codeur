package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Column;
import models.Table;

public class DatabaseSchema {
    private Connection connection;

    public DatabaseSchema(Connection connection) {
        this.connection = connection;
    }

    public List<String> getTables() {
        List<String> tables = new ArrayList<>();
        try {
            if (connection != null && !connection.isClosed()) {
                DatabaseMetaData metaData = connection.getMetaData();
                try (ResultSet rs = metaData.getTables(null, null, "%", new String[]{"TABLE"})) {
                    while (rs.next()) {
                        tables.add(rs.getString("TABLE_NAME"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    public boolean isPrimaryKey(String tableName, String columnName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet pk = metaData.getPrimaryKeys(null, null, tableName)) {
            while (pk.next()) {
                if (pk.getString("COLUMN_NAME").equals(columnName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isForeignKey(String tableName, String columnName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet fk = metaData.getImportedKeys(null, null, tableName)) {
            while (fk.next()) {
                if (fk.getString("FKCOLUMN_NAME").equals(columnName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getForeignTable(String tableName, String columnName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet fk = metaData.getImportedKeys(null, null, tableName)) {
            while (fk.next()) {
                if (fk.getString("FKCOLUMN_NAME").equals(columnName)) {
                    return fk.getString("PKTABLE_NAME");
                }
            }
        }
        return null;
    }

    private String getForeignColumnName(String tableName, String columnName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName)) {
            while (foreignKeys.next()) {
                if (foreignKeys.getString("FKTABLE_NAME").equals(tableName) &&
                    foreignKeys.getString("FKCOLUMN_NAME").equals(columnName)) {
                    return foreignKeys.getString("PKCOLUMN_NAME");
                }
            }
        }
        return null; 
    }

    public Table getTableStructure(String tableName) throws SQLException {
        Table table = new Table(tableName);
        DatabaseMetaData metaData = connection.getMetaData();

        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            List<String> columnNames = new ArrayList<>(); 
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");
                if (isSystemColumnType(columnType)) { 
                    if (!columnNames.contains(columnName)) { 
                        boolean isPrimaryKey = isPrimaryKey(tableName, columnName);
                        boolean isForeignKey = isForeignKey(tableName, columnName);
                        String foreignTable = isForeignKey ? getForeignTable(tableName, columnName) : null;
                        String foreignColumnName = isForeignKey ? getForeignColumnName(tableName, columnName) : null;
                        table.addColumn(new Column(columnName, columnType, isPrimaryKey, isForeignKey, foreignTable, foreignColumnName));
                        columnNames.add(columnName); 
                    }
                }
            }
        }
        return table;
    }

    private boolean isSystemColumnType(String columnType) {
        String[] systemColumnTypes = {"INT", "VARCHAR", "CHAR", "DATETIME", "DATE", "TIME", "TIMESTAMP", "DECIMAL", "BIGINT", "DOUBLE", "FLOAT", "BIT", "BINARY"};
        for (String type : systemColumnTypes) {
            if (columnType.toUpperCase().contains(type)) {
                return true;
            }
        }
        return false;
    }


    public Table getTable(String selectedTable) throws SQLException {
        return getTableStructure(selectedTable);
    }

    public Table getAssociatedTable(Table table) throws SQLException {
        String tableName = table.getName();
        Table associatedTable = null; // Initialisation à null car nous recherchons la table associée

        for (String foreignTableName : getTables()) {
            if (!foreignTableName.equals(tableName)) {
                Table foreignTable = getTableStructure(foreignTableName);
                for (Column column : foreignTable.getColumns()) {
                    if (column.isForeignKey() && column.getForeignTable().equals(tableName)) {
                        // Si la colonne est une clé étrangère et qu'elle référence la table principale
                        associatedTable = foreignTable;
                        break; // Sortir de la boucle interne car nous avons trouvé la table associée
                    }
                }
            }
            if (associatedTable != null) {
                break; // Sortir de la boucle externe si la table associée a été trouvée
            }
        }

        return associatedTable;
    }


}
