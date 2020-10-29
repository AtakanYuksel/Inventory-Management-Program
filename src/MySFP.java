public class MySFP implements Comparable<MySFP> {
    private String location;
    private String model;
    private String serial_number;
    private String IP;

    @Override
    public String toString() {
        return "Lokasyon: " + location + "\tModel: " + model + "\tSeri NO: " + serial_number + "\tIP: " + IP;
    }

    public MySFP(String location, String model, String serial_number, String IP) {
        this.location = location;
        this.model = model;
        this.serial_number = serial_number;
        this.IP = IP;
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

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()){
            return this.toString().equals(obj.toString());
        }
        return false;
    }

    @Override
    public int compareTo(MySFP mySFP) {
        return this.getLocation().compareTo(mySFP.getLocation());
    }
}
