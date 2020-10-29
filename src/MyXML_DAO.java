import java.util.ArrayList;

public interface MyXML_DAO {    // Data Access Object
    ArrayList<MySwitch> getAllSwitches();

    ArrayList<MySwitch> searchSwitches(String place, String location, String floor, String name, String ip, String make, String model, String serial_number, String software_version);

    void updateSwitch(String oldPlace, String oldLocation, String oldFloor, String oldName, String oldIP, String oldMake, String oldModel, String old_serial_number, String old_software_version,
                      String newPlace, String newLocation, String newFloor, String newName, String newIP, String newMake, String newModel, String new_serial_number, String new_software_version);

    void removeSwitch(String place, String location, String floor, String name, String ip, String make, String model, String serial_number, String software_version);

    void addSwitch(String place, String location, String floor, String name, String ip, String make, String model, String serial_number, String software_version);



    ArrayList<MyModule> getAllModules();


    ArrayList<MyModule> searchModules(String location, String model, String serial_number, String IP);


    void updateModule(String oldLocation, String oldModel, String old_serial_number, String oldIP,
                      String newLocation, String newModel, String new_serial_number, String newIP);


    void removeModule(String location, String model, String serial_number, String IP);


    void addModule(String location, String model, String serial_number, String IP);



    ArrayList<MySFP> getAllSFPs();


    ArrayList<MySFP> searchSFPs(String location, String model, String serial_number, String IP);


    void updateSFP(String oldLocation, String oldModel, String old_serial_number, String oldIP,
                      String newLocation, String newModel, String new_serial_number, String newIP);


    void removeSFP(String location, String model, String serial_number, String IP);


    void addSFP(String location, String model, String serial_number, String IP);


    ArrayList<MyFirewall> getAllFirewalls();

    ArrayList<MyFirewall> searchFirewalls(String place, String location, String floor, String name, String ip, String make, String model, String serial_number, String software_version);

    void updateFirewall(String oldPlace, String oldLocation, String oldFloor, String oldName, String oldIP, String oldMake, String oldModel, String old_serial_number, String old_software_version,
                      String newPlace, String newLocation, String newFloor, String newName, String newIP, String newMake, String newModel, String new_serial_number, String new_software_version);

    void removeFirewall(String place, String location, String floor, String name, String ip, String make, String model, String serial_number, String software_version);

    void addFirewall(String place, String location, String floor, String name, String ip, String make, String model, String serial_number, String software_version);


    String[] getAllPlaces();

    void addPlace(String place);

    void removePlace(String place);


    String[] getAllSwitchMakes();

    String[] getAllSwitchModelsByMake(String make);

    void addSwitchMake(String make);

    void removeSwitchMake(String make);

    void addSwitchModel(String make, String model);

    void removeSwitchModel(String make, String model);


    String[] getAllModuleMakes();

    String[] getAllModuleModelsByMake(String make);

    void addModuleMake(String make);

    void removeModuleMake(String make);

    void addModuleModel(String make, String model);

    void removeModuleModel(String make, String model);


    String[] getAllSFPMakes();

    String[] getAllSFPModelsByMake(String make);

    void addSFPMake(String make);

    void removeSFPMake(String make);

    void addSFPModel(String make, String model);

    void removeSFPModel(String make, String model);


}
