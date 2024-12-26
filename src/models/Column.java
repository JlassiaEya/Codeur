package models;

public class Column {
    private String name;
    private String type;
    private boolean isPrimaryKey;
    private boolean isForeignKey;
    private boolean manyToMany; 
    private String foreignTable; 
    private String manyToManyTable;
    private String foreignColumnName;
    
    public Column(String name, String type, boolean isPrimaryKey, boolean isForeignKey, String foreignTable) {
        this.name = name;
        this.type = type;
        this.isPrimaryKey = isPrimaryKey;
        this.isForeignKey = isForeignKey;
        this.foreignTable = foreignTable;

    }
    
    public Column(String name, String type, boolean isPrimaryKey, boolean isForeignKey, String foreignTable, String foreignColumnName) {
        this.name = name;
        this.type = type;
        this.isPrimaryKey = isPrimaryKey;
        this.isForeignKey = isForeignKey;
        this.foreignTable = foreignTable;
        this.foreignColumnName = foreignColumnName;
    }
    
    public Column(String name, String type) {
        this.name = name;
        this.type = type;
        this.isPrimaryKey = false;
        this.isForeignKey = false;
        this.foreignTable = null;
    }

    public Column(String columnName, String columnType, boolean isPrimaryKey2, boolean isForeignKey2) {
        this.name = columnName;
        this.type = columnType;
        this.isPrimaryKey = isPrimaryKey2;
        this.isForeignKey = isForeignKey2;
        this.foreignTable = null;
        this.foreignColumnName = null;
    }


	public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public boolean isForeignKey() {
        return isForeignKey;
    }

    public String getForeignTable() {
        return foreignTable;
    }

    public String getForeignKeyColumnName() {
        return foreignColumnName;
    }
    
    public void setForeignKeyColumnName(String foreignColumnName) {
        this.foreignColumnName = foreignColumnName;
    }

    public void setForeignKey(boolean foreignKey) {
        isForeignKey = foreignKey;
    }

    public void setForeignTable(String foreignTable) {
        this.foreignTable = foreignTable;
    }

    public boolean isManyToMany() {
        return manyToMany;
    }

    public void setManyToMany(boolean manyToMany) {
        this.manyToMany = manyToMany;
    }

    public String getManyToManyTable() {
        return manyToManyTable;
    }

    public void setManyToManyTable(String manyToManyTable) {
        this.manyToManyTable = manyToManyTable;
    }
}
