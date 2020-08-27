package com.krystianrymonlipinski.main_frame;

import java.io.*;

public class MoveFileManager {

    private final static File filePath = new File("../move.txt");


    public MoveData loadMoveData() {
        String moveString = loadStringFromFile();
        return parseStringToMoveData(moveString);
    }

    public String loadStringFromFile() {
        String moveString = "";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            moveString = bufferedReader.readLine();
            bufferedReader.close();

        } catch(IOException ex) {
            ex.printStackTrace();
        }

        return moveString;
    }

    public MoveData parseStringToMoveData(String moveString) {
        String[] tilesStrings = moveString.split(",");

        MoveData moveData = new MoveData();

        moveData.setSource(Integer.parseInt(tilesStrings[0]));
        moveData.setDestination(Integer.parseInt(tilesStrings[1]));

        if (tilesStrings.length > 2) {
            String[] takenPawnsStrings = tilesStrings[2].split("x");

            for (int i=0; i<takenPawnsStrings.length; i++) {
                moveData.addTakenPawn(Integer.parseInt(takenPawnsStrings[i]));
            }
        }
        return moveData;
    }

    public void writeMoveResult(boolean wasMoveLegal) {
        String result = wasMoveLegal ? "1" : "0";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(result);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
