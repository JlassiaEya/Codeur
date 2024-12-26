package dao;

import models.Table;
import models.Column;


public class JavaCodeGenerator {
    public static String generateClass(Table table) {
        StringBuilder classCode = new StringBuilder();
        String className = capitalizeFirstLetter(table.getName());

        classCode.append("import java.time.LocalDateTime;\n");
        classCode.append("import java.util.List;\n");
        classCode.append("import java.util.ArrayList;\n\n");

        classCode.append("public class ").append(className).append(" {\n");

        // Générer les champs
        for (Column column : table.getColumns()) {
            String fieldType = getJavaType(column.getType());
            if (column.isForeignKey()) {
                fieldType = capitalizeFirstLetter(column.getForeignTable());
                classCode.append("    // ManyToOne relation with ").append(column.getForeignTable()).append("\n");
            }
            classCode.append("    private ").append(fieldType).append(" ").append(column.getName()).append(";\n");
        }

        // Générer les collections pour les relations ManyToMany
        for (Column column : table.getColumns()) {
            if (column.isManyToMany()) {
                String associatedTableName = capitalizeFirstLetter(column.getManyToManyTable());
                classCode.append("    // ManyToMany relation with ").append(column.getManyToManyTable()).append("\n");
                classCode.append("    private List<").append(associatedTableName).append("> ").append(column.getName()).append(" = new ArrayList<>();\n");
            }
        }

        // Générer le constructeur sans champs
        classCode.append("\n    // Constructeur sans champs\n");
        classCode.append("    public ").append(className).append("() {\n");
        classCode.append("        // Initialisez les champs si nécessaire\n");
        classCode.append("    }\n");

        // Générer le constructeur avec champs
        classCode.append("\n    // Constructeur avec champs\n");
        classCode.append("    public ").append(className).append("(");
        boolean first = true;
        for (Column column : table.getColumns()) {
            if (!first) classCode.append(", ");
            String fieldType = getJavaType(column.getType());
            if (column.isForeignKey()) {
                fieldType = capitalizeFirstLetter(column.getForeignTable());
            }
            classCode.append(fieldType).append(" ").append(column.getName());
            first = false;
        }
        classCode.append(") {\n");
        for (Column column : table.getColumns()) {
            classCode.append("        this.").append(column.getName()).append(" = ").append(column.getName()).append(";\n");
        }
        classCode.append("    }\n");

        // Générer getters et setters
        for (Column column : table.getColumns()) {
            String fieldType = getJavaType(column.getType());
            if (column.isForeignKey()) {
                fieldType = capitalizeFirstLetter(column.getForeignTable());
            }
            String capitalizedField = capitalizeFirstLetter(column.getName());
            classCode.append("\n    public ").append(fieldType).append(" get").append(capitalizedField).append("() {\n");
            classCode.append("        return ").append(column.getName()).append(";\n");
            classCode.append("    }\n");

            classCode.append("\n    public void set").append(capitalizedField).append("(").append(fieldType).append(" ").append(column.getName()).append(") {\n");
            classCode.append("        this.").append(column.getName()).append(" = ").append(column.getName()).append(";\n");
            classCode.append("    }\n");
        }

        // Générer toString
        classCode.append("\n    @Override\n");
        classCode.append("    public String toString() {\n");
        classCode.append("        return \"").append(className).append("{\" +\n");
        first = true;
        for (Column column : table.getColumns()) {
            if (!first) classCode.append(" + \", ");
            classCode.append(column.getName()).append("='\" + ").append(column.getName()).append(" + '\\''");
            first = false;
        }
        classCode.append(" + '}';\n");
        classCode.append("    }\n");

        classCode.append("}\n");

        return classCode.toString();
    }

    private static String getJavaType(String sqlType) {
        switch (sqlType.toLowerCase()) {
            case "bigint":
                return "Long";
            case "int":
                return "Integer";
            case "varchar":
                return "String";
            case "datetime":
                return "LocalDateTime";
            case "boolean":
                return "Boolean";
            case "float":
                return "Float";
            case "double":
                return "Double";
            default:
                return "Object";
        }
    }

    private static String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
