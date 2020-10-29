import java.util.ArrayList;

public class MySwitch implements Comparable<MySwitch> {
    private String place;
    private String location;
    private String floor;
    private String name;
    private String IP;
    private String make;
    private String model;
    private String serialNumber;
    private String softwareVersion;

    private ArrayList<MyModule> myModuleArrayList;

    public MySwitch(String place, String location, String floor, String name, String IP, String make, String model, String serialNumber, String softwareVersion, ArrayList<MyModule> myModuleArrayList) {
        this.place = place;
        this.location = location;
        this.floor = floor;
        this.name = name;
        this.IP = IP;
        this.make = make;
        this.model = model;
        this.serialNumber = serialNumber;
        this.softwareVersion = softwareVersion;

        this.myModuleArrayList = myModuleArrayList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Konum: ").append(getPlace()).append("\tLokasyon: ").append(getLocation()).append("\tYer: ").append(getFloor()).append("\tİsim: ").append(getName()).append("\tIP: ").append(getIP())
                     .append("\tMarka: ").append(getMake()).append("\tModel: ").append(getModel()).append("\tSeri NO: ").append(getSerialNumber()).append("\tYazılım Versiyonu: ").append(softwareVersion);
        for (MyModule aModule : myModuleArrayList){
            stringBuilder.append("\n---->Modül: ").append(aModule.toString());
        }
        return stringBuilder.toString();
    }
    public void printSwitch(){
        System.out.println(toString());
    }

    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getFloor() {
        return floor;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIP() {
        return IP;
    }
    public void setIP(String IP) {
        this.IP = IP;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getSoftwareVersion() {
        return softwareVersion;
    }
    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }
    public ArrayList<MyModule> getMyModuleArrayList() {
        return myModuleArrayList;
    }
    public void setMyModuleArrayList(ArrayList<MyModule> myModuleArrayList) {
        this.myModuleArrayList = myModuleArrayList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            return this.toString().equals(obj.toString());
        }
        return false;
    }

    @Override
    public int compareTo(MySwitch mySwitch) {
        return this.getName().compareTo(mySwitch.getName());
    }
}
