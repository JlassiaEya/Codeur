package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GeneratorView extends JFrame {
    private JButton generateButton;
    private JComboBox<String> languageSelection;
    private JComboBox<String> interfaceTypeSelection;
    private JComboBox<String> tableSelection;
    private JCheckBox includeAssociatedClassesCheckbox;
    private JCheckBox includeAssociatedInterfacesCheckbox;

    public GeneratorView() {
        setTitle("Code Generator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE); 

        JPanel topPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        topPanel.setBackground(Color.WHITE); 
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE); 

        languageSelection = new JComboBox<>(new String[]{"Java", "Python"});
        interfaceTypeSelection = new JComboBox<>(new String[]{"Swing", "Html"});
        tableSelection = new JComboBox<>();
        generateButton = new JButton("Generate");
        generateButton.setPreferredSize(new Dimension(150, 30));

        includeAssociatedClassesCheckbox = new JCheckBox("Include Associated Classes");
        includeAssociatedInterfacesCheckbox = new JCheckBox("Include Associated Interfaces");

        applyComponentStyles(languageSelection);
        applyComponentStyles(interfaceTypeSelection);
        applyComponentStyles(tableSelection);
        applyComponentStyles(generateButton);
        applyComponentStyles(includeAssociatedClassesCheckbox);
        applyComponentStyles(includeAssociatedInterfacesCheckbox);

        topPanel.add(createStyledLabel("Select Language:"));
        topPanel.add(languageSelection);
        topPanel.add(createStyledLabel("Select Interface Type:"));
        topPanel.add(interfaceTypeSelection);
        topPanel.add(createStyledLabel("Select Table:"));
        topPanel.add(tableSelection);
        topPanel.add(includeAssociatedClassesCheckbox);
        topPanel.add(includeAssociatedInterfacesCheckbox);
        bottomPanel.add(generateButton);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLUE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private void applyComponentStyles(JComponent component) {
        component.setBackground(Color.WHITE);
        component.setForeground(Color.BLACK);
        if (component instanceof JButton) {
            ((JButton) component).setContentAreaFilled(false);
            component.setOpaque(true);
            component.setBorder(BorderFactory.createLineBorder(Color.BLUE)); 
        }
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    public JComboBox<String> getLanguageSelection() {
        return languageSelection;
    }

    public JComboBox<String> getInterfaceTypeSelection() {
        return interfaceTypeSelection;
    }

    public JComboBox<String> getTableSelection() {
        return tableSelection;
    }

    public JCheckBox getIncludeAssociatedClassesCheckbox() {
        return includeAssociatedClassesCheckbox;
    }

    public JCheckBox getIncludeAssociatedInterfacesCheckbox() {
        return includeAssociatedInterfacesCheckbox;
    }

    public void loadTables(List<String> tableNames) {
        for (String tableName : tableNames) {
            tableSelection.addItem(tableName);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GeneratorView view = new GeneratorView();
            view.setVisible(true);
        });
    }
}
