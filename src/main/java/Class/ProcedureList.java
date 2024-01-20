package Class;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcedureList {
    private List<Procedure> procedureList;

    @JsonCreator
    public ProcedureList(@JsonProperty("procedureList") List<Procedure> procedureList) {
        this.procedureList = procedureList;
    }

    public List<Procedure> getProcedureList() {
        return procedureList;
    }

    public void setProcedureList(List<Procedure> procedureList) {
        this.procedureList = procedureList;
    }
}
