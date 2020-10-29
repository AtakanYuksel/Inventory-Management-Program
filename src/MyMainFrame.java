import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyMainFrame extends JFrame implements ActionListener {
    JFrame mainFrame;
    private JTabbedPane cardTabbedPane;

    //
    private JPanel switchCardPanel;

    private JPanel switchButtonsPanel;
    private JButton switchAddButton;
    private JButton switchRemoveButton;
    private JButton placeAddButton;
    private JButton placeRemoveButton;
    private JButton switchMakeAddButton;
    private JButton switchMakeRemoveButton;
    private JButton switchModelAddButton;
    private JButton switchModelRemoveButton;

    private JPanel switchTablePanel;
    private MySwitchTable switchTable;
    //

    //
    private JPanel moduleCardPanel;

    private JPanel moduleButtonsPanel;
    private JButton moduleAddButton;
    private JButton moduleRemoveButton;
    private JButton moduleAddModelButton;
    private JButton moduleRemoveModelButton;

    private JPanel moduleTablePanel;
    private MyModuleTable moduleTable;
    //

    //
    private JPanel sfpCardPanel;

    private JPanel sfpButtonsPanel;
    private JButton sfpAddButton;
    private JButton sfpRemoveButton;
    private JButton sfpAddModelButton;
    private JButton sfpRemoveModelButton;

    private JPanel sfpTablePanel;
    private MySFPTable sfpTable;
    //

    //
    private JPanel firewallCardPanel;

    private JPanel firewallButtonsPanel;
    private JButton firewallAddButton;
    private JButton firewallRemoveButton;

    private JPanel firewallTablePanel;
    private MyFirewallTable firewallTable;
    //


    private String path;
    private MyXML_DAO database;

    public MyMainFrame() {
        // code for choosing a file using explorer
//        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
//        fileChooser.setAcceptAllFileFilterUsed(false);
//        fileChooser.setDialogTitle(".xml dosyası seçin.");
//        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Sadece .xml dosyaları", "xml");
//        fileChooser.addChoosableFileFilter(restrict);
//        int i = fileChooser.showOpenDialog(this);
//        if (i == JFileChooser.APPROVE_OPTION){
//            File file = fileChooser.getSelectedFile();
//            this.path = file.getAbsolutePath();
//        } else {
//            JOptionPane.showMessageDialog(null, "Dosya seçmediniz.\nDosya seçilmeden program çalışamaz.", "HATA !", JOptionPane.ERROR_MESSAGE);
//            System.exit(-1);
//        }
        //
        
        // remove this for dynamic file names
        this.path = "switch_envanter.xml";

        this.database = new MyXMLDAO_Operations(path);

        this.mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.cardTabbedPane = new JTabbedPane() {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 165;
                return size;
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = -1;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(5, 5, 5, 5);

        switchCardPanel = new JPanel(new GridBagLayout());
        switchButtonsPanel = new JPanel(new GridBagLayout());
        switchTablePanel = new JPanel();

        gbc.gridx++;
        gbc.ipady = 10;
        this.switchButtonsPanel.add(new JLabel("Switch -->"), gbc);
        gbc.ipady = 0;

        gbc.gridx++;
        switchAddButton = new JButton("Ekle...");
        switchAddButton.addActionListener(this);
        this.switchButtonsPanel.add(switchAddButton, gbc);

        gbc.gridx++;
        switchRemoveButton = new JButton("Sil...");
        switchRemoveButton.addActionListener(this);
        this.switchButtonsPanel.add(switchRemoveButton, gbc);

        gbc.gridx++;
        gbc.ipady = 10;
        this.switchButtonsPanel.add(new JLabel("Konum -->"), gbc);
        gbc.ipady = 0;

        gbc.gridx++;
        placeAddButton = new JButton("Ekle...");
        placeAddButton.addActionListener(this);
        this.switchButtonsPanel.add(placeAddButton, gbc);

        gbc.gridx++;
        placeRemoveButton = new JButton("Sil...");
        placeRemoveButton.addActionListener(this);
        this.switchButtonsPanel.add(placeRemoveButton, gbc);


        gbc.gridx = 0;
        gbc.gridy++;
        gbc.ipady = 10;
        this.switchButtonsPanel.add(new JLabel("Marka -->"), gbc);
        gbc.ipady = 0;

        gbc.gridx++;
        switchMakeAddButton = new JButton("Ekle...");
        switchMakeAddButton.addActionListener(this);
        this.switchButtonsPanel.add(switchMakeAddButton, gbc);

        gbc.gridx++;
        switchMakeRemoveButton = new JButton("Sil...");
        switchMakeRemoveButton.addActionListener(this);
        this.switchButtonsPanel.add(switchMakeRemoveButton, gbc);


        gbc.gridx++;
        gbc.ipady = 10;
        this.switchButtonsPanel.add(new JLabel("Model -->"), gbc);
        gbc.ipady = 0;

        gbc.gridx++;
        switchModelAddButton = new JButton("Ekle...");
        switchModelAddButton.addActionListener(this);
        this.switchButtonsPanel.add(switchModelAddButton, gbc);

        gbc.gridx++;
        switchModelRemoveButton = new JButton("Sil...");
        switchModelRemoveButton.addActionListener(this);
        this.switchButtonsPanel.add(switchModelRemoveButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,0);

        switchTable = new MySwitchTable(database);
        JScrollPane switchTableScrollPane = new JScrollPane(switchTable);
        switchTablePanel.add(switchTableScrollPane);

        switchCardPanel.add(switchButtonsPanel, gbc);
        gbc.gridy++;
        switchCardPanel.add(switchTablePanel, gbc);

        cardTabbedPane.add("Switch", switchCardPanel);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.PAGE_START;

        moduleCardPanel = new JPanel(new GridBagLayout());
        moduleButtonsPanel = new JPanel(new GridBagLayout());
        moduleTablePanel = new JPanel();

        gbc.ipady = 10;
        moduleButtonsPanel.add(new JLabel("Modül -->"), gbc);
        gbc.ipady = 0;

        gbc.gridx++;
        moduleAddButton = new JButton("Ekle...");
        moduleAddButton.addActionListener(this);
        moduleButtonsPanel.add(moduleAddButton, gbc);

        gbc.gridx++;
        moduleRemoveButton = new JButton("Sil...");
        moduleRemoveButton.addActionListener(this);
        moduleButtonsPanel.add(moduleRemoveButton, gbc);

        gbc.gridx++;
        gbc.ipady = 10;
        moduleButtonsPanel.add(new JLabel("Model -->"), gbc);
        gbc.ipady = 0;

        gbc.gridx++;
        moduleAddModelButton = new JButton("Ekle...");
        moduleAddModelButton.addActionListener(this);
        moduleButtonsPanel.add(moduleAddModelButton, gbc);

        gbc.gridx++;
        moduleRemoveModelButton = new JButton("Sil...");
        moduleRemoveModelButton.addActionListener(this);
        moduleButtonsPanel.add(moduleRemoveModelButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;

        moduleCardPanel.add(moduleButtonsPanel, gbc);

        moduleTable = new MyModuleTable(database);
        JScrollPane moduleTableScrollPane = new JScrollPane(moduleTable);
        moduleTablePanel.add(moduleTableScrollPane);
        gbc.gridy++;
        moduleCardPanel.add(moduleTablePanel, gbc);

        cardTabbedPane.add("Modül", moduleCardPanel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        sfpCardPanel = new JPanel(new GridBagLayout());
        sfpButtonsPanel = new JPanel(new GridBagLayout());
        sfpTablePanel = new JPanel();

        gbc.ipady = 10;
        sfpButtonsPanel.add(new JLabel("SFP'ler -->"), gbc);
        gbc.ipady = 0;

        gbc.gridx++;
        sfpAddButton = new JButton("Ekle...");
        sfpAddButton.addActionListener(this);
        sfpButtonsPanel.add(sfpAddButton, gbc);

        gbc.gridx++;
        sfpRemoveButton = new JButton("Sil...");
        sfpRemoveButton.addActionListener(this);
        sfpButtonsPanel.add(sfpRemoveButton, gbc);

        gbc.gridx++;
        gbc.ipady = 10;
        sfpButtonsPanel.add(new JLabel("Model -->"), gbc);
        gbc.ipady = 0;

        gbc.gridx++;
        sfpAddModelButton = new JButton("Ekle...");
        sfpAddModelButton.addActionListener(this);
        sfpButtonsPanel.add(sfpAddModelButton, gbc);

        gbc.gridx++;
        sfpRemoveModelButton = new JButton("Sil...");
        sfpRemoveModelButton.addActionListener(this);
        sfpButtonsPanel.add(sfpRemoveModelButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;

        sfpCardPanel.add(sfpButtonsPanel, gbc);

        gbc.gridy++;
        sfpTable = new MySFPTable(database);
        JScrollPane sfpTableScrollPane = new JScrollPane(sfpTable);
        sfpTablePanel.add(sfpTableScrollPane);
        sfpCardPanel.add(sfpTablePanel);

        sfpCardPanel.add(sfpTablePanel, gbc);

        cardTabbedPane.add("SFP", sfpCardPanel);

        firewallCardPanel = new JPanel(new GridBagLayout());
        firewallButtonsPanel = new JPanel(new GridBagLayout());
        firewallTablePanel = new JPanel();

        gbc.ipady = 10;
        firewallButtonsPanel.add(new JLabel("Firewall -->"), gbc);
        gbc.ipady = 0;

        gbc.gridx++;
        firewallAddButton = new JButton("Ekle...");
        firewallAddButton.addActionListener(this);
        firewallButtonsPanel.add(firewallAddButton, gbc);

        gbc.gridx++;
        firewallRemoveButton = new JButton("Sil...");
        firewallRemoveButton.addActionListener(this);
        firewallButtonsPanel.add(firewallRemoveButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        firewallCardPanel.add(firewallButtonsPanel, gbc);

        gbc.gridy++;
        firewallTable = new MyFirewallTable(database);
        JScrollPane firewallTableScrollPane = new JScrollPane(firewallTable);
        firewallTablePanel.add(firewallTableScrollPane);
        firewallCardPanel.add(firewallTablePanel, gbc);

        cardTabbedPane.add("Firewall", firewallCardPanel);

        mainFrame.add(cardTabbedPane, BorderLayout.PAGE_START);

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == switchAddButton){
            JPanel panelSwitchAdd = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            JComboBox place = new JComboBox(database.getAllPlaces());
            JTextField location = new JTextField(15);
            JTextField floor = new JTextField(15);
            JTextField name = new JTextField(15);
            JTextField IP = new JTextField(15);

            JComboBox make = new JComboBox(database.getAllSwitchMakes());
            make.insertItemAt("",0);
            make.setSelectedIndex(0);

            final JComboBox[] model = new JComboBox[1];

            make.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    make.removeItem("");
                    try {
                        panelSwitchAdd.remove(model[0]);
                        panelSwitchAdd.revalidate();
                        panelSwitchAdd.repaint();
                    } catch (Exception ignored){

                    }
                    model[0] = new JComboBox(database.getAllSwitchModelsByMake(make.getSelectedItem().toString()));
                    model[0].insertItemAt("", 0);
                    model[0].setSelectedIndex(0);

                    model[0].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            model[0].removeItem("");
                        }
                    });
                    gbc.gridy = 3;
                    gbc.gridx = 3;
                    panelSwitchAdd.add(model[0], gbc);
                    panelSwitchAdd.revalidate();
                    panelSwitchAdd.repaint();
                }
            });
            JTextField serialNumber = new JTextField(15);
            JTextField softwareVersion = new JTextField(15);



            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.ipady = 10;
            gbc.insets = new Insets(5,5,5,0);
            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;

            panelSwitchAdd.add(new JLabel("Konum:"), gbc);
            gbc.gridx = 1;
            panelSwitchAdd.add(place, gbc);

            gbc.gridx = 2;
            panelSwitchAdd.add(new JLabel("Lokasyon:"), gbc);
            gbc.gridx = 3;
            panelSwitchAdd.add(location, gbc);

            gbc.gridx = 4;
            panelSwitchAdd.add(new JLabel("Yer:"), gbc);
            gbc.gridx = 5;
            panelSwitchAdd.add(floor, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            panelSwitchAdd.add(new JLabel("İsim:"), gbc);
            gbc.gridx = 1;
            panelSwitchAdd.add(name, gbc);

            gbc.gridx = 2;
            panelSwitchAdd.add(new JLabel("IP:"), gbc);
            gbc.gridx = 3;
            panelSwitchAdd.add(IP, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            panelSwitchAdd.add(new JLabel("Marka:"), gbc);
            gbc.gridx = 1;
            panelSwitchAdd.add(make, gbc);

            gbc.gridx = 2;
            panelSwitchAdd.add(new JLabel("Model:"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            panelSwitchAdd.add(new JLabel("Seri NO:"), gbc);
            gbc.gridx = 1;
            panelSwitchAdd.add(serialNumber, gbc);

            gbc.gridx = 2;
            panelSwitchAdd.add(new JLabel("Yazılım Versiyonu:"), gbc);
            gbc.gridx = 3;
            panelSwitchAdd.add(softwareVersion, gbc);

            int result = JOptionPane.showConfirmDialog(mainFrame, panelSwitchAdd, "Değerleri giriniz", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION){
                database.addSwitch(place.getSelectedItem().toString(), location.getText(), floor.getText(), name.getText(), IP.getText(), make.getSelectedItem().toString(), model[0].getSelectedItem().toString(), serialNumber.getText(), softwareVersion.getText());
                switchTable.refreshTable();
            }
        }
        else if (actionEvent.getSource() == switchRemoveButton){
            JPanel panelSwitchRemove = new JPanel(new GridBagLayout());

            JComboBox place = new JComboBox(database.getAllPlaces());
            JTextField location = new JTextField(10);
            JTextField floor = new JTextField(10);
            JTextField name = new JTextField(10);
            JTextField IP = new JTextField(10);
            JComboBox make = new JComboBox(database.getAllSwitchMakes());
            final JComboBox[] model = new JComboBox[1];
            make.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    model[0] = new JComboBox(database.getAllSwitchModelsByMake(make.getSelectedItem().toString()));
                }
            });
            JTextField serialNumber = new JTextField(10);
            JTextField softwareVersion = new JTextField(10);


            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.ipady = 10;
            gbc.insets = new Insets(5,5,5,0);
            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;

            panelSwitchRemove.add(new JLabel("Konum:"), gbc);
            gbc.gridx = 1;
            panelSwitchRemove.add(place, gbc);

            gbc.gridx = 2;
            panelSwitchRemove.add(new JLabel("Lokasyon:"), gbc);
            gbc.gridx = 3;
            panelSwitchRemove.add(location, gbc);

            gbc.gridx = 4;
            panelSwitchRemove.add(new JLabel("Yer:"), gbc);
            gbc.gridx = 5;
            panelSwitchRemove.add(floor, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            panelSwitchRemove.add(new JLabel("İsim:"), gbc);
            gbc.gridx = 1;
            panelSwitchRemove.add(name, gbc);

            gbc.gridx = 2;
            panelSwitchRemove.add(new JLabel("IP:"), gbc);
            gbc.gridx = 3;
            panelSwitchRemove.add(IP, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            panelSwitchRemove.add(new JLabel("Marka:"), gbc);
            gbc.gridx = 1;
            panelSwitchRemove.add(make, gbc);

            gbc.gridx = 2;
            panelSwitchRemove.add(new JLabel("Model:"), gbc);
            gbc.gridx = 3;
            panelSwitchRemove.add(model[0], gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            panelSwitchRemove.add(new JLabel("Seri NO:"), gbc);
            gbc.gridx = 1;
            panelSwitchRemove.add(serialNumber, gbc);

            gbc.gridx = 2;
            panelSwitchRemove.add(new JLabel("Yazılım Versiyonu:"), gbc);
            gbc.gridx = 3;
            panelSwitchRemove.add(softwareVersion, gbc);

            int result = JOptionPane.showConfirmDialog(mainFrame, panelSwitchRemove, "Değerleri giriniz", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION){
                database.removeSwitch(place.getSelectedItem().toString(), location.getText(), floor.getText(), name.getText(), IP.getText(), make.getSelectedItem().toString(), model[0].getSelectedItem().toString(), serialNumber.getText(), softwareVersion.getText());
                switchTable.refreshTable();
            }
        }
        else if (actionEvent.getSource() == placeAddButton){
            JPanel panelPlaceAdd = new JPanel(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;
            gbc.anchor = GridBagConstraints.PAGE_START;

            panelPlaceAdd.add(new JLabel("Konum:     "), gbc);

            gbc.gridx++;
            JTextField make = new JTextField(10);
            panelPlaceAdd.add(make, gbc);

            int result = JOptionPane.showConfirmDialog(mainFrame, panelPlaceAdd, "Konum Giriniz", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION){
                database.addPlace(make.getText());
            }
        }
        else if (actionEvent.getSource() == placeRemoveButton){
            JPanel panelPlaceRemove = new JPanel(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;
            gbc.anchor = GridBagConstraints.PAGE_START;

            gbc.ipady = 9;
            panelPlaceRemove.add(new JLabel("Konum:     "), gbc);
            gbc.ipady = 0;

            gbc.gridx++;
            JComboBox make = new JComboBox(database.getAllPlaces());
            panelPlaceRemove.add(make, gbc);

            int result = JOptionPane.showConfirmDialog(mainFrame, panelPlaceRemove, "Silinecek Konumu Seçiniz", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION){
                database.removePlace(make.getSelectedItem().toString());
            }

        }
        else if (actionEvent.getSource() == switchMakeAddButton){
            JPanel panelMakeAdd = new JPanel(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;
            gbc.anchor = GridBagConstraints.PAGE_START;

            panelMakeAdd.add(new JLabel("Marka:     "), gbc);

            gbc.gridx++;
            JTextField make = new JTextField(10);
            panelMakeAdd.add(make, gbc);

            int result = JOptionPane.showConfirmDialog(mainFrame, panelMakeAdd, "Marka Adı Giriniz", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION){
                database.addSwitchMake(make.getText());
            }
        }
        else if (actionEvent.getSource() == switchMakeRemoveButton){
            JPanel panelMakeRemove = new JPanel(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;
            gbc.anchor = GridBagConstraints.PAGE_START;

            gbc.ipady = 9;
            panelMakeRemove.add(new JLabel("Marka:     "), gbc);
            gbc.ipady = 0;

            gbc.gridx++;
            JComboBox make = new JComboBox(database.getAllSwitchMakes());
            panelMakeRemove.add(make, gbc);

            int result = JOptionPane.showConfirmDialog(mainFrame, panelMakeRemove, "Silinecek Markayı Seçiniz", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION){
                database.removeSwitchMake(make.getSelectedItem().toString());
            }
        }
        else if (actionEvent.getSource() == switchModelAddButton){
            JPanel panelModelAdd = new JPanel(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;
            gbc.anchor = GridBagConstraints.PAGE_START;

            panelModelAdd.add(new JLabel("Marka:     "), gbc);

            gbc.gridx++;
            JComboBox make = new JComboBox(database.getAllSwitchMakes());
            panelModelAdd.add(make, gbc);

            gbc.gridx++;
            panelModelAdd.add(new JLabel("Model:     "), gbc);

            gbc.gridx++;
            JTextField model = new JTextField(10);
            panelModelAdd.add(model, gbc);

            int result = JOptionPane.showConfirmDialog(mainFrame, panelModelAdd, "Model Adı Giriniz", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION){
                database.addSwitchModel(make.getSelectedItem().toString() ,model.getText());
            }

        }
        else if (actionEvent.getSource() == switchModelRemoveButton){
            JPanel panelModelRemove = new JPanel(new GridBagLayout());
            final JComboBox[] model = new JComboBox[1];

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;
            gbc.anchor = GridBagConstraints.PAGE_START;

            panelModelRemove.add(new JLabel("Marka:     "), gbc);

            gbc.gridx = 1;
            JComboBox make = new JComboBox(database.getAllSwitchMakes());
            make.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gbc.gridx = 3;
                    model[0] = new JComboBox(database.getAllSwitchModelsByMake(make.getSelectedItem().toString()));
                }
            });
            panelModelRemove.add(make, gbc);

            gbc.gridx = 2;
            panelModelRemove.add(new JLabel("Model:     "), gbc);


            int result = JOptionPane.showConfirmDialog(mainFrame, panelModelRemove, "Silinecek Modeli Seçiniz", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION){
                database.removeSwitchModel(make.getSelectedItem().toString() ,model[0].getSelectedItem().toString());
            }

        }
//        else if (actionEvent.getSource() == moduleAddButton){
//            JPanel panelModuleAdd = new JPanel(new GridBagLayout());
//
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.gridx = 0;
//            gbc.gridy = 0;
//            gbc.weightx = 0.0;
//            gbc.weighty = 0.0;
//            gbc.anchor = GridBagConstraints.PAGE_START;
//            gbc.insets = new Insets(5,5,5,5);
//
//            panelModuleAdd.add(new JLabel("Lokasyon:"), gbc);
//
//            gbc.gridx++;
//            JTextField location = new JTextField(10);
//            panelModuleAdd.add(location, gbc);
//
//            gbc.gridx++;
//            panelModuleAdd.add(new JLabel("Model:"), gbc);
//
//            gbc.gridx++;
//            JComboBox model = new JComboBox(database.getAllModuleModels());
//            panelModuleAdd.add(model, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy++;
//            panelModuleAdd.add(new JLabel("Seri NO:"), gbc);
//
//            gbc.gridx++;
//            JTextField serialNumber = new JTextField(10);
//            panelModuleAdd.add(serialNumber, gbc);
//
//            gbc.gridx++;
//            panelModuleAdd.add(new JLabel("IP:"), gbc);
//
//            gbc.gridx++;
//            JTextField IP = new JTextField(10);
//            panelModuleAdd.add(IP, gbc);
//
//            int result = JOptionPane.showConfirmDialog(mainFrame, panelModuleAdd, "Modül Giriniz", JOptionPane.OK_CANCEL_OPTION);
//            if (result == JOptionPane.OK_OPTION){
//                database.addModule(location.getText(), model.getSelectedItem().toString(), serialNumber.getText(), IP.getText());
//                moduleTable.refreshTable();
//            }
//        }
//        else if (actionEvent.getSource() == moduleRemoveButton){
//            JPanel panelModuleRemove = new JPanel(new GridBagLayout());
//
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.gridx = 0;
//            gbc.gridy = 0;
//            gbc.weightx = 0.0;
//            gbc.weighty = 0.0;
//            gbc.anchor = GridBagConstraints.PAGE_START;
//            gbc.insets = new Insets(5,5,5,5);
//
//            panelModuleRemove.add(new JLabel("Lokasyon:"), gbc);
//
//            gbc.gridx++;
//            JTextField location = new JTextField(10);
//            panelModuleRemove.add(location, gbc);
//
//            gbc.gridx++;
//            panelModuleRemove.add(new JLabel("Model:"), gbc);
//
//            gbc.gridx++;
//            JComboBox model = new JComboBox(database.getAllModuleModels());
//            panelModuleRemove.add(model, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy++;
//            panelModuleRemove.add(new JLabel("Seri NO:"), gbc);
//
//            gbc.gridx++;
//            JTextField serialNumber = new JTextField(10);
//            panelModuleRemove.add(serialNumber, gbc);
//
//            gbc.gridx++;
//            panelModuleRemove.add(new JLabel("IP:"), gbc);
//
//            gbc.gridx++;
//            JTextField IP = new JTextField(10);
//            panelModuleRemove.add(IP, gbc);
//
//            int result = JOptionPane.showConfirmDialog(mainFrame, panelModuleRemove, "Modül Giriniz", JOptionPane.OK_CANCEL_OPTION);
//            if (result == JOptionPane.OK_OPTION){
//                database.removeModule(location.getText(), model.getSelectedItem().toString(), serialNumber.getText(), IP.getText());
//                moduleTable.refreshTable();
//            }
//        }
//        else if (actionEvent.getSource() == moduleAddModelButton){
//            JPanel panelModuleAddModelPanel = new JPanel(new GridBagLayout());
//
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.gridx = 0;
//            gbc.gridy = 0;
//            gbc.weightx = 0.0;
//            gbc.weighty = 0.0;
//            gbc.anchor = GridBagConstraints.PAGE_START;
//
//            panelModuleAddModelPanel.add(new JLabel("Model:     "), gbc);
//
//            gbc.gridx++;
//            JTextField model = new JTextField(10);
//            panelModuleAddModelPanel.add(model, gbc);
//
//            int result = JOptionPane.showConfirmDialog(mainFrame, panelModuleAddModelPanel, "Modül Modeli Giriniz", JOptionPane.OK_CANCEL_OPTION);
//            if (result == JOptionPane.OK_OPTION){
//                database.addModuleModel(model.getText());
//            }
//        }
//        else if (actionEvent.getSource() == moduleRemoveModelButton){
//            JPanel panelModuleRemoveModel = new JPanel(new GridBagLayout());
//
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.gridx = 0;
//            gbc.gridy = 0;
//            gbc.weightx = 0.0;
//            gbc.weighty = 0.0;
//            gbc.anchor = GridBagConstraints.PAGE_START;
//
//            gbc.ipady = 9;
//            panelModuleRemoveModel.add(new JLabel("Model:     "), gbc);
//            gbc.ipady = 0;
//
//            gbc.gridx++;
//            JComboBox model = new JComboBox(database.getAllModuleModels());
//            panelModuleRemoveModel.add(model, gbc);
//
//            int result = JOptionPane.showConfirmDialog(mainFrame, panelModuleRemoveModel, "Silinecek Modeli Seçiniz", JOptionPane.OK_CANCEL_OPTION);
//            if (result == JOptionPane.OK_OPTION){
//                database.removeModuleModel(model.getSelectedItem().toString());
//            }
//        }
//        else if (actionEvent.getSource() == sfpAddButton){
//            JPanel panelSFPAdd = new JPanel(new GridBagLayout());
//
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.gridx = 0;
//            gbc.gridy = 0;
//            gbc.weightx = 0.0;
//            gbc.weighty = 0.0;
//            gbc.anchor = GridBagConstraints.PAGE_START;
//            gbc.insets = new Insets(5,5,5,5);
//
//            panelSFPAdd.add(new JLabel("Lokasyon:"), gbc);
//
//            gbc.gridx++;
//            JTextField location = new JTextField(10);
//            panelSFPAdd.add(location, gbc);
//
//            gbc.gridx++;
//            panelSFPAdd.add(new JLabel("Model:"), gbc);
//
//            gbc.gridx++;
//            JComboBox model = new JComboBox(database.getAllSFPModels());
//            panelSFPAdd.add(model, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy++;
//            panelSFPAdd.add(new JLabel("Seri NO:"), gbc);
//
//            gbc.gridx++;
//            JTextField serialNumber = new JTextField(10);
//            panelSFPAdd.add(serialNumber, gbc);
//
//            gbc.gridx++;
//            panelSFPAdd.add(new JLabel("IP:"), gbc);
//
//            gbc.gridx++;
//            JTextField IP = new JTextField(10);
//            panelSFPAdd.add(IP, gbc);
//
//            int result = JOptionPane.showConfirmDialog(mainFrame, panelSFPAdd, "SFP Giriniz", JOptionPane.OK_CANCEL_OPTION);
//            if (result == JOptionPane.OK_OPTION){
//                database.addSFP(location.getText(), model.getSelectedItem().toString(), serialNumber.getText(), IP.getText());
//                sfpTable.refreshTable();
//            }
//        }
//        else if (actionEvent.getSource() == sfpRemoveButton){
//            JPanel panelSFPRemove = new JPanel(new GridBagLayout());
//
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.gridx = 0;
//            gbc.gridy = 0;
//            gbc.weightx = 0.0;
//            gbc.weighty = 0.0;
//            gbc.anchor = GridBagConstraints.PAGE_START;
//            gbc.insets = new Insets(5,5,5,5);
//
//            panelSFPRemove.add(new JLabel("Lokasyon:"), gbc);
//
//            gbc.gridx++;
//            JTextField location = new JTextField(10);
//            panelSFPRemove.add(location, gbc);
//
//            gbc.gridx++;
//            panelSFPRemove.add(new JLabel("Model:"), gbc);
//
//            gbc.gridx++;
//            JComboBox model = new JComboBox(database.getAllSFPModels());
//            panelSFPRemove.add(model, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy++;
//            panelSFPRemove.add(new JLabel("Seri NO:"), gbc);
//
//            gbc.gridx++;
//            JTextField serialNumber = new JTextField(10);
//            panelSFPRemove.add(serialNumber, gbc);
//
//            gbc.gridx++;
//            panelSFPRemove.add(new JLabel("IP:"), gbc);
//
//            gbc.gridx++;
//            JTextField IP = new JTextField(10);
//            panelSFPRemove.add(IP, gbc);
//
//            int result = JOptionPane.showConfirmDialog(mainFrame, panelSFPRemove, "SFP Giriniz", JOptionPane.OK_CANCEL_OPTION);
//            if (result == JOptionPane.OK_OPTION){
//                database.removeSFP(location.getText(), model.getSelectedItem().toString(), serialNumber.getText(), IP.getText());
//                sfpTable.refreshTable();
//            }
//        }
//        else if (actionEvent.getSource() == sfpAddModelButton){
//            JPanel panelSFPAddModelPanel = new JPanel(new GridBagLayout());
//
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.gridx = 0;
//            gbc.gridy = 0;
//            gbc.weightx = 0.0;
//            gbc.weighty = 0.0;
//            gbc.anchor = GridBagConstraints.PAGE_START;
//
//            panelSFPAddModelPanel.add(new JLabel("Model:     "), gbc);
//
//            gbc.gridx++;
//            JTextField model = new JTextField(10);
//            panelSFPAddModelPanel.add(model, gbc);
//
//            int result = JOptionPane.showConfirmDialog(mainFrame, panelSFPAddModelPanel, "SFP Modeli Giriniz", JOptionPane.OK_CANCEL_OPTION);
//            if (result == JOptionPane.OK_OPTION){
//                database.addSFPModel(model.getText());
//            }
//        }
//        else if (actionEvent.getSource() == sfpRemoveModelButton){
//            JPanel panelSFPRemoveModel = new JPanel(new GridBagLayout());
//
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.gridx = 0;
//            gbc.gridy = 0;
//            gbc.weightx = 0.0;
//            gbc.weighty = 0.0;
//            gbc.anchor = GridBagConstraints.PAGE_START;
//
//            gbc.ipady = 9;
//            panelSFPRemoveModel.add(new JLabel("Model:     "), gbc);
//            gbc.ipady = 0;
//
//            gbc.gridx++;
//            JComboBox model = new JComboBox(database.getAllSFPModels());
//            panelSFPRemoveModel.add(model, gbc);
//
//            int result = JOptionPane.showConfirmDialog(mainFrame, panelSFPRemoveModel, "Silinecek Modeli Seçiniz", JOptionPane.OK_CANCEL_OPTION);
//            if (result == JOptionPane.OK_OPTION){
//                database.removeSFPModel(model.getSelectedItem().toString());
//            }
//        }
//        else if (actionEvent.getSource() == firewallAddButton){
//            JPanel panelFirewallAdd = new JPanel(new GridBagLayout());
//
//            JComboBox place = new JComboBox(database.getAllPlaces());
//            JTextField location = new JTextField(10);
//            JTextField floor = new JTextField(10);
//            JTextField name = new JTextField(10);
//            JTextField IP = new JTextField(10);
//            JComboBox make = new JComboBox(database.getAllMakes());
//            JComboBox model = new JComboBox(database.getAllSwitchModels());
//            JTextField serialNumber = new JTextField(10);
//            JTextField softwareVersion = new JTextField(10);
//
//
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.gridx = 0;
//            gbc.gridy = 1;
//            gbc.ipady = 10;
//            gbc.insets = new Insets(5,5,5,0);
//            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
//            gbc.weightx = 0.0;
//            gbc.weighty = 0.0;
//
//            panelFirewallAdd.add(new JLabel("Konum:"), gbc);
//            gbc.gridx = 1;
//            panelFirewallAdd.add(place, gbc);
//
//            gbc.gridx = 2;
//            panelFirewallAdd.add(new JLabel("Lokasyon:"), gbc);
//            gbc.gridx = 3;
//            panelFirewallAdd.add(location, gbc);
//
//            gbc.gridx = 4;
//            panelFirewallAdd.add(new JLabel("Yer:"), gbc);
//            gbc.gridx = 5;
//            panelFirewallAdd.add(floor, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy = 2;
//            panelFirewallAdd.add(new JLabel("İsim:"), gbc);
//            gbc.gridx = 1;
//            panelFirewallAdd.add(name, gbc);
//
//            gbc.gridx = 2;
//            panelFirewallAdd.add(new JLabel("IP:"), gbc);
//            gbc.gridx = 3;
//            panelFirewallAdd.add(IP, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy = 3;
//            panelFirewallAdd.add(new JLabel("Marka:"), gbc);
//            gbc.gridx = 1;
//            panelFirewallAdd.add(make, gbc);
//
//            gbc.gridx = 2;
//            panelFirewallAdd.add(new JLabel("Model:"), gbc);
//            gbc.gridx = 3;
//            panelFirewallAdd.add(model, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy = 4;
//            panelFirewallAdd.add(new JLabel("Seri NO:"), gbc);
//            gbc.gridx = 1;
//            panelFirewallAdd.add(serialNumber, gbc);
//
//            gbc.gridx = 2;
//            panelFirewallAdd.add(new JLabel("Yazılım Versiyonu:"), gbc);
//            gbc.gridx = 3;
//            panelFirewallAdd.add(softwareVersion, gbc);
//
//            int result = JOptionPane.showConfirmDialog(mainFrame, panelFirewallAdd, "Değerleri giriniz", JOptionPane.OK_CANCEL_OPTION);
//            if (result == JOptionPane.OK_OPTION){
//                database.addFirewall(place.getSelectedItem().toString(), location.getText(), floor.getText(), name.getText(), IP.getText(), make.getSelectedItem().toString(), model.getSelectedItem().toString(), serialNumber.getText(), softwareVersion.getText());
//                firewallTable.refreshTable();
//            }
//        }
//        else if (actionEvent.getSource() == firewallRemoveButton){
//            JPanel panelFirewallAdd = new JPanel(new GridBagLayout());
//
//            JComboBox place = new JComboBox(database.getAllPlaces());
//            JTextField location = new JTextField(10);
//            JTextField floor = new JTextField(10);
//            JTextField name = new JTextField(10);
//            JTextField IP = new JTextField(10);
//            JComboBox make = new JComboBox(database.getAllMakes());
//            JComboBox model = new JComboBox(database.getAllSwitchModels());
//            JTextField serialNumber = new JTextField(10);
//            JTextField softwareVersion = new JTextField(10);
//
//
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.gridx = 0;
//            gbc.gridy = 1;
//            gbc.ipady = 10;
//            gbc.insets = new Insets(5,5,5,0);
//            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
//            gbc.weightx = 0.0;
//            gbc.weighty = 0.0;
//
//            panelFirewallAdd.add(new JLabel("Konum:"), gbc);
//            gbc.gridx = 1;
//            panelFirewallAdd.add(place, gbc);
//
//            gbc.gridx = 2;
//            panelFirewallAdd.add(new JLabel("Lokasyon:"), gbc);
//            gbc.gridx = 3;
//            panelFirewallAdd.add(location, gbc);
//
//            gbc.gridx = 4;
//            panelFirewallAdd.add(new JLabel("Yer:"), gbc);
//            gbc.gridx = 5;
//            panelFirewallAdd.add(floor, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy = 2;
//            panelFirewallAdd.add(new JLabel("İsim:"), gbc);
//            gbc.gridx = 1;
//            panelFirewallAdd.add(name, gbc);
//
//            gbc.gridx = 2;
//            panelFirewallAdd.add(new JLabel("IP:"), gbc);
//            gbc.gridx = 3;
//            panelFirewallAdd.add(IP, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy = 3;
//            panelFirewallAdd.add(new JLabel("Marka:"), gbc);
//            gbc.gridx = 1;
//            panelFirewallAdd.add(make, gbc);
//
//            gbc.gridx = 2;
//            panelFirewallAdd.add(new JLabel("Model:"), gbc);
//            gbc.gridx = 3;
//            panelFirewallAdd.add(model, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy = 4;
//            panelFirewallAdd.add(new JLabel("Seri NO:"), gbc);
//            gbc.gridx = 1;
//            panelFirewallAdd.add(serialNumber, gbc);
//
//            gbc.gridx = 2;
//            panelFirewallAdd.add(new JLabel("Yazılım Versiyonu:"), gbc);
//            gbc.gridx = 3;
//            panelFirewallAdd.add(softwareVersion, gbc);
//
//            int result = JOptionPane.showConfirmDialog(mainFrame, panelFirewallAdd, "Değerleri giriniz", JOptionPane.OK_CANCEL_OPTION);
//            if (result == JOptionPane.OK_OPTION){
//                database.removeFirewall(place.getSelectedItem().toString(), location.getText(), floor.getText(), name.getText(), IP.getText(), make.getSelectedItem().toString(), model.getSelectedItem().toString(), serialNumber.getText(), softwareVersion.getText());
//                firewallTable.refreshTable();
//            }
//        }
    }
}
