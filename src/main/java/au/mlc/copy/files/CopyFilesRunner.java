package au.mlc.copy.files;

import java.io.*;
import java.nio.file.*;

public class CopyFilesRunner {

    public int copyAsBuiltSupportingDocumentation(int filesCounter) {

        int countFilesInFolder;
        String filesPath, destinationPath, plantRoomSuffix;
        File sourceFilePath;
        File[] destinationFolderContent;
        File[] listOfFiles,ndiForCompletedWorksFiles;
        Path path;

        for (int drop = 2; drop <= 8; drop = drop + 2) {

            for (int level = 8; level <= 66; level++) {

                if (level < 10) {
                    destinationPath = String.format("T:/QUALITY/FACADE DATABASE ( FINAL )/4b Works (As-built reports)/DROP %d/D%dL0%d/Inspection and Test Plan/", drop, drop, level);
                    filesPath = String.format("T:/QUALITY/SCANPRINT REPORTS/DROP %d/D%dL0%d/Scanned documents", drop, drop, level);
                } else {
                    destinationPath = String.format("T:/QUALITY/FACADE DATABASE ( FINAL )/4b Works (As-built reports)/DROP %d/D%dL%d/Inspection and Test Plan/", drop, drop, level);
                    filesPath = String.format("T:/QUALITY/SCANPRINT REPORTS/DROP %d/D%dL%d/Scanned documents", drop, drop, level);
                }

                destinationFolderContent = new File(destinationPath).listFiles();
                try {
                    for (File file : destinationFolderContent) {
                        file.delete();
                    }
                } catch (NullPointerException e) {
                    System.out.println(String.format("D%dL%d foldr 'Inspection and Test Plan' found empty or not existing", drop, level));
                }

                try {
                    sourceFilePath = new File(filesPath);

                    listOfFiles = sourceFilePath.listFiles();

                    // check if contains NDI folder, if yes, extract file from it and add to list of files
                    for (File file : listOfFiles) {
                        if (file.toString().contains("NDI")) {
                            ndiForCompletedWorksFiles = new File(file.getPath()).listFiles();
                            for (File ndiFile : ndiForCompletedWorksFiles) {
                                if (ndiFile.getName().endsWith(".pdf")) {
                                    try {
                                        path = Paths.get(ndiFile.getPath());
                                        Files.move(path, path.resolveSibling(String.format("D%dL%d - NDI's for completed works.pdf", drop, level)));
                                        listOfFiles[listOfFiles.length - 1] = ndiFile;
                                    } catch (IOException e) {
                                        System.out.println(e + String.format(" troubles renaming NDI file at D%sL%s", drop, level));
                                    }
                                }
                            }

                        }
                    }

                    try {
                        Files.createDirectories(Paths.get(destinationPath));
                    } catch (IOException en) {
                        System.out.println(en);
                    }
                    for (File file : listOfFiles) {
                        try {

                            Files.copy(file.toPath(), new File(destinationPath + file.getName()).toPath());
                            filesCounter++;

                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    }

                    // cleanup the file names
                    countFilesInFolder = 1;
                    for (File file : new File(destinationPath).listFiles()) {
                        plantRoomSuffix = "";
                        try {
                            if (file.getName().contains("P ") || file.getName().contains("P_")) {
                                plantRoomSuffix = "P";
                            } else if (file.getName().contains("art 1")) {
                                plantRoomSuffix = " Part 1";
                            } else if (file.getName().contains("art 2")) {
                                plantRoomSuffix = " Part 2";
                            } else if (file.getName().contains("art 3")) {
                                plantRoomSuffix = " Part 3";
                            }
                            if (file.getName().contains("NDI")) {
                                Files.move(file.toPath(), file.toPath().resolveSibling(String.format("%d - D%dL%d%s - NDI's for completed works.pdf", countFilesInFolder, drop, level, plantRoomSuffix)));
                            } else if (file.getName().contains("ITP")) {
                                Files.move(file.toPath(), file.toPath().resolveSibling(String.format("%d - D%dL%d%s - Inspection and Test Plan.pdf", countFilesInFolder, drop, level, plantRoomSuffix)));
                            } else if (file.getName().contains("Q-REC") && file.getName().contains("emeas")) {
                                Files.move(file.toPath(), file.toPath().resolveSibling(String.format("%d - D%dL%d%s - Q-REC-008 as-built records and remeasure.pdf", countFilesInFolder, drop, level, plantRoomSuffix)));
                            } else if (file.getName().contains("Q-REC") && !file.getName().contains("emeas")) {
                                Files.move(file.toPath(), file.toPath().resolveSibling(String.format("%d - D%dL%d%s - Q-REC-008 as-built records.pdf", countFilesInFolder, drop, level, plantRoomSuffix)));
                            } else if (file.getName().contains("emeas") && !file.getName().contains("Q-REC")) {
                                Files.move(file.toPath(), file.toPath().resolveSibling(String.format("%d - D%dL%d%s - Remeasure.pdf", countFilesInFolder, drop, level, plantRoomSuffix)));
                            } else if (file.getName().contains("nag")) {
                                Files.move(file.toPath(), file.toPath().resolveSibling(String.format("%d - D%dL%d%s - Snag list.pdf", countFilesInFolder, drop, level, plantRoomSuffix)));
                            }
                        } catch (IOException e) {
                            System.out.println(e + String.format(" troubles renaming NDI file at D%sL%s", drop, level, plantRoomSuffix));
                        }
                        countFilesInFolder++;
                    }


                } catch (NullPointerException e) {
                    System.out.println(filesPath + " not found");
                }
            }
        }
        return filesCounter;
    }

    public int copyAsBuiltReports(int filesCounter) {

        String filePath;
        String destinationString;
        File sourceFilePath;
        File asBuiltReport;
        File[] listOfFiles;
        File[] destinationFolderContent;

        for (int drop = 2; drop <= 8; drop = drop + 2) {

            for (int level = 8; level <= 66; level++) {

                if (level < 10) {
                    destinationString = String.format("T:/QUALITY/FACADE DATABASE ( FINAL )/4b Works (As-built reports)/DROP %d/D%dL0%d/ScanPrint report/", drop, drop, level);
                    filePath = String.format("T:/QUALITY/SCANPRINT REPORTS/DROP %d/D%dL0%d/As-built Report", drop, drop, level);
                } else {
                    destinationString = String.format("T:/QUALITY/FACADE DATABASE ( FINAL )/4b Works (As-built reports)/DROP %d/D%dL%d/ScanPrint report/", drop, drop, level);
                    filePath = String.format("T:/QUALITY/SCANPRINT REPORTS/DROP %d/D%dL%d/As-built Report", drop, drop, level);
                }

                destinationFolderContent = new File(destinationString).listFiles();
                try {
                    for (File file : destinationFolderContent) {
                        file.delete();
                    }
                } catch (NullPointerException e) {
                    System.out.println(String.format("D%dL%d folder ScanPrint report found empty or not existing", drop, level));
                }

                //System.out.println(destinationString + "    " + filePath);

                try {
                    sourceFilePath = new File(filePath);

                    listOfFiles = sourceFilePath.listFiles();

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
                        filesCounter++;
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                } catch (NullPointerException e) {
                    System.out.println(filePath + " not found");
                }
            }
        }
        return filesCounter;
    }


    public static void main(String[] args) {
        CopyFilesRunner copyFilesRunner = new CopyFilesRunner();
        int filesCounter = 0;
        System.out.println("Starting to copy As-built ScanPrint Reports...");
        long startMillis = System.currentTimeMillis();
        filesCounter = copyFilesRunner.copyAsBuiltReports(filesCounter);
        System.out.println("Starting to copy As-built supporting documentation...");
        filesCounter = copyFilesRunner.copyAsBuiltSupportingDocumentation(filesCounter);
        long endMillis = System.currentTimeMillis();
        System.out.println(String.format("Complete, files copied: %d, elapsed time: %d s", filesCounter, (endMillis-startMillis)/1000 ));
    }
}
