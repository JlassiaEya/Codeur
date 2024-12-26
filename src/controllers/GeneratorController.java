package controllers;

import models.Column;
import models.Table;
import utilitaires.Utilitaire;
import view.GeneratorView;
import dao.DatabaseSchema;
import dao.HtmlInterfaceGenerator;
import dao.JavaCodeGenerator;
import dao.PythonCodeGenerator;
import dao.SwingInterfaceGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneratorController {
    private GeneratorView view;
    private DatabaseSchema schema;

    public GeneratorController(GeneratorView view, DatabaseSchema schema) {
        this.view = view;
        this.schema = schema;
        addEventHandlers();
        loadTablesFromDatabase();
    }

    private void addEventHandlers() {
        view.getGenerateButton().addActionListener(e -> {
            String language = (String) view.getLanguageSelection().getSelectedItem();
            String selectedTable = (String) view.getTableSelection().getSelectedItem();
            String interfaceType = (String) view.getInterfaceTypeSelection().getSelectedItem();
            boolean generateAssociatedClasses = view.getIncludeAssociatedClassesCheckbox().isSelected();
            boolean generateAssociatedInterfaces = view.getIncludeAssociatedInterfacesCheckbox().isSelected();

            try {
                Table table = schema.getTableStructure(selectedTable);

                if (interfaceType.equals("Html")) {
                    String htmlInterface = HtmlInterfaceGenerator.generateHtmlInterface(table);
                    writeToFile(htmlInterface, selectedTable + "Interface.html");
                    if (generateAssociatedInterfaces) {
                        generateAssociatedInterfaces(table, "Html");
                    }
                } else if (interfaceType.equals("Swing")) {
                    String swingInterface = SwingInterfaceGenerator.generateSwingInterface(table);
                    writeToFile(swingInterface, selectedTable + "Interface.java");
                    if (generateAssociatedInterfaces) {
                        generateAssociatedInterfaces(table, "Swing");
                    }
                }

                if (language.equals("Java")) {
                    String javaClass = JavaCodeGenerator.generateClass(table);
                    writeToFile(javaClass, selectedTable + ".java");

                    if (generateAssociatedClasses) {
                        generateAssociatedClasses(table, "Java");
                    }
                } else if (language.equals("Python")) {
                    String pythonClass = PythonCodeGenerator.generateClass(table);
                    writeToFile(pythonClass, selectedTable + ".py");

                    if (generateAssociatedClasses) {
                        generateAssociatedClasses(table, "Python");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }


    private void loadTablesFromDatabase() {
        List<String> tableNames = new ArrayList<>();
        try {
            Utilitaire.seConnecter("database.properties");
            ResultSet rs = Utilitaire.OuvrirReq("SHOW TABLES");
            while (rs.next()) {
                tableNames.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.loadTables(tableNames);
    }

    private void writeToFile(String content, String outputFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true))) {
            writer.write(content);
            System.out.println("Code généré avec succès dans le fichier " + outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateAssociatedClasses(Table table, String language) throws SQLException {
        for (Column column : table.getColumns()) {
            if (column.isForeignKey()) {
                String associatedTableName = column.getForeignTable();
                Table associatedTable = schema.getTableStructure(associatedTableName);
                String associatedClass = "";
                if (language.equals("Java")) {
                    associatedClass = JavaCodeGenerator.generateClass(associatedTable);
                    writeToFile(associatedClass, associatedTableName + "." + language.toLowerCase());
                } else if (language.equals("Python")) {
                    associatedClass = PythonCodeGenerator.generateClass(associatedTable);
                    writeToFile(associatedClass, associatedTableName + ".py");
                }
            }
        }
    }

    private void generateAssociatedInterfaces(Table table, String language) throws SQLException {
        for (Column column : table.getColumns()) {
            if (column.isForeignKey()) {
                String associatedTableName = column.getForeignTable();
                Table associatedTable = schema.getTableStructure(associatedTableName);
                String associatedInterface = "";

                if (language.equals("Html")) {
                    associatedInterface = HtmlInterfaceGenerator.generateHtmlInterface(associatedTable);
                    writeToFile(associatedInterface,associatedTableName+ "." + "Interface.html");
                } else if (language.equals("Swing")) {
                    associatedInterface = SwingInterfaceGenerator.generateSwingInterface(associatedTable);
                    writeToFile(associatedInterface,associatedTableName+ "." + "Interface.java");
                }
            }
        }
    }


}
