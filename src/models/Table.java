package models;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private String name;
    private List<Column> columns;
    private List<String> primaryKeys;
    private List<Table> associatedTables;
    
    public Table(String name) {
        this.name = name;
        this.columns = new ArrayList<>();
        this.primaryKeys = new ArrayList<>();
        this.associatedTables = new ArrayList<>(); 
    }

    public String getName() {
        return name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public void addPrimaryKey(String columnName) {
        primaryKeys.add(columnName);
    }
    
    public void addColumn(String name, String type, boolean isPrimaryKey, boolean isForeignKey, String foreignTable, String foreignColumnName) {
        Column column = new Column(name, type, isPrimaryKey, isForeignKey, foreignTable);
        columns.add(column);}
    
    public void addColumn(Column column) {
        columns.add(column);
    }

    public List<Table> getAssociatedTables() {
        return associatedTables;
    }

    public void addAssociatedTable(Table table) {
        associatedTables.add(table);
    }
}
