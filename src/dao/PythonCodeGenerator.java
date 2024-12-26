package dao;

import models.Table;
import models.Column;


public class PythonCodeGenerator {
    public static String generateClass(Table table) {
        // Générer le code Python pour la classe représentant la table
        StringBuilder sb = new StringBuilder();
        String className = capitalizeFirstLetter(table.getName());
        
        sb.append("class ").append(className).append(":\n");

        // Générer le constructeur avec initialisation des attributs
        sb.append("    def __init__(self");
        for (Column column : table.getColumns()) {
            sb.append(", ").append(column.getName()).append(": ")
              .append(mapTypeToPython(column.getType()));
        }
        sb.append("):\n");
        for (Column column : table.getColumns()) {
            sb.append("        self.").append(column.getName()).append(" = ")
              .append(column.getName()).append("\n");
        }

        // Générer les méthodes de getter et setter pour chaque attribut
        for (Column column : table.getColumns()) {
            sb.append("\n")
              .append("    def get_").append(column.getName()).append("(self):\n")
              .append("        return self.").append(column.getName()).append("\n")
              .append("\n")
              .append("    def set_").append(column.getName()).append("(self, value):\n")
              .append("        self.").append(column.getName()).append(" = value\n");
        }

        // Générer les collections pour les relations ManyToMany
        for (Column column : table.getColumns()) {
            if (column.isManyToMany()) {
                String associatedTableName = capitalizeFirstLetter(column.getManyToManyTable());
                sb.append("\n")
                  .append("    def add_").append(column.getName()).append("(self, item: ")
                  .append(associatedTableName).append("):\n")
                  .append("        if not hasattr(self, '").append(column.getName()).append("'):\n")
                  .append("            self.").append(column.getName()).append(" = []\n")
                  .append("        self.").append(column.getName()).append(".append(item)\n")
                  .append("\n")
                  .append("    def remove_").append(column.getName()).append("(self, item: ")
                  .append(associatedTableName).append("):\n")
                  .append("        if hasattr(self, '").append(column.getName()).append("'):\n")
                  .append("            self.").append(column.getName()).append(".remove(item)\n");
            }
        }

        // Générer la méthode __str__()
        sb.append("\n")
          .append("    def __str__(self):\n")
          .append("        return \"").append(className).append("{\" +\n");
        for (Column column : table.getColumns()) {
            sb.append("               \"").append(column.getName()).append("='\" + str(self.")
              .append(column.getName()).append(") + '\\'',\" +\n");
        }
        sb.append("               '}\'\n");

        return sb.toString();
    }

    private static String mapTypeToPython(String sqlType) {
        switch (sqlType.toLowerCase()) {
            case "bigint":
                return "int";
            case "int":
                return "int";
            case "varchar":
                return "str";
            case "datetime":
                return "datetime.datetime";
            case "boolean":
                return "bool";
            case "float":
                return "float";
            case "double":
                return "float";
            default:
                return "object";
        }
    }

    private static String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
