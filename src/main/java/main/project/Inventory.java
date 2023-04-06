package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 *
 */
public class Inventory {


    private static int partId = 0;
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }


    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static int getNewPartId() {
        return ++partId;
    }


    //Part LookUp
    public static Part lookupPart(int partId) {
        Part partFound = null;

        for (Part part : allParts) {
            if (part.getId() == partId) {
                partFound = part;
            }
        }

        return partFound;
    }

    //Part Search
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().equals(partName)) {
                partsFound.add(part);
            }
        }

        return partsFound;
    }


    public static void updatePart(int index, Part selectedPart) {

        allParts.set(index, selectedPart);
    }
}



