package au.mlc.copy.files;

import java.io.*;
import java.nio.file.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class CopyFilesRunner {

    public void copyAsBuiltReports() {

        File asBuiltReport;
        String filePath;
        String destinationString;

        for (int drop = 2; drop <= 8; drop = drop + 2) {

            for (int level = 8; level <= 66; level++) {

                if (level < 10) {
                    destinationString = String.format("T:/QUALITY/FACADE DATABASE ( FINAL )/4b Works (As-built reports)/DROP %d/D%dL0%d/ScanPrint report/", drop, drop, level);
                    filePath = String.format("T:/QUALITY/SCANPRINT REPORTS/DROP %d/D%dL0%d/As-built Report", drop, drop, level);
                } else {
                    destinationString = String.format("T:/QUALITY/FACADE DATABASE ( FINAL )/4b Works (As-built reports)/DROP %d/D%dL%d/ScanPrint report/", drop, drop, level);
                    filePath = String.format("T:/QUALITY/SCANPRINT REPORTS/DROP %d/D%dL%d/As-built Report", drop, drop, level);
                }

                //System.out.println(destinationString + "    " + filePath);

                try {
                    File sourceFilePath = new File(filePath);

                    File[] listOfFiles = sourceFilePath.listFiles();

                    //System.out.println(listOfFiles[listOfFiles.length-1].getName());

                    String lastFolder = listOfFiles[listOfFiles.length - 1].getName();

                    filePath = String.format("%s/%s", filePath, lastFolder);

                    sourceFilePath = new File(filePath);
                    asBuiltReport = sourceFilePath.listFiles()[0]; // the As-built report pdf file

                    try {
                        Files.createDirectories(Paths.get(destinationString));
                    } catch (IOException en) {
                        System.out.println(en);
                    }

                    try {
                        Files.copy(asBuiltReport.toPath(), new File(destinationString + asBuiltReport.getName()).toPath());
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                } catch (NullPointerException e) {
                    System.out.println(filePath + " not found");
                }
            }
        }
    }


    public static void main(String[] args) {
        CopyFilesRunner copyFilesRunner = new CopyFilesRunner();
        copyFilesRunner.copyAsBuiltReports();
    }
}
