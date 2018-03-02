package au.mlc.copy.files;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class CopyFilesRunner {

    private int copyNdiSupportingDocumentation(int filesCounter) {
        String filesPath, destinationPath, destinationCovermeter, destinationPotential, destionationVisual, destinationDrummy;
        File sourceFilePath, fileITP, fileCover, fileVisual, fileDrummy = null, newFileITP, newFileDrummy, newFileVisual, newFileCover, newFilePotential, newFile;
        File[] destinationFolderContent;
        File[] listOfFiles;
        List<File> filesPotential;

        for (int drop = 2; drop <= 8; drop = drop + 2) {

            for (int level = 8; level <= 66; level++) {

                if (level < 10) {
                    destinationPath = String.format("T:/QUALITY/FACADE DATABASE ( FINAL )/4a Schedule of all repairs (NDI reports)/DROP %d/D%dL0%d/Inspection and Test Plan/", drop, drop, level);
                    filesPath = String.format("T:/DOCUMENTS/From 2000 to 4999 = WORKS/From 3000 to 3050 = Repair Works/3000 = ITP, Tower NDI/Results/Drop %d/D%d L%d/", drop, drop, level);
                } else {
                    destinationPath = String.format("T:/QUALITY/FACADE DATABASE ( FINAL )/4a Schedule of all repairs (NDI reports)/DROP %d/D%dL%d/Inspection and Test Plan/", drop, drop, level);
                    filesPath = String.format("T:/DOCUMENTS/From 2000 to 4999 = WORKS/From 3000 to 3050 = Repair Works/3000 = ITP, Tower NDI/Results/Drop %d/D%d L%d/", drop, drop, level);
                }
                destinationCovermeter = destinationPath + "Q-CL-004 Base of Spandrel Covermeter Survey/";
                destinationPotential = destinationPath + "Q-CL-009 Potential mapping results/";
                destinationDrummy = destinationPath + "Q-REC-012 Drummy and low cover survey/";
                destionationVisual = destinationPath + "Q-REC-012 Visual Inspection/";

                destinationFolderContent = new File(destinationPath).listFiles();
                try {
                    for (File file : destinationFolderContent) {
                        if (file.isDirectory()) {
                            for (File fileInFolder : file.listFiles()) {
                                fileInFolder.delete();
                            }
                        }
                        file.delete();
                    }
                } catch (NullPointerException e) {
                    System.out.println(String.format("D%dL%d folder 'Inspection and Test Plan' found empty or not existing", drop, level));
                }

                try {
                    Files.createDirectories(Paths.get(destinationCovermeter));
                    Files.createDirectories(Paths.get(destinationPotential));
                    Files.createDirectories(Paths.get(destionationVisual));
                    Files.createDirectories(Paths.get(destinationDrummy));
                } catch (IOException en) {
                    System.out.println(en);
                }

                sourceFilePath = new File(filesPath);

                try {
                    listOfFiles = sourceFilePath.listFiles();

                    if (listOfFiles.length == 0) {
                        System.out.println("empty source folder");
                    }
                    filesPotential = new ArrayList<>();
                    fileDrummy = null;
                    for (File file : listOfFiles) {

                        if (file.getName().contains("ITP")) {
                            fileITP = file;
                            if (fileITP.isDirectory()) {
                                for (File fileInFolder : fileITP.listFiles()) {
                                    if (fileInFolder.getName().contains("NDI") && fileInFolder.getName().endsWith(".pdf")) {
                                        fileITP = fileInFolder;
                                    } else if (fileInFolder.getName().endsWith(".pdf")) {
                                        fileITP = fileInFolder;
                                    }
                                }
                            }
                            try {
                                newFileITP = new File(destinationPath + fileITP.getName());
                                Files.copy(fileITP.toPath(), newFileITP.toPath());
                                Files.move(newFileITP.toPath(), newFileITP.toPath().resolveSibling(String.format("D%dL%d - NDI's Inspection and Test Plan.pdf", drop, level)));
                                filesCounter++;
                            } catch (Exception e) {
                                System.out.println(e + String.format("D%dL%d ITP file problem", drop, level));
                            }
                        }
                        if (file.getName().contains("base")) {
                            fileCover = file;
                            if (fileCover.isDirectory()) {
                                for (File fileInFolder : fileCover.listFiles()) {
                                    if (fileInFolder.getName().contains(String.format("D%dL%d", drop, level)) && fileInFolder.getName().endsWith(".pdf")) {
                                        fileCover = fileInFolder;
                                    } else if (fileInFolder.getName().endsWith(".pdf")) {
                                        fileCover = fileInFolder;
                                    }
                                }
                            }
                            try {
                                newFileCover = new File(destinationCovermeter + fileCover.getName());
                                Files.copy(fileCover.toPath(), newFileCover.toPath());
                                Files.move(newFileCover.toPath(), newFileCover.toPath().resolveSibling(String.format("D%dL%d - Q-CL-004 Base of Spandrel Covermeter Survey.pdf", drop, level)));
                                filesCounter++;
                            } catch (Exception e) {
                                System.out.println(e + String.format("D%dL%d CoverMeter file problem", drop, level));
                            }

                        }
                        if (file.getName().contains("night")) {
                            fileDrummy = file;
                            if (fileDrummy.isDirectory()) {
                                for (File fileInFolder : fileDrummy.listFiles()) {
                                    if (fileInFolder.getName().endsWith(".pdf")) {
                                        fileDrummy = fileInFolder;
                                    }
                                }
                            }
                            try {
                                newFileDrummy = new File(destinationDrummy + fileDrummy.getName());
                                Files.copy(fileDrummy.toPath(), newFileDrummy.toPath());
                                Files.move(newFileDrummy.toPath(), newFileDrummy.toPath().resolveSibling(String.format("D%dL%d - Q-REC-012 Drummy and Low Cover Survey.pdf", drop, level)));
                                filesCounter++;
                            } catch (Exception e) {
                                System.out.println(e + String.format("D%dL%d Drummy file problem", drop, level));
                            }
                        }
                        if (file.getName().contains("visual")) {
                            fileVisual = file;
                            if (fileVisual.isDirectory()) {
                                for (File fileInFolder : fileVisual.listFiles()) {
                                    if (fileInFolder.getName().endsWith(".pdf")) {
                                        fileVisual = fileInFolder;
                                    }
                                }
                            }
                            try {
                                newFileVisual = new File(destionationVisual + fileVisual.getName());
                                Files.copy(fileVisual.toPath(), newFileVisual.toPath());
                                Files.move(newFileVisual.toPath(), newFileVisual.toPath().resolveSibling(String.format("D%dL%d - Q-REC-012 Visual Inspection.pdf", drop, level)));
                                filesCounter++;
                            } catch (Exception e) {
                                System.out.println(e + String.format("D%dL%d Visual file problem", drop, level));
                            }
                        }
                        if (file.getName().contains("corro") || file.getName().contains("otential")) {
                            if (file.isDirectory()) {
                                for (File fileInFolder : file.listFiles()) {
                                    if (fileInFolder.getName().endsWith(".pdf")) {
                                        filesPotential.add(fileInFolder);
                                    }
                                }
                                if (filesPotential.size() == 0) {
                                    for (File fileInFolder : file.listFiles()) {
                                        if (fileInFolder.getName().contains("TRANS")) {
                                            for (File fileInsideTrans : fileInFolder.listFiles()) {
                                                if (fileInsideTrans.getName().endsWith(".pdf")) {
                                                    filesPotential.add(fileInsideTrans);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (filesPotential.size() == 0) {
                                    System.out.println(String.format("D%dL%d is missing potential mapping results <<<-----", drop, level));
                                }
                            } else {
                                filesPotential.add(file);
                            }
                        }
                    }
                    for (File filePotential : filesPotential) {
                        try {
                            newFilePotential = new File(destinationPotential + filePotential.getName());
                            Files.copy(filePotential.toPath(), newFilePotential.toPath());
                            if (filesPotential.size() == 1) {
                                Files.move(newFilePotential.toPath(), newFilePotential.toPath().resolveSibling(String.format("D%dL%d - Q-CL-009 Potential mapping results.pdf", drop, level)));
                            } else {
                                Files.move(newFilePotential.toPath(), newFilePotential.toPath().resolveSibling(String.format("D%dL%d - Q-CL-009 Potential mapping results (%s).pdf", drop, level, filePotential.getName())));
                            }
                            filesCounter++;
                        } catch (Exception e) {
                            System.out.println(e + String.format("D%dL%d Potential file problem", drop, level));
                        }
                    }
                    System.out.println(fileDrummy);
                    if (fileDrummy == null) {
                        newFile = new File(String.format("%sPlease refer to D%dL%d Q-REC-012 Visual Inspection", destinationDrummy, drop, level));
                        try {
                            newFile.createNewFile();
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println(e);
                }
            }
        }
        return filesCounter;
    }

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
                    System.out.println(String.format("D%dL%d folder 'Inspection and Test Plan' found empty or not existing", drop, level));
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
        long startMillis = System.currentTimeMillis();
        CopyFilesRunner copyFilesRunner = new CopyFilesRunner();
        int filesCounter = 0;
/*        System.out.println("Starting to copy As-built ScanPrint Reports...");
        filesCounter = copyFilesRunner.copyAsBuiltReports(filesCounter);
        System.out.println("Starting to copy As-built supporting documentation...");
        filesCounter = copyFilesRunner.copyAsBuiltSupportingDocumentation(filesCounter);*/
        System.out.println("Starting to copy NDI's supporting documentation...");
        filesCounter = copyFilesRunner.copyNdiSupportingDocumentation(filesCounter);
        long endMillis = System.currentTimeMillis();
        System.out.println(String.format("Complete, files copied: %d, elapsed time: %d s", filesCounter, (endMillis-startMillis)/1000 ));
    }
}
