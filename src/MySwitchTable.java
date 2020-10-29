import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MySwitchTable extends JTable implements ActionListener {
    static MyXML_DAO database;

    JPopupMenu mySwitchTablePopupMenu;
    JMenuItem mySwitchTablePopupMenuAddToStack;
    JMenuItem mySwitchTablePopupMenuAddModuleToStack;
    JMenuItem mySwitchTablePopupMenuRemove;
    JMenuItem mySwitchTablePopupMenuUpdate;
    JMenuItem mySwitchTablePopupMenuDetails;

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        JComponent component = (JComponent) super.prepareRenderer(renderer, row, column);

        if (row == 0){
            if (!getModel().getValueAt(row, 5).equals(getModel().getValueAt(row + 1, 5))){
                component.setBorder(BorderFactory.createMatteBorder(2,0,2,0, Color.BLUE));
            } else {
                component.setBorder(BorderFactory.createMatteBorder(2,0,0,0, Color.BLUE));
            }
        } else if (row == getModel().getRowCount() - 1){
            component.setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.BLUE));
        } else if (row != getModel().getRowCount() - 1){
            if (!getModel().getValueAt(row, 5).equals(getModel().getValueAt(row + 1, 5))){
                component.setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.BLUE));
            }
        }
        return component;
    }

    public MySwitchTable(MyXML_DAO database){
        super(new MySwitchTableModel(database));

        getColumnModel().getColumn(0).setPreferredWidth(20);
        getColumnModel().getColumn(3).setPreferredWidth(50);
        getColumnModel().getColumn(4).setPreferredWidth(150);
        getColumnModel().getColumn(5).setPreferredWidth(100);
        getColumnModel().getColumn(6).setPreferredWidth(50);
        getColumnModel().getColumn(7).setPreferredWidth(100);

        mySwitchTablePopupMenu = new JPopupMenu();

        mySwitchTablePopupMenuAddToStack = new JMenuItem("Stack'e Ekle...");
        mySwitchTablePopupMenuAddToStack.addActionListener(this);
        mySwitchTablePopupMenu.add(mySwitchTablePopupMenuAddToStack);

        mySwitchTablePopupMenuAddModuleToStack = new JMenuItem("Switch'e Modül Ekle");
        mySwitchTablePopupMenuAddModuleToStack.addActionListener(this);
        mySwitchTablePopupMenu.add(mySwitchTablePopupMenuAddModuleToStack);

        mySwitchTablePopupMenu.addSeparator();

        mySwitchTablePopupMenuRemove = new JMenuItem("Sil");
        mySwitchTablePopupMenuRemove.addActionListener(this);
        mySwitchTablePopupMenu.add(mySwitchTablePopupMenuRemove);

        mySwitchTablePopupMenuUpdate = new JMenuItem("Güncelle...");
        mySwitchTablePopupMenuUpdate.addActionListener(this);
        mySwitchTablePopupMenu.add(mySwitchTablePopupMenuUpdate);

        mySwitchTablePopupMenu.addSeparator();

        mySwitchTablePopupMenuDetails = new JMenuItem("Detaylar...");
        mySwitchTablePopupMenuDetails.addActionListener(this);
        mySwitchTablePopupMenu.add(mySwitchTablePopupMenuDetails);

        setComponentPopupMenu(mySwitchTablePopupMenu);


        setPreferredScrollableViewportSize(new Dimension(900, 200));
        setFillsViewportHeight(true);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == mySwitchTablePopupMenuAddToStack){
            if (getSelectedRowCount() == 1){
                int row = getSelectedRow();
                JPanel mySwitchTablePopupMenuAddToStackPanel = new JPanel(new GridBagLayout());

                String oldPlace = (String) getModel().getValueAt(row, 1);
                String oldLocation = (String) getModel().getValueAt(row, 2);
                String oldFloor = (String) getModel().getValueAt(row, 3);
                String oldName = (String) getModel().getValueAt(row, 4);
                String oldIP = (String) getModel().getValueAt(row, 5);
                String oldMake = (String) getModel().getValueAt(row, 6);
                String oldModel = (String) getModel().getValueAt(row, 7);
                String oldSerialNumber = (String) getModel().getValueAt(row, 8);
                String oldSoftwareVersion = (String) getModel().getValueAt(row, 9);

                String[] allPlaces = database.getAllPlaces();
                JComboBox place = new JComboBox(allPlaces);
                for (int i = 0; i < allPlaces.length; i++) {
                    if (allPlaces[i].equals(oldPlace)){
                        place.setSelectedIndex(i);
                    }
                }
                place.setEditable(false);

                JTextField location = new JTextField(oldLocation,10);
                location.setEditable(false);

                JTextField floor = new JTextField(oldFloor,10);
                floor.setEditable(false);

                JTextField name = new JTextField(oldName, 10);
                name.setEditable(false);

                JTextField IP = new JTextField(oldIP,10);
                IP.setEditable(false);

                String[] allMakes = database.getAllSwitchMakes();
                JComboBox make = new JComboBox(allMakes);
                for (int i = 0; i < allMakes.length; i++) {
                    if (allMakes[i].equals(oldMake)){
                        make.setSelectedIndex(i);
                    }
                }
                make.setEditable(false);

                String[] allModels = database.getAllSwitchModelsByMake(oldMake);
                JComboBox model = new JComboBox(allModels);

                JTextField serialNumber = new JTextField(oldSerialNumber,10);
                JTextField softwareVersion = new JTextField(oldSoftwareVersion,10);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.FIRST_LINE_START;
                gbc.weightx = 0.0;
                gbc.weighty = 0.0;
                gbc.insets = new Insets(5,5,5,5);

                mySwitchTablePopupMenuAddToStackPanel.add(new JLabel("Konum: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuAddToStackPanel.add(place, gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuAddToStackPanel.add(new JLabel("Lokasyon: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuAddToStackPanel.add(location, gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuAddToStackPanel.add(new JLabel("Yer: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuAddToStackPanel.add(floor, gbc);

                gbc.gridx = 0;
                gbc.gridy++;
                mySwitchTablePopupMenuAddToStackPanel.add(new JLabel("İsim: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuAddToStackPanel.add(name, gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuAddToStackPanel.add(new JLabel("IP: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuAddToStackPanel.add(IP, gbc);

                gbc.gridx = 0;
                gbc.gridy++;
                mySwitchTablePopupMenuAddToStackPanel.add(new JLabel("Marka: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuAddToStackPanel.add(make, gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuAddToStackPanel.add(new JLabel("Model: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuAddToStackPanel.add(model, gbc);

                gbc.gridx = 0;
                gbc.gridy++;
                mySwitchTablePopupMenuAddToStackPanel.add(new JLabel("Seri NO: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuAddToStackPanel.add(serialNumber, gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuAddToStackPanel.add(new JLabel("Yazılım Versiyonu: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuAddToStackPanel.add(softwareVersion, gbc);

                int result = JOptionPane.showConfirmDialog(new JFrame(), mySwitchTablePopupMenuAddToStackPanel, "Stack'e eklemek istediğiniz Switch değerlerini giriniz.", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION){
                    database.addSwitch(place.getSelectedItem().toString(), location.getText(), floor.getText(), name.getText(), IP.getText(), make.getSelectedItem().toString(), model.getSelectedItem().toString(), serialNumber.getText(), softwareVersion.getText());
                    refreshTable();
                }
            }
        }
        else if (actionEvent.getSource() == mySwitchTablePopupMenuAddModuleToStack){
            if (getSelectedRowCount() == 1){
                int row = getSelectedRow();
                String IP = (String) getModel().getValueAt(row, 5);


            }
        }
        else if (actionEvent.getSource() == mySwitchTablePopupMenuRemove){
            if (getSelectedRowCount() == 1){
                int row = getSelectedRow();
                String place = (String) getModel().getValueAt(row, 1);
                String location = (String) getModel().getValueAt(row, 2);
                String floor = (String) getModel().getValueAt(row, 3);
                String name = (String) getModel().getValueAt(row, 4);
                String IP = (String) getModel().getValueAt(row, 5);
                String make = (String) getModel().getValueAt(row, 6);
                String model = (String) getModel().getValueAt(row, 7);
                String serialNumber = (String) getModel().getValueAt(row, 8);
                String softwareVersion = (String) getModel().getValueAt(row, 9);

                database.removeSwitch(place, location, floor, name, IP, make, model, serialNumber, softwareVersion);
                refreshTable();
            }
        }
        else if (actionEvent.getSource() == mySwitchTablePopupMenuUpdate){
            if (getSelectedRowCount() == 1){
                int row = getSelectedRow();
                JPanel mySwitchTablePopupMenuUpdatePanel = new JPanel(new GridBagLayout());

                String oldPlace = (String) getModel().getValueAt(row, 1);
                String oldLocation = (String) getModel().getValueAt(row, 2);
                String oldFloor = (String) getModel().getValueAt(row, 3);
                String oldName = (String) getModel().getValueAt(row, 4);
                String oldIP = (String) getModel().getValueAt(row, 5);
                String oldMake = (String) getModel().getValueAt(row, 6);
                String oldModel = (String) getModel().getValueAt(row, 7);
                String oldSerialNumber = (String) getModel().getValueAt(row, 8);
                String oldSoftwareVersion = (String) getModel().getValueAt(row, 9);

                String[] allPlaces = database.getAllPlaces();
                JComboBox place = new JComboBox(allPlaces);
                for (int i = 0; i < allPlaces.length; i++) {
                    if (allPlaces[i].equals(oldPlace)){
                        place.setSelectedIndex(i);
                    }
                }

                JTextField location = new JTextField(oldLocation,10);
                JTextField floor = new JTextField(oldFloor,10);
                JTextField name = new JTextField(oldName, 10);
                JTextField IP = new JTextField(oldIP,10);

                String[] allMakes = database.getAllSwitchMakes();
                JComboBox make = new JComboBox(allMakes);
                for (int i = 0; i < allMakes.length; i++) {
                    if (allMakes[i].equals(oldMake)){
                        make.setSelectedIndex(i);
                    }
                }

                String[] allModels = database.getAllSwitchModelsByMake(oldMake);
                JComboBox model = new JComboBox(allModels);
                for (int i = 0; i < allModels.length; i++) {
                    if (allModels[i].equals(oldModel)){
                        model.setSelectedIndex(i);
                    }
                }

                JTextField serialNumber = new JTextField(oldSerialNumber,10);
                JTextField softwareVersion = new JTextField(oldSoftwareVersion,10);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.FIRST_LINE_START;
                gbc.weightx = 0.0;
                gbc.weighty = 0.0;
                gbc.insets = new Insets(5,5,5,5);

                mySwitchTablePopupMenuUpdatePanel.add(new JLabel("Konum: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuUpdatePanel.add(place, gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuUpdatePanel.add(new JLabel("Lokasyon: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuUpdatePanel.add(location, gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuUpdatePanel.add(new JLabel("Yer: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuUpdatePanel.add(floor, gbc);

                gbc.gridx = 0;
                gbc.gridy++;
                mySwitchTablePopupMenuUpdatePanel.add(new JLabel("İsim: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuUpdatePanel.add(name, gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuUpdatePanel.add(new JLabel("IP: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuUpdatePanel.add(IP, gbc);

                gbc.gridx = 0;
                gbc.gridy++;
                mySwitchTablePopupMenuUpdatePanel.add(new JLabel("Marka: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuUpdatePanel.add(make, gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuUpdatePanel.add(new JLabel("Model: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuUpdatePanel.add(model, gbc);

                gbc.gridx = 0;
                gbc.gridy++;
                mySwitchTablePopupMenuUpdatePanel.add(new JLabel("Seri NO: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuUpdatePanel.add(serialNumber, gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuUpdatePanel.add(new JLabel("Yazılım Versiyonu: "), gbc);

                gbc.gridx++;
                mySwitchTablePopupMenuUpdatePanel.add(softwareVersion, gbc);

                int result = JOptionPane.showConfirmDialog(new JFrame(), mySwitchTablePopupMenuUpdatePanel, "Güncellemek istediğiniz değerleri değiştiriniz.", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION){
                    database.updateSwitch(oldPlace, oldLocation, oldFloor, oldName, oldIP, oldMake, oldModel, oldSerialNumber, oldSoftwareVersion,
                                          place.getSelectedItem().toString(), location.getText(), floor.getText(), name.getText(), IP.getText(), make.getSelectedItem().toString(), model.getSelectedItem().toString(), serialNumber.getText(), softwareVersion.getText());
                    refreshTable();
                }
            }
        }
        else if (actionEvent.getSource() == mySwitchTablePopupMenuDetails){
            if (getSelectedRowCount() == 1){
                JPanel mySwitchTablePopupMenuDetailsPanel = new JPanel(new GridBagLayout());

                int row = getSelectedRow();

                String place = (String) getModel().getValueAt(row, 1);
                String location = (String) getModel().getValueAt(row, 2);
                String floor = (String) getModel().getValueAt(row, 3);
                String name = (String) getModel().getValueAt(row, 4);
                String IP = (String) getModel().getValueAt(row, 5);
                String make = (String) getModel().getValueAt(row, 6);
                String model = (String) getModel().getValueAt(row, 7);
                String serialNumber = (String) getModel().getValueAt(row, 8);
                String softwareVersion = (String) getModel().getValueAt(row, 9);

                ArrayList<MySwitch> switches = database.searchSwitches(place, location, floor, name, IP, make, model, serialNumber, softwareVersion);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 0.0;
                gbc.weighty = 0.0;
                gbc.insets = new Insets(5,5,5,5);
                gbc.anchor = GridBagConstraints.FIRST_LINE_START;
                for (MySwitch aSwitch : switches){
                    JTextArea textArea = new JTextArea(aSwitch.toString());
                    textArea.setEditable(false);
                    mySwitchTablePopupMenuDetailsPanel.add(new JTextArea(aSwitch.toString()), gbc);
                    gbc.weighty++;
                }
                JOptionPane.showMessageDialog(new JFrame(), mySwitchTablePopupMenuDetailsPanel, "Detaylar", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void refreshTable(){
        setModel(new MySwitchTableModel(database));

        getColumnModel().getColumn(0).setPreferredWidth(20);
        getColumnModel().getColumn(3).setPreferredWidth(50);
        getColumnModel().getColumn(4).setPreferredWidth(150);
        getColumnModel().getColumn(5).setPreferredWidth(100);
        getColumnModel().getColumn(6).setPreferredWidth(50);
        getColumnModel().getColumn(7).setPreferredWidth(100);

        setPreferredScrollableViewportSize(new Dimension(900, 200));
        setFillsViewportHeight(true);
    }

    static class MySwitchTableModel extends AbstractTableModel {
        private String[] columnNames = {"Sıra", "Konum", "Lokasyon", "Yer", "İsim", "IP", "Marka", "Model", "Seri No", "Yazılım Versiyonu", "Switch Stack"};
        private String[][] data;

        public MySwitchTableModel(MyXML_DAO database){
            MySwitchTable.database = database;
            ArrayList<MySwitch> switches = database.getAllSwitches();
            this.data = new String[switches.size()][11];

            for (int i = 0; i < switches.size(); i++) {
                MySwitch currentSwitch = switches.get(i);
                data[i][0] = Integer.toString(i + 1);
                data[i][1] = currentSwitch.getPlace();
                data[i][2] = currentSwitch.getLocation();
                data[i][3] = currentSwitch.getFloor();
                data[i][4] = currentSwitch.getName();
                data[i][5] = currentSwitch.getIP();
                data[i][6] = currentSwitch.getMake();
                data[i][7] = currentSwitch.getModel();
                data[i][8] = currentSwitch.getSerialNumber();
                data[i][9] = currentSwitch.getSoftwareVersion();
                data[i][10] = Integer.toString(database.searchSwitches("","","",currentSwitch.getName(),"","","","","").size());
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
