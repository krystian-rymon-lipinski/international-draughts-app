package com.krystianrymonlipinski.main_frame;

import draughts.library.movemodel.Hop;
import draughts.library.movemodel.Move;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MoveFileManager {

    private static File relativePath;
    private final static File filePath = new File("../move.txt");

/*
    public Move<? extends Hop> loadMove() {

    }
*/
    public String loadStringFromFile() {
        String moveString = "";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            moveString = bufferedReader.readLine();
            bufferedReader.close();
            System.out.println(moveString);

        } catch(IOException ex) {
            ex.printStackTrace();
        }

        return moveString;
    }
}
