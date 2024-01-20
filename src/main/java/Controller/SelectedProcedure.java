package Controller;

import Class.Procedure;

// singleton class to restrict user from creating multiple object of this class
public class SelectedProcedure {
    private static SelectedProcedure instance = null;
    private Procedure procedure;

    private SelectedProcedure() {
    }

    public static SelectedProcedure getInstance() {
        if (instance == null) {
            instance = new SelectedProcedure();
        }
        return instance;
    }

    public Procedure getProcedure() {
        return this.procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }
}
