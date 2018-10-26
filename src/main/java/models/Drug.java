package models;

import java.util.ArrayList;

public class Drug {
    Long id; // id
    String name; // nazwaProduktu
    String commonName; // nazwaPowszechnieStosowana
    String drugType; // rodzajPreparatu
    String ammountOfSubstance; // moc
    String type; // postac
    String owner; // podmiotOdpowiedzialny
    String procedureType; // typProcedury
    Long permissionNumber; // numerPozwolenia
    String permissionExpiry; //waznoscPozwolenia
    String atc; // kodATC
    ArrayList<Substance> substances = new ArrayList<>();
    ArrayList<Package> packaging = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmmountOfSubstance() {
        return ammountOfSubstance;
    }

    public void setAmmountOfSubstance(String ammountOfSubstance) {
        this.ammountOfSubstance = ammountOfSubstance;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getProcedureType() {
        return procedureType;
    }

    public void setProcedureType(String procedureType) {
        this.procedureType = procedureType;
    }

    public Long getPermissionNumber() {
        return permissionNumber;
    }

    public void setPermissionNumber(Long permissionNumber) {
        this.permissionNumber = permissionNumber;
    }

    public String getPermissionExpiry() {
        return permissionExpiry;
    }

    public void setPermissionExpiry(String permissionExpiry) {
        this.permissionExpiry = permissionExpiry;
    }

    public String getAtc() {
        return atc;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }

    public ArrayList<Substance> getSubstances() {
        return substances;
    }

    public void setSubstances(ArrayList<Substance> substances) {
        this.substances = substances;
    }

    public ArrayList<Package> getPackaging() {
        return packaging;
    }

    public void setPackaging(ArrayList<Package> packaging) {
        this.packaging = packaging;
    }
}
