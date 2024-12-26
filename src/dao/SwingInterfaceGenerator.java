package dao;



import models.Column;
import models.Table;

public class SwingInterfaceGenerator {

    public static  String generateSwingInterface(Table table) {
        StringBuilder swingCode = new StringBuilder();
        swingCode.append("import javax.swing.*;\n");
        swingCode.append("import java.awt.*;\n\n");
        swingCode.append("public class ").append(table.getName()).append("Interface extends JFrame {\n\n");
        swingCode.append("    private JTextField[] textFields;\n");
        swingCode.append("    private JButton addButton, updateButton, deleteButton;\n\n");
        swingCode.append("    public ").append(table.getName()).append("Interface() {\n");
        swingCode.append("        setTitle(\"").append(table.getName()).append(" Interface\");\n");
        swingCode.append("        setSize(400, 300);\n");
        swingCode.append("        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n");
        swingCode.append("        setLayout(new GridLayout(").append(table.getColumns().size() + 3).append(", 2));\n\n");
        
        // Ajouter les champs de texte pour chaque colonne
        swingCode.append("        textFields = new JTextField[").append(table.getColumns().size()).append("];\n");
        for (int i = 0; i < table.getColumns().size(); i++) {
            Column column = table.getColumns().get(i);
            swingCode.append("        textFields[").append(i).append("] = new JTextField();\n");
            swingCode.append("        add(new JLabel(\"").append(column.getName()).append(":\"));\n");
            swingCode.append("        add(textFields[").append(i).append("]);\n\n");
        }
        
        // Ajouter les boutons Ajouter, Mettre à jour et Supprimer
        swingCode.append("        addButton = new JButton(\"Ajouter\");\n");
        swingCode.append("        updateButton = new JButton(\"Mettre à jour\");\n");
        swingCode.append("        deleteButton = new JButton(\"Supprimer\");\n\n");
        swingCode.append("        add(addButton);\n");
        swingCode.append("        add(updateButton);\n");
        swingCode.append("        add(deleteButton);\n\n");
        
        swingCode.append("        setVisible(true);\n");
        swingCode.append("    }\n\n");
        
        swingCode.append("    public static void main(String[] args) {\n");
        swingCode.append("        SwingUtilities.invokeLater(() -> new ").append(table.getName()).append("Interface());\n");
        swingCode.append("    }\n\n");
        
        swingCode.append("}");
        return swingCode.toString();
    }
    
   
}
