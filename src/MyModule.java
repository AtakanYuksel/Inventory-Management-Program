import java.util.ArrayList;

public class MyModule implements Comparable<MyModule> {
    private String location;
    private String model;
    private String serial_number;
    private String IP;

    private ArrayList<MySFP> mySFPArrayList;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Konum: ").append(getLocation()).append("\tModel: ").append(getModel()).append("\tSeri NO: ").append(serial_number).append("\tIP: ").append(getIP());
        for (MySFP aSFP : mySFPArrayList){
            stringBuilder.append("\n\t---->SFP: ").append(aSFP.toString());
        }
        return stringBuilder.toString();
    }

    public MyModule(String location, String model, String serial_number, String IP, ArrayList<MySFP> mySFPArrayList) {
        this.location = location;
        this.model = model;
        this.serial_number = serial_number;
        this.IP = IP;

        this.mySFPArrayList = mySFPArrayList;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getSerial_number() {
        return serial_number;
    }
    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }
    public String getIP() {
        return IP;
    }
    public void setIP(String IP) {
        this.IP = IP;
    }
    public ArrayList<MySFP> getMySFPArrayList() {
        return mySFPArrayList;
    }
    public void setMySFPArrayList(ArrayList<MySFP> mySFPArrayList) {
        this.mySFPArrayList = mySFPArrayList;
    }

    @Override
    public int compareTo(MyModule myModule) {
        return this.getLocation().compareTo(myModule.getLocation());
    }
}
