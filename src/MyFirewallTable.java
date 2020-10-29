import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MyFirewallTable extends JTable {
    static MyXML_DAO database;

    public MyFirewallTable(MyXML_DAO database){
        super(new MyFirewallTableModel(database));

        getColumnModel().getColumn(0).setPreferredWidth(20);
        getColumnModel().getColumn(3).setPreferredWidth(50);
        getColumnModel().getColumn(4).setPreferredWidth(150);
        getColumnModel().getColumn(5).setPreferredWidth(100);
        getColumnModel().getColumn(6).setPreferredWidth(50);
        getColumnModel().getColumn(7).setPreferredWidth(100);


        setPreferredScrollableViewportSize(new Dimension(900, 200));
        setFillsViewportHeight(true);

    }

    public void refreshTable(){
        setModel(new MyFirewallTableModel(database));

        getColumnModel().getColumn(0).setPreferredWidth(20);
        getColumnModel().getColumn(3).setPreferredWidth(50);
        getColumnModel().getColumn(4).setPreferredWidth(150);
        getColumnModel().getColumn(5).setPreferredWidth(100);
        getColumnModel().getColumn(6).setPreferredWidth(50);
        getColumnModel().getColumn(7).setPreferredWidth(100);

        setPreferredScrollableViewportSize(new Dimension(900, 200));
        setFillsViewportHeight(true);
    }

    static class MyFirewallTableModel extends AbstractTableModel {
        private String[] columnNames = {"Sıra", "Konum", "Lokasyon", "Yer", "İsim", "IP", "Marka", "Model", "Seri No", "Yazılım Versiyonu"};
        private String[][] data;

        public MyFirewallTableModel(MyXML_DAO database){
            MyFirewallTable.database = database;
            ArrayList<MyFirewall> firewalls = database.getAllFirewalls();
            this.data = new String[firewalls.size()][10];

            for (int i = 0; i < firewalls.size(); i++) {
                MyFirewall currentFirewall = firewalls.get(i);
                data[i][0] = Integer.toString(i + 1);
                data[i][1] = currentFirewall.getPlace();
                data[i][2] = currentFirewall.getLocation();
                data[i][3] = currentFirewall.getFloor();
                data[i][4] = currentFirewall.getName();
                data[i][5] = currentFirewall.getIP();
                data[i][6] = currentFirewall.getMake();
                data[i][7] = currentFirewall.getModel();
                data[i][8] = currentFirewall.getSerialNumber();
                data[i][9] = currentFirewall.getSoftwareVersion();
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
