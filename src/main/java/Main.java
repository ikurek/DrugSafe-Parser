import logic.FileDownloader;
import logic.FileParser;
import models.Drug;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        System.out.println("==> Running DrugSafe-Parser 0.1");
        System.out.println("==> Building required objects...");

        FileDownloader fileDownloader = new FileDownloader();
        FileParser fileParser = new FileParser();
        ArrayList<Drug> listOfDrugs = new ArrayList<>();

        System.out.println("==> Objects ready!");
        System.out.println("==> Stage I: Getting data from remote");

        fileDownloader.validateFilePresence();
        fileDownloader.downloadDrugListAsXML();
        fileDownloader.getDownloadedFileInfo();

        System.out.println("==> Stage II: Parsing XML file");
        fileParser.prepareDocument();
        fileParser.parseDocument();

        System.out.println("==> Stage III: Doing whatever You want");
        listOfDrugs = fileParser.getListOfDrugs();
        System.out.println("    ==> Main method received " + listOfDrugs.size() + " elements of type Drug");

        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
