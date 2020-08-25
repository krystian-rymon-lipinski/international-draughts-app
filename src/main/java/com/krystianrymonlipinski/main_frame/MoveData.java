package com.krystianrymonlipinski.main_frame;

import java.util.ArrayList;

public class MoveData {

    private int source;
    private int destination;
    private ArrayList<Integer> takenPawns;

    public MoveData(int source, int destination, ArrayList<Integer> takenPawns) {
        this.source = source;
        this.destination = destination;
        this.takenPawns = takenPawns;
    }

    public MoveData() {
        takenPawns = new ArrayList<>();
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public ArrayList<Integer> getTakenPawns() {
        return takenPawns;
    }

    public void setTakenPawns(ArrayList<Integer> takenPawns) {
        this.takenPawns = takenPawns;
    }

    public void addTakenPawn(int takenPawn) {
        takenPawns.add(takenPawn);
    }

    @Override
    public String toString() {
        return "MoveData{" +
                "source=" + source +
                ", destination=" + destination +
                ", takenPawns=" + takenPawns +
                '}';
    }
}
