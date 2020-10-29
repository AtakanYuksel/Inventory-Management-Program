import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class MyModuleTable extends JTable {
    static MyXML_DAO database;

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        JComponent component = (JComponent) super.prepareRenderer(renderer, row, column);

        if (row == 0){
            if (!getModel().getValueAt(row, 4).equals(getModel().getValueAt(row + 1, 4))){
                component.setBorder(BorderFactory.createMatteBorder(2,0,2,0, Color.BLUE));
            } else {
                component.setBorder(BorderFactory.createMatteBorder(2,0,0,0, Color.BLUE));
            }
        } else if (row == getModel().getRowCount() - 1){
            component.setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.BLUE));
        } else if (row != getModel().getRowCount() - 1){
            if (!getModel().getValueAt(row, 4).equals(getModel().getValueAt(row + 1, 4))){
                component.setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.BLUE));
            }
        }
        return component;
    }

    public MyModuleTable(MyXML_DAO database){
        super(new MyModuleTableModel(database));


        setPreferredScrollableViewportSize(new Dimension(900, 200));
        setFillsViewportHeight(true);

    }

    public void refreshTable(){
        setModel(new MyModuleTableModel(database));


        setPreferredScrollableViewportSize(new Dimension(900, 200));
        setFillsViewportHeight(true);
    }

    static class MyModuleTableModel extends AbstractTableModel {
        private String[] columnNames = {"Sıra", "Lokasyon", "Model", "Seri No", "IP"};
        private String[][] data;

        public MyModuleTableModel(MyXML_DAO database){
            MyModuleTable.database = database;
            ArrayList<MyModule> modules = database.getAllModules();
            this.data = new String[modules.size()][5];

            for (int i = 0; i < modules.size(); i++) {
                MyModule currentModule = modules.get(i);
                data[i][0] = Integer.toString(i + 1);
                data[i][1] = currentModule.getLocation();
                data[i][2] = currentModule.getModel();
                data[i][3] = currentModule.getSerial_number();
                data[i][4] = currentModule.getIP();
            }
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int i, int j) {
            return data[i][j];
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }


    }
}
