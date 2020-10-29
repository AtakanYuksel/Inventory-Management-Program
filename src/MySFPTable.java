import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class MySFPTable extends JTable {
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

    public MySFPTable(MyXML_DAO database){
        super(new MySFTPTableModel(database));

        setPreferredScrollableViewportSize(new Dimension(900, 200));
        setFillsViewportHeight(true);
    }

    public void refreshTable(){
        setModel(new MySFTPTableModel(database));

        setPreferredScrollableViewportSize(new Dimension(900, 200));
        setFillsViewportHeight(true);
    }

    static class MySFTPTableModel extends AbstractTableModel {
        private String[] columnNames = {"Sıra", "Lokasyon", "Model", "Seri No", "IP"};
        private String[][] data;

        public MySFTPTableModel(MyXML_DAO database){
            MySFPTable.database = database;
            ArrayList<MySFP> modules = database.getAllSFPs();
            this.data = new String[modules.size()][5];

            for (int i = 0; i < modules.size(); i++) {
                MySFP currentSFP = modules.get(i);
                data[i][0] = Integer.toString(i + 1);
                data[i][1] = currentSFP.getLocation();
                data[i][2] = currentSFP.getModel();
                data[i][3] = currentSFP.getSerial_number();
                data[i][4] = currentSFP.getIP();
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
