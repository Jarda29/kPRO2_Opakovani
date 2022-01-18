package cz.uhk.pro2.gui;

import cz.uhk.pro2.models.Person;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PersonListTableModel extends AbstractTableModel {
    private List<Person> personList;
    public PersonListTableModel(List<Person> personList){
        this.personList = personList;
    }

    @Override
    public int getRowCount() {
        return personList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex==0){
            return personList.get(rowIndex).getName();
        }
        else if(columnIndex == 1) {
            return personList.get(rowIndex).getAge();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column)
        {
            case 0: return "Jméno";
            case 1: return "Věk";
            default: return null;
        }
    }
}
