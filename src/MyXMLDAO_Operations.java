import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Collections;

public class MyXMLDAO_Operations implements MyXML_DAO {   // Operation Implementations
    String path;
    Document myDoc;

    public MyXMLDAO_Operations(String path) {
        this.path = path;
        this.myDoc = XML_ReadWrite.readFile(path);
    }

    @Override
    public ArrayList<MySwitch> getAllSwitches() {
        return searchSwitches("", "", "", "", "", "", "", "", "");
    }

    @Override
    public ArrayList<MySwitch> searchSwitches(String place, String location, String floor, String name, String ip, String make, String model, String serial_number, String software_version) {
        NodeList nodeList = myDoc.getElementsByTagName("switch");
        ArrayList<MySwitch> switchArrayList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            if (
                    (element.getElementsByTagName("place").item(0).getTextContent().equals(place) || place.equals("")) &&
                    (element.getElementsByTagName("name").item(0).getTextContent().equals(name) || name.equals("")) &&
                    (element.getElementsByTagName("ip").item(0).getTextContent().equals(ip) || ip.equals("")) &&
                    (element.getElementsByTagName("make").item(0).getTextContent().equals(make) || make.equals("")) &&
                    (element.getElementsByTagName("model").item(0).getTextContent().equals(model) || model.equals("")) &&
                    (element.getElementsByTagName("serial_number").item(0).getTextContent().equals(serial_number) || serial_number.equals("")) &&
                    (element.getElementsByTagName("software_version").item(0).getTextContent().equals(software_version) || software_version.equals(""))
            ) {
                switchArrayList.add(new MySwitch(
                    element.getElementsByTagName("place").item(0).getTextContent(),
                    element.getElementsByTagName("location").item(0).getTextContent(),
                    element.getElementsByTagName("floor").item(0).getTextContent(),
                    element.getElementsByTagName("name").item(0).getTextContent(),
                    element.getElementsByTagName("ip").item(0).getTextContent(),
                    element.getElementsByTagName("make").item(0).getTextContent(),
                    element.getElementsByTagName("model").item(0).getTextContent(),
                    element.getElementsByTagName("serial_number").item(0).getTextContent(),
                    element.getElementsByTagName("software_version").item(0).getTextContent(),
                    searchModules("","","", element.getElementsByTagName("ip").item(0).getTextContent())
                ));
            }
        }
        Collections.sort(switchArrayList);
        return switchArrayList;
    }

    @Override
    public void updateSwitch(String oldPlace, String oldLocation, String oldFloor, String oldName, String oldIP, String oldMake, String oldModel, String old_serial_number, String old_software_version,
                             String newPlace, String newLocation, String newFloor, String newName, String newIP, String newMake, String newModel, String new_serial_number, String new_software_version) {
        removeSwitch(oldPlace, oldLocation, oldFloor, oldName, oldIP, oldMake, oldModel, old_serial_number, old_software_version);
        addSwitch(newPlace, newLocation, newFloor, newName, newIP, newMake, newModel, new_serial_number, new_software_version);
    }

    @Override
    public void removeSwitch(String place, String location, String floor, String name, String ip, String make, String model, String serial_number, String software_version) {
        Node switchesNode = myDoc.getElementsByTagName("switches").item(0);
        NodeList switchesChildNodes = switchesNode.getChildNodes();
        for (int i = 0; i < switchesChildNodes.getLength(); i++) {
            Node switchChildNode = switchesChildNodes.item(i);
            if (switchChildNode.getNodeType() == Node.ELEMENT_NODE) {
                Element switchElement = (Element) switchChildNode;
                if (
                        (switchElement.getElementsByTagName("place").item(0).getTextContent().equals(place)) &&
                                (switchElement.getElementsByTagName("location").item(0).getTextContent().equals(location)) &&
                                (switchElement.getElementsByTagName("floor").item(0).getTextContent().equals(floor)) &&
                                (switchElement.getElementsByTagName("name").item(0).getTextContent().equals(name)) &&
                                (switchElement.getElementsByTagName("ip").item(0).getTextContent().equals(ip)) &&
                                (switchElement.getElementsByTagName("make").item(0).getTextContent().equals(make)) &&
                                (switchElement.getElementsByTagName("model").item(0).getTextContent().equals(model)) &&
                                (switchElement.getElementsByTagName("serial_number").item(0).getTextContent().equals(serial_number)) &&
                                (switchElement.getElementsByTagName("software_version").item(0).getTextContent().equals(software_version))
                ) {
                    switchesNode.removeChild(switchChildNode);
                    break;  // If multiple switches have the same fields remove the first one that fits the criteria and be done.
                }
            }
        }
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public void addSwitch(String place, String location, String floor, String name, String ip, String make, String model, String serial_number, String software_version) {
        Element newSwitch = myDoc.createElement("switch");
//        newSwitch.setAttribute("id", String.valueOf(name.hashCode()));

        Element placeElement = myDoc.createElement("place");
        placeElement.setTextContent(place);
        newSwitch.appendChild(placeElement);

        Element locationElement = myDoc.createElement("location");
        locationElement.setTextContent(location);
        newSwitch.appendChild(locationElement);

        Element floorElement = myDoc.createElement("floor");
        floorElement.setTextContent(floor);
        newSwitch.appendChild(floorElement);

        Element nameElement = myDoc.createElement("name");
        nameElement.setTextContent(name);
        newSwitch.appendChild(nameElement);

        Element ipElement = myDoc.createElement("ip");
        ipElement.setTextContent(ip);
        newSwitch.appendChild(ipElement);

        Element makeElement = myDoc.createElement("make");
        makeElement.setTextContent(make);
        newSwitch.appendChild(makeElement);

        Element modelElement = myDoc.createElement("model");
        modelElement.setTextContent(model);
        newSwitch.appendChild(modelElement);

        Element serial_numberElement = myDoc.createElement("serial_number");
        serial_numberElement.setTextContent(serial_number);
        newSwitch.appendChild(serial_numberElement);

        Element software_versionElement = myDoc.createElement("software_version");
        software_versionElement.setTextContent(software_version);
        newSwitch.appendChild(software_versionElement);

        myDoc.getElementsByTagName("switches").item(0).appendChild(newSwitch);
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public ArrayList<MyModule> getAllModules() {
        return searchModules("","","","");
    }

    @Override
    public ArrayList<MyModule> searchModules(String location, String model, String serial_number, String IP) {
        NodeList nodeList = myDoc.getElementsByTagName("module");
        ArrayList<MyModule> myModuleArrayList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            if (
                    (element.getElementsByTagName("location").item(0).getTextContent().equals(location) || location.equals("")) &&
                    (element.getElementsByTagName("model").item(0).getTextContent().equals(model) || model.equals("")) &&
                    (element.getElementsByTagName("serial_number").item(0).getTextContent().equals(serial_number) || serial_number.equals("")) &&
                    (element.getElementsByTagName("ip").item(0).getTextContent().equals(IP) || IP.equals(""))
            ) {
                myModuleArrayList.add(new MyModule(
                        element.getElementsByTagName("location").item(0).getTextContent(),
                        element.getElementsByTagName("model").item(0).getTextContent(),
                        element.getElementsByTagName("serial_number").item(0).getTextContent(),
                        element.getElementsByTagName("ip").item(0).getTextContent(),
                        searchSFPs("","","", element.getElementsByTagName("ip").item(0).getTextContent())
                ));
            }
        }

        return myModuleArrayList;
    }

    @Override
    public void updateModule(String oldLocation, String oldModel, String old_serial_number, String oldIP, String newLocation, String newModel, String new_serial_number, String newIP) {
        removeModule(oldLocation, oldModel, old_serial_number, oldIP);
        addModule(newLocation, newModel, new_serial_number, newIP);
    }

    @Override
    public void removeModule(String location, String model, String serial_number, String IP) {
        Node modulesNode = myDoc.getElementsByTagName("modules").item(0);
        NodeList modulesChildNodes = modulesNode.getChildNodes();
        for (int i = 0; i < modulesChildNodes.getLength(); i++) {
            Node moduleChildNode = modulesChildNodes.item(i);
            if (moduleChildNode.getNodeType() == Node.ELEMENT_NODE) {
                Element moduleElement = (Element) moduleChildNode;
                if (
                        (moduleElement.getElementsByTagName("location").item(0).getTextContent().equals(location)) &&
                        (moduleElement.getElementsByTagName("model").item(0).getTextContent().equals(model)) &&
                        (moduleElement.getElementsByTagName("serial_number").item(0).getTextContent().equals(serial_number) &&
                        (moduleElement.getElementsByTagName("ip").item(0).getTextContent().equals(IP)))
                ) {
                    modulesNode.removeChild(moduleChildNode);
                    break;  // If multiple modules have the same fields remove the first one that fits the criteria and be done.
                }
            }
        }
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public void addModule(String location, String model, String serial_number, String IP) {
        Element newModule = myDoc.createElement("module");

        Element locationElement = myDoc.createElement("location");
        locationElement.setTextContent(location);
        newModule.appendChild(locationElement);

        Element modelElement = myDoc.createElement("model");
        modelElement.setTextContent(model);
        newModule.appendChild(modelElement);

        Element serial_numberElement = myDoc.createElement("serial_number");
        serial_numberElement.setTextContent(serial_number);
        newModule.appendChild(serial_numberElement);

        Element ipElement = myDoc.createElement("ip");
        ipElement.setTextContent(IP);
        newModule.appendChild(ipElement);

        myDoc.getElementsByTagName("modules").item(0).appendChild(newModule);
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public ArrayList<MySFP> getAllSFPs() {
        return searchSFPs("","","","");
    }

    @Override
    public ArrayList<MySFP> searchSFPs(String location, String model, String serial_number, String IP) {
        NodeList nodeList = myDoc.getElementsByTagName("sfp");
        ArrayList<MySFP> mySFPArrayList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            if (
                    (element.getElementsByTagName("location").item(0).getTextContent().equals(location) || location.equals("")) &&
                    (element.getElementsByTagName("model").item(0).getTextContent().equals(model) || model.equals("")) &&
                    (element.getElementsByTagName("serial_number").item(0).getTextContent().equals(serial_number) || serial_number.equals("")) &&
                    (element.getElementsByTagName("ip").item(0).getTextContent().equals(IP) || IP.equals(""))
            ) {
                mySFPArrayList.add(new MySFP(
                        element.getElementsByTagName("location").item(0).getTextContent(),
                        element.getElementsByTagName("model").item(0).getTextContent(),
                        element.getElementsByTagName("serial_number").item(0).getTextContent(),
                        element.getElementsByTagName("ip").item(0).getTextContent()
                ));
            }
        }
        return mySFPArrayList;
    }

    @Override
    public void updateSFP(String oldLocation, String oldModel, String old_serial_number, String oldIP, String newLocation, String newModel, String new_serial_number, String newIP) {
        removeSFP(oldLocation, oldModel, old_serial_number, oldIP);
        addSFP(newLocation, newModel, new_serial_number, newIP);
    }

    @Override
    public void removeSFP(String location, String model, String serial_number, String IP) {
        Node sfpsNode = myDoc.getElementsByTagName("sfps").item(0);
        NodeList sfpsChildNodes = sfpsNode.getChildNodes();
        for (int i = 0; i < sfpsChildNodes.getLength(); i++) {
            Node sfpChildNode = sfpsChildNodes.item(i);
            if (sfpChildNode.getNodeType() == Node.ELEMENT_NODE) {
                Element sfpElement = (Element) sfpChildNode;
                if (
                        (sfpElement.getElementsByTagName("location").item(0).getTextContent().equals(location)) &&
                        (sfpElement.getElementsByTagName("model").item(0).getTextContent().equals(model)) &&
                        (sfpElement.getElementsByTagName("serial_number").item(0).getTextContent().equals(serial_number) &&
                        (sfpElement.getElementsByTagName("ip").item(0).getTextContent().equals(IP)))
                ) {
                    sfpsNode.removeChild(sfpChildNode);
                    break;  // If multiple SFPs have the same fields remove the first one that fits the criteria and be done.
                }
            }
        }
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public void addSFP(String location, String model, String serial_number, String IP) {
        Element newSFP = myDoc.createElement("sfp");

        Element locationElement = myDoc.createElement("location");
        locationElement.setTextContent(location);
        newSFP.appendChild(locationElement);

        Element modelElement = myDoc.createElement("model");
        modelElement.setTextContent(model);
        newSFP.appendChild(modelElement);

        Element serial_numberElement = myDoc.createElement("serial_number");
        serial_numberElement.setTextContent(serial_number);
        newSFP.appendChild(serial_numberElement);

        Element ipElement = myDoc.createElement("ip");
        ipElement.setTextContent(IP);
        newSFP.appendChild(ipElement);

        myDoc.getElementsByTagName("sfps").item(0).appendChild(newSFP);
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public ArrayList<MyFirewall> getAllFirewalls() {
        return searchFirewalls("","","","","","","","","");
    }

    @Override
    public ArrayList<MyFirewall> searchFirewalls(String place, String location, String floor, String name, String ip, String make, String model, String serial_number, String software_version) {
        NodeList nodeList = myDoc.getElementsByTagName("firewall");
        ArrayList<MyFirewall> firewallArrayList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            if (
                    (element.getElementsByTagName("place").item(0).getTextContent().equals(place) || place.equals("")) &&
                            (element.getElementsByTagName("location").item(0).getTextContent().equals(location) || location.equals("")) &&
                            (element.getElementsByTagName("floor").item(0).getTextContent().equals(floor) || floor.equals("")) &&
                            (element.getElementsByTagName("name").item(0).getTextContent().equals(name) || name.equals("")) &&
                            (element.getElementsByTagName("ip").item(0).getTextContent().equals(ip) || ip.equals("")) &&
                            (element.getElementsByTagName("make").item(0).getTextContent().equals(make) || make.equals("")) &&
                            (element.getElementsByTagName("model").item(0).getTextContent().equals(model) || model.equals("")) &&
                            (element.getElementsByTagName("serial_number").item(0).getTextContent().equals(serial_number) || serial_number.equals("")) &&
                            (element.getElementsByTagName("software_version").item(0).getTextContent().equals(software_version) || software_version.equals(""))
            ) {
                firewallArrayList.add(new MyFirewall(
                        element.getElementsByTagName("place").item(0).getTextContent(),
                        element.getElementsByTagName("location").item(0).getTextContent(),
                        element.getElementsByTagName("floor").item(0).getTextContent(),
                        element.getElementsByTagName("name").item(0).getTextContent(),
                        element.getElementsByTagName("ip").item(0).getTextContent(),
                        element.getElementsByTagName("make").item(0).getTextContent(),
                        element.getElementsByTagName("model").item(0).getTextContent(),
                        element.getElementsByTagName("serial_number").item(0).getTextContent(),
                        element.getElementsByTagName("software_version").item(0).getTextContent()
                ));
            }
        }
        Collections.sort(firewallArrayList);
        return firewallArrayList;
    }

    @Override
    public void updateFirewall(String oldPlace, String oldLocation, String oldFloor, String oldName, String oldIP, String oldMake, String oldModel, String old_serial_number, String old_software_version,
                               String newPlace, String newLocation, String newFloor, String newName, String newIP, String newMake, String newModel, String new_serial_number, String new_software_version) {
        removeFirewall(oldPlace, oldLocation, oldFloor, oldName, oldIP, oldMake, oldModel, old_serial_number, old_software_version);
        addFirewall(newPlace, newLocation, newFloor, newName, newIP, newMake, newModel, new_serial_number, new_software_version);
    }

    @Override
    public void removeFirewall(String place, String location, String floor, String name, String ip, String make, String model, String serial_number, String software_version) {
        Node firewallsNode = myDoc.getElementsByTagName("firewalls").item(0);
        NodeList firewallsChildNodes = firewallsNode.getChildNodes();
        for (int i = 0; i < firewallsChildNodes.getLength(); i++) {
            Node firewallChildNode = firewallsChildNodes.item(i);
            if (firewallChildNode.getNodeType() == Node.ELEMENT_NODE) {
                Element firewallElement = (Element) firewallChildNode;
                if (
                        (firewallElement.getElementsByTagName("place").item(0).getTextContent().equals(place)) &&
                                (firewallElement.getElementsByTagName("location").item(0).getTextContent().equals(location)) &&
                                (firewallElement.getElementsByTagName("floor").item(0).getTextContent().equals(floor)) &&
                                (firewallElement.getElementsByTagName("name").item(0).getTextContent().equals(name)) &&
                                (firewallElement.getElementsByTagName("ip").item(0).getTextContent().equals(ip)) &&
                                (firewallElement.getElementsByTagName("make").item(0).getTextContent().equals(make)) &&
                                (firewallElement.getElementsByTagName("model").item(0).getTextContent().equals(model)) &&
                                (firewallElement.getElementsByTagName("serial_number").item(0).getTextContent().equals(serial_number)) &&
                                (firewallElement.getElementsByTagName("software_version").item(0).getTextContent().equals(software_version))
                ) {
                    firewallsNode.removeChild(firewallChildNode);
                    break;  // If multiple firewalls have the same fields remove the first one that fits the criteria and be done.
                }
            }
        }
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public void addFirewall(String place, String location, String floor, String name, String ip, String make, String model, String serial_number, String software_version) {
        Element newFirewall = myDoc.createElement("firewall");
//        newFirewall.setAttribute("id", String.valueOf(name.hashCode()));

        Element placeElement = myDoc.createElement("place");
        placeElement.setTextContent(place);
        newFirewall.appendChild(placeElement);

        Element locationElement = myDoc.createElement("location");
        locationElement.setTextContent(location);
        newFirewall.appendChild(locationElement);

        Element floorElement = myDoc.createElement("floor");
        floorElement.setTextContent(floor);
        newFirewall.appendChild(floorElement);

        Element nameElement = myDoc.createElement("name");
        nameElement.setTextContent(name);
        newFirewall.appendChild(nameElement);

        Element ipElement = myDoc.createElement("ip");
        ipElement.setTextContent(ip);
        newFirewall.appendChild(ipElement);

        Element makeElement = myDoc.createElement("make");
        makeElement.setTextContent(make);
        newFirewall.appendChild(makeElement);

        Element modelElement = myDoc.createElement("model");
        modelElement.setTextContent(model);
        newFirewall.appendChild(modelElement);

        Element serial_numberElement = myDoc.createElement("serial_number");
        serial_numberElement.setTextContent(serial_number);
        newFirewall.appendChild(serial_numberElement);

        Element software_versionElement = myDoc.createElement("software_version");
        software_versionElement.setTextContent(software_version);
        newFirewall.appendChild(software_versionElement);

        myDoc.getElementsByTagName("firewalls").item(0).appendChild(newFirewall);
        XML_ReadWrite.updateFile(path, myDoc);
    }

//    @Override
//    public String[] getAllMakes() {
//        NodeList makesNodeList = myDoc.getElementsByTagName("makes");
//        Node makesNode = makesNodeList.item(0);
//        Element makesElement = (Element) makesNode;
//        NodeList makeNodeList = makesElement.getElementsByTagName("make");
//        String[] makesArray = new String[makeNodeList.getLength()];
//        for (int i = 0; i < makeNodeList.getLength(); i++) {
//            Node makeNode = makeNodeList.item(i);
//            Element makeElement = (Element) makeNode;
//            makesArray[i] = makeElement.getTextContent();
//        }
//        return makesArray;
//    }
//
//    @Override
//    public void addMake(String make) {
//        Element newMake = myDoc.createElement("make");
//        newMake.setTextContent(make);
//        myDoc.getElementsByTagName("makes").item(0).appendChild(newMake);
//        XML_ReadWrite.updateFile(path, myDoc);
//    }
//
//    @Override
//    public void removeMake(String make) {
//        Node node = myDoc.getElementsByTagName("makes").item(0);
//        NodeList makesChildNodes = node.getChildNodes();
//        for (int i = 0; i < makesChildNodes.getLength(); i++) {
//            Node makeChildNode = makesChildNodes.item(i);
//            if (make.equals(makeChildNode.getTextContent())) {
//                node.removeChild(makeChildNode);
//            }
//        }
//        XML_ReadWrite.updateFile(path, myDoc);
//    }

    @Override
    public String[] getAllPlaces() {
        NodeList placesNodeList = myDoc.getElementsByTagName("places");
        Node placesNode = placesNodeList.item(0);
        Element placesElement = (Element) placesNode;
        NodeList placeNodeList = placesElement.getElementsByTagName("place");
        String[] placesArray = new String[placeNodeList.getLength()];
        for (int i = 0; i < placeNodeList.getLength(); i++) {
            Node placeNode = placeNodeList.item(i);
            Element placeElement = (Element) placeNode;
            placesArray[i] = (placeElement.getTextContent());
        }
        return placesArray;
    }

    @Override
    public void addPlace(String place) {
        Element newPlace = myDoc.createElement("place");
        newPlace.setTextContent(place);
        myDoc.getElementsByTagName("places").item(0).appendChild(newPlace);
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public void removePlace(String place) {
        Node node = myDoc.getElementsByTagName("places").item(0);
        NodeList placesChildNodes = node.getChildNodes();
        for (int i = 0; i < placesChildNodes.getLength(); i++) {
            Node placeChildNode = placesChildNodes.item(i);
            if (place.equals(placeChildNode.getTextContent())) {
                node.removeChild(placeChildNode);
            }
        }
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public String[] getAllSwitchMakes() {
        Node allSwitchModelsNode = myDoc.getElementsByTagName("all_switch_models").item(0);
        NodeList switchMakesNodeList = ((Element) allSwitchModelsNode).getElementsByTagName("switch_models");
        String[] switchMakesArray = new String[switchMakesNodeList.getLength()];
        for (int i = 0; i < switchMakesNodeList.getLength(); i++) {
            if (switchMakesNodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element switchMakeElement = (Element) switchMakesNodeList.item(i);
                switchMakesArray[i] = switchMakeElement.getAttribute("make");
            }
        }
        return switchMakesArray;
    }

    @Override
    public String[] getAllSwitchModelsByMake(String make) {
        Node allSwitchModelsNode = myDoc.getElementsByTagName("all_switch_models").item(0);
        NodeList switchModelsNodeList = ((Element) allSwitchModelsNode).getElementsByTagName("switch_models");
        ArrayList<String> switchModelsArrayList = new ArrayList<>();
        for (int i = 0; i < switchModelsNodeList.getLength(); i++) {
            if (((Element) switchModelsNodeList.item(i)).getAttribute("make").equals(make)){
                NodeList switchModelNodeList = ((Element) switchModelsNodeList.item(i)).getElementsByTagName("model");
                for (int j = 0; j < switchModelNodeList.getLength(); j++) {
                    if (switchModelNodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                        Element switchModelElement = (Element) switchModelNodeList.item(i);
                        switchModelsArrayList.add(switchModelElement.getTextContent());
                    }
                }
            }
        }
        String[] switchModelsByMakeArray = new String[switchModelsArrayList.size()];
        for (int i = 0; i < switchModelsArrayList.size(); i++) {
            switchModelsByMakeArray[i] = switchModelsArrayList.get(i);
        }
        return switchModelsByMakeArray;

//        Node switchModelsNode = switchModelsNodeList.item(0);
//        Element switchModelsElement = (Element) switchModelsNode;
//        NodeList switchModelNodeList = switchModelsElement.getElementsByTagName("model");
//        String[] switchModelsArray = new String[switchModelNodeList.getLength()];
//        for (int i = 0; i < switchModelNodeList.getLength(); i++) {
//            Node switchModelNode = switchModelNodeList.item(i);
//            Element switchModelElement = (Element) switchModelNode;
//            switchModelsArray[i] = switchModelElement.getTextContent();
//        }
    }

    @Override
    public void addSwitchMake(String make) {
        Element newSwitchMake = myDoc.createElement("switch_models");
        newSwitchMake.setAttribute("make", make);
        myDoc.getElementsByTagName("all_switch_models").item(0).appendChild(newSwitchMake);
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public void removeSwitchMake(String make) {
        Node allSwitchModelsNode = myDoc.getElementsByTagName("all_switch_models").item(0);
        NodeList switchModelNodeList = allSwitchModelsNode.getChildNodes();
        for (int i = 0; i < switchModelNodeList.getLength(); i++) {
            if (switchModelNodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                if (((Element) switchModelNodeList.item(i)).getAttribute("make").equals(make)){
                    allSwitchModelsNode.removeChild(switchModelNodeList.item(i));
                }
            }
        }
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public void addSwitchModel(String make, String model) {
        Element newSwitchModel = myDoc.createElement("model");
        newSwitchModel.setTextContent(model);
        Node allSwitchModelsNode = myDoc.getElementsByTagName("all_switch_models").item(0);
        NodeList switchModelNodeList = allSwitchModelsNode.getChildNodes();
        for (int i = 0; i < switchModelNodeList.getLength(); i++) {
            if (switchModelNodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                if (((Element) switchModelNodeList.item(i)).getAttribute("make").equals(make)){
                    switchModelNodeList.item(i).appendChild(newSwitchModel);
                }
            }
        }
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public void removeSwitchModel(String make, String model) {
        Element switchModelToRemove = myDoc.createElement("model");
        switchModelToRemove.setTextContent(model);
        Node allSwitchModelsNode = myDoc.getElementsByTagName("all_switch_models").item(0);
        NodeList switchModelNodeList = allSwitchModelsNode.getChildNodes();
        for (int i = 0; i < switchModelNodeList.getLength(); i++) {
            if (switchModelNodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                if (((Element) switchModelNodeList.item(i)).getAttribute("make").equals(make)){
                    switchModelNodeList.item(i).removeChild(switchModelToRemove);
                }
            }
        }
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public String[] getAllModuleMakes() {
        Node allModuleModelsNode = myDoc.getElementsByTagName("all_module_models").item(0);
        NodeList moduleMakesNodeList = ((Element) allModuleModelsNode).getElementsByTagName("module_models");
        String[] moduleMakesArray = new String[moduleMakesNodeList.getLength()];
        for (int i = 0; i < moduleMakesNodeList.getLength(); i++) {
            Element moduleMakeElement = (Element) moduleMakesNodeList.item(i);
            moduleMakesArray[i] = moduleMakeElement.getAttribute("make");
        }
        return moduleMakesArray;
    }

    @Override
    public String[] getAllModuleModelsByMake(String make) {
        NodeList moduleModelsNodeList = myDoc.getElementsByTagName("module_models");
        ArrayList<String> moduleModelsArrayList = new ArrayList<>();
        for (int i = 0; i < moduleModelsNodeList.getLength(); i++) {
            if (((Element) moduleModelsNodeList.item(i)).getAttribute("make").equals(make)){
                moduleModelsArrayList.add(((Element) moduleModelsNodeList.item(i)).getAttribute("make"));
            }
        }
        String[] moduleModelsByMakeArray = new String[moduleModelsArrayList.size()];
        for (int i = 0; i < moduleModelsArrayList.size(); i++) {
            moduleModelsByMakeArray[i] = moduleModelsArrayList.get(i);
        }
        return moduleModelsByMakeArray;
    }

    @Override
    public void addModuleMake(String make) {

    }

    @Override
    public void removeModuleMake(String make) {

    }

    @Override
    public void addModuleModel(String make, String model) {
        Element newModuleModel = myDoc.createElement("model");
        newModuleModel.setTextContent(model);
        NodeList moduleModelNodeList = myDoc.getElementsByTagName("module_models");
        for (int i = 0; i < moduleModelNodeList.getLength(); i++) {
            if (((Element) moduleModelNodeList.item(i)).getAttribute("make").equals(make)){
                moduleModelNodeList.item(i).appendChild(newModuleModel);
            }
        }
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public void removeModuleModel(String make, String model) {
        Element moduleModelToRemove = myDoc.createElement("model");
        moduleModelToRemove.setTextContent(model);
        NodeList moduleModelNodeList = myDoc.getElementsByTagName("module_models");
        for (int i = 0; i < moduleModelNodeList.getLength(); i++) {
            if (((Element) moduleModelNodeList.item(i)).getAttribute("make").equals(make)){
                moduleModelNodeList.item(i).removeChild(moduleModelToRemove);
            }
        }
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public String[] getAllSFPMakes() {
        Node allSFPMakesNode = myDoc.getElementsByTagName("all_sfp_models").item(0);
        NodeList sfpMakesNodeList = ((Element) allSFPMakesNode).getElementsByTagName("sfp_models");
        String[] sfpMakesArray = new String[sfpMakesNodeList.getLength()];
        for (int i = 0; i < sfpMakesNodeList.getLength(); i++) {
            if (sfpMakesNodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element sfpMakeElement = (Element) sfpMakesNodeList.item(i);
                sfpMakesArray[i] = sfpMakeElement.getAttribute("make");
            }
        }
        return sfpMakesArray;
    }

    @Override
    public String[] getAllSFPModelsByMake(String make) {
        NodeList sfpModelsNodeList = myDoc.getElementsByTagName("sfp_models");
        ArrayList<String> sfpModelsArrayList = new ArrayList<>();
        for (int i = 0; i < sfpModelsNodeList.getLength(); i++) {
            if (((Element) sfpModelsNodeList.item(i)).getAttribute("make").equals(make)){
                sfpModelsArrayList.add(((Element) sfpModelsNodeList.item(i)).getAttribute("make"));
            }
        }
        String[] sfpModelsByMakeArray = new String[sfpModelsArrayList.size()];
        for (int i = 0; i < sfpModelsArrayList.size(); i++) {
            sfpModelsByMakeArray[i] = sfpModelsArrayList.get(i);
        }
        return sfpModelsByMakeArray;
    }

    @Override
    public void addSFPMake(String make) {

    }

    @Override
    public void removeSFPMake(String make) {

    }

    @Override
    public void addSFPModel(String make, String model) {
        Element newSFPModel = myDoc.createElement("model");
        newSFPModel.setTextContent(model);
        NodeList sfpModelNodeList = myDoc.getElementsByTagName("sfp_models");
        for (int i = 0; i < sfpModelNodeList.getLength(); i++) {
            if (((Element) sfpModelNodeList.item(i)).getAttribute("make").equals(make)){
                sfpModelNodeList.item(i).appendChild(newSFPModel);
            }
        }
        XML_ReadWrite.updateFile(path, myDoc);
    }

    @Override
    public void removeSFPModel(String make, String model) {
        Element sfpModelToRemove = myDoc.createElement("model");
        sfpModelToRemove.setTextContent(model);
        NodeList sfpModelNodeList = myDoc.getElementsByTagName("sfp_models");
        for (int i = 0; i < sfpModelNodeList.getLength(); i++) {
            if (((Element) sfpModelNodeList.item(i)).getAttribute("make").equals(make)){
                sfpModelNodeList.item(i).removeChild(sfpModelToRemove);
            }
        }
        XML_ReadWrite.updateFile(path, myDoc);
    }

}
