package Controller;

import Class.PHistory;

public class SelectedHistory {
    private static SelectedHistory instance = null;
    private PHistory patientHistory;

    // singleton class to restrict user from creating multiple object of this class
    private SelectedHistory() {
    }

    public static SelectedHistory getInstance() {
        if (instance == null) {
            instance = new SelectedHistory();
        }
        return instance;
    }

    public PHistory getHistory() {
        return patientHistory;
    }

    public void setHistory(PHistory pH) {
        this.patientHistory = pH;
    }
}
