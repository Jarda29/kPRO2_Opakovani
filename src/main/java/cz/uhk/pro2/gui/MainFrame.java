package cz.uhk.pro2.gui;

import cz.uhk.pro2.models.Person;
import cz.uhk.pro2.models.io.FileOperations;
import cz.uhk.pro2.models.io.JsonFileOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private List<Person> personList = new ArrayList<>();

    private PersonListTableModel personListTableModel;

    private FileOperations fileOperations;

    public MainFrame(int width, int height, FileOperations fileOperations){
        super("PRO2 Opakování");

        this.fileOperations = fileOperations;

        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initGui();

    }

    private void initGui(){
        JPanel panelMain = new JPanel(new BorderLayout());

        panelMain.add(initInputPanel(), BorderLayout.NORTH);
        panelMain.add(initPersonPanel(), BorderLayout.CENTER);
        add(panelMain);

        setJMenuBar(initMenu());
    }

    private JPanel initInputPanel(){
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Jméno"));
        JTextField txtInputName = new JTextField("", 20);
        inputPanel.add(txtInputName);

        inputPanel.add(new JLabel("Věk"));
        JTextField txtInputAge = new JTextField("", 5);
        inputPanel.add(txtInputAge);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(e -> {
            String name = txtInputName.getText();
            int age = Integer.valueOf(txtInputAge.getText());
            personList.add(new Person(name, age));

            personListTableModel.fireTableDataChanged();
            txtInputName.setText("");
            txtInputAge.setText("");
        });
        inputPanel.add(btnAdd);

        return inputPanel;
    }

    private JPanel initPersonPanel(){
        JPanel panelTbl = new JPanel();
        Object[][] data = new Object[][]{
                {"AA", "AB"},
                {"AC", "BA"},
                {"AD", "BY"},
        };
        String[] colNames = new String[] {"Col1", "Col2"};

        //JTable tblPersons = new JTable(data, colNames);

        personListTableModel = new PersonListTableModel(personList);

        JTable tblPersons = new JTable();
        tblPersons.setAutoCreateRowSorter(true);
        tblPersons.setModel(personListTableModel);

        JScrollPane scrollPane = new JScrollPane(tblPersons);
        scrollPane.setPreferredSize(new Dimension(750,500));
        panelTbl.add(scrollPane);
        return panelTbl;
    }

    private JMenuBar initMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("Soubor");
        menuFile.add(new AbstractAction("Uložit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileOperations.writeToFile(personList);
            }
        });
        menuFile.add(new AbstractAction("Načti") {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePersonList(fileOperations.loadFromFile());
                personListTableModel.fireTableDataChanged();
            }
        });
        menuFile.add(new AbstractAction("Nastav JSON") {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileOperations = new JsonFileOperations();
            }
        });

        menuBar.add(menuFile);
        return menuBar;
    }

    private void updatePersonList(List<Person> list){
        personList.clear();
        for (Person p:list) {
            personList.add(p);
        }
    }
}
