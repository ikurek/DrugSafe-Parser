package logic;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import static utils.Constants.FILE_NAME;
import static utils.Constants.FILE_URL;


public class FileDownloader {

    public void validateFilePresence() {
        System.out.println("    ==> Validating presence of " + FILE_NAME);
        File f = new File(FILE_NAME);
        if (f.exists() && !f.isDirectory()) {
            System.out.println("    ==> " + FILE_NAME + " file was found, attempting remove...");
            boolean isDeleted = f.delete();

            if (isDeleted) {
                System.out.println("    ==> Old " + FILE_NAME + " file was removed succesfully!");
            } else {
                System.out.println("    ==> Old " + FILE_NAME + " file couldn't be removed!");
                System.exit(0);
            }
        } else {
            System.out.println("    ==> " + FILE_NAME + " file was not found!");
        }
    }

    public void downloadDrugListAsXML() {
        System.out.println("    ==> Attempting XML download...");

        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(FILE_URL).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);

            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }

            bufferedInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("    ==> XML File downloaded!");
    }

    public void getDownloadedFileInfo() {
        File f = new File(FILE_NAME);
        System.out.println("    ==> File data:");
        System.out.println("        ==> Name: " + f.getName());
        System.out.println("        ==> Path: " + f.getAbsolutePath());
        System.out.println("        ==> Size: " + f.length() + "b");
        System.out.println("        ==> Readable: " + f.canRead());
    }

}
