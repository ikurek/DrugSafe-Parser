package logic;

import models.Drug;
import models.Package;
import models.Substance;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

import static utils.Constants.FILE_NAME;

public class FileParser {

    // Parser elements
    DocumentBuilderFactory documentBuilderFactory;
    DocumentBuilder documentBuilder;
    Document document;

    // List storing all POJOs
    ArrayList<Drug> listOfDrugs = new ArrayList<>();

    // Temporary variables to awoid constant recreation
    Drug tempDrug;
    Package tempPackage;
    Substance tempSubstance;
    ArrayList<Substance> tempListOfSubstances;
    ArrayList<Package> tempListOfPackages;


    public FileParser() {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void prepareDocument() {
        System.out.println("    ==> Attempting to parse XML file to Document object...");

        File file = new File(FILE_NAME);

        try {
            document = documentBuilder.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("    ==> Document object created successfully!");
    }

    public void parseDocument() {
        System.out.println("    ==> Attempting to parse Document into POJOs...");
        Element root = document.getDocumentElement();
        System.out.println("        ==> Root name: " + root.getNodeName());
        System.out.println("        ==> Creation date: " + root.getAttribute("stanNaDzien"));
        NodeList productElements = root.getElementsByTagName("produktLeczniczy");
        System.out.println("        ==> Products: " + productElements.getLength());

        for (int i = 0; i < productElements.getLength(); i++) {
            parseSingleProduct(productElements.item(i));
        }

        System.out.println("    ==> Parsing Document finished successfully!");

    }

    public void parseSingleProduct(Node productNode) {
        // Validate if Node is XML element
        if (productNode.getNodeType() == Node.ELEMENT_NODE) {

            // Cast node as XML element
            Element productElement = (Element) productNode;

            // Rebuild temporary drug as empty
            tempDrug = new Drug();

            // Read basic drug data
            parseSingleDrugDataFromElement(productElement);

            // Parse substances
            parseSubstancesFromElement(productElement);

            // Parse packages
            parsePackagesFromElement(productElement);

            listOfDrugs.add(tempDrug);
        }

    }

    private void parseSingleDrugDataFromElement(Element productElement) {

        try {
            tempDrug.setPermissionNumber(Long.parseLong(productElement.getAttribute("numerPozwolenia")));
        } catch (NumberFormatException nfe) {
            tempDrug.setPermissionNumber(null);
        }

        try {
            tempDrug.setId(Long.parseLong(productElement.getAttribute("id")));
        } catch (NumberFormatException nfe) {
            tempDrug.setId(null);
        }

        tempDrug.setName(getProperAttributeValueFromElement(productElement, "nazwaProduktu"));
        tempDrug.setDrugType(getProperAttributeValueFromElement(productElement, "rodzajPreparatu"));
        tempDrug.setCommonName(getProperAttributeValueFromElement(productElement, "nazwaPowszechnieStosowana"));
        tempDrug.setAmmountOfSubstance(getProperAttributeValueFromElement(productElement, "moc"));
        tempDrug.setType(getProperAttributeValueFromElement(productElement, "postac"));
        tempDrug.setOwner(getProperAttributeValueFromElement(productElement, "podmiotOdpowiedzialny"));
        tempDrug.setProcedureType(getProperAttributeValueFromElement(productElement, "typProcedury"));
        tempDrug.setPermissionExpiry(getProperAttributeValueFromElement(productElement, "waznoscPozwolenia"));
        tempDrug.setAtc(getProperAttributeValueFromElement(productElement, "kodATC"));
    }

    private void parseSubstancesFromElement(Element productElement) {
        // Rebuild temporary variables
        tempSubstance = new Substance();
        tempListOfSubstances = new ArrayList<>();

        // Find element that represents substances node
        Element substancesNode = (Element) productElement.getElementsByTagName("substancjeCzynne").item(0);

        // Iterate over all substances
        for (int i = 0; i < substancesNode.getElementsByTagName("substancjaCzynna").getLength(); i++) {
            tempSubstance = new Substance();
            tempSubstance.setName(substancesNode.getElementsByTagName("substancjaCzynna").item(i).getTextContent());
            tempListOfSubstances.add(tempSubstance);
        }

        tempDrug.setSubstances(tempListOfSubstances);
    }

    private void parsePackagesFromElement(Element productElement) {
        // Rebuild temporary variables
        tempPackage = new Package();
        tempListOfPackages = new ArrayList<>();

        // Find element that represents substances node
        Element substancesNode = (Element) productElement.getElementsByTagName("opakowania").item(0);

        // Iterate over all substances
        for (int i = 0; i < substancesNode.getElementsByTagName("opakowanie").getLength(); i++) {
            tempPackage = new Package();
            parseSinglePackageFromElement((Element) substancesNode.getElementsByTagName("opakowanie").item(i));
            tempListOfPackages.add(tempPackage);
        }

        tempDrug.setPackaging(tempListOfPackages);
    }

    private void parseSinglePackageFromElement(Element packageElement) {
        try {
            tempPackage.setSize(Long.parseLong(packageElement.getAttribute("wielkosc")));
        } catch (NumberFormatException nfe) {
            tempPackage.setSize(null);
        }

        try {
            tempPackage.setEan(Long.parseLong(packageElement.getAttribute("kodEAN")));
        } catch (NumberFormatException nfe) {
            tempPackage.setEan(null);
        }

        try {
            tempPackage.setId(Long.parseLong(packageElement.getAttribute("id")));
        } catch (NumberFormatException nfe) {
            tempPackage.setId(null);
        }

        switch (packageElement.getAttribute("skasowane")) {
            case "NIE":
                tempPackage.setRemoved(false);
                break;

            case "TAK":
                tempPackage.setRemoved(true);
                break;

            default:
                tempPackage.setRemoved(null);
                break;
        }

        tempPackage.setSizeUnit(getProperAttributeValueFromElement(packageElement, "jednostkaWielkosci"));
        tempPackage.setCategory(getProperAttributeValueFromElement(packageElement, "kategoriaDostepnosci"));
        tempPackage.setEuNumber(getProperAttributeValueFromElement(packageElement, "numerEu"));
        tempPackage.setParallelDistributor(getProperAttributeValueFromElement(packageElement, "dystrybutorRownolegly"));
    }

    private String getProperAttributeValueFromElement(Element element, String attribute) {

        // Check if given attribute exists inside element
        if (element.hasAttribute(attribute)) {

            // Check if field is not empty
            if (!element.getAttribute(attribute).equals("") || !element.getAttribute(attribute).trim().isEmpty()) {

                // Return element if not
                return element.getAttribute(attribute);

            } else {

                return null;
            }

        } else {

            return null;
        }


    }

    public ArrayList<Drug> getListOfDrugs() {
        return listOfDrugs;
    }

}
