package viktormiller.employeemanager.data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class EmployeeCriteria extends AbstractPagingCriteria {
    @NotNull
    @Pattern(regexp = "id|t.name|t.jobTitle")
    private String sort = "id";

    @Min(1)
    @Max(100)
    private int perPage = 5;

    private String query = "";

    public String getQuery() {
        return this.query;
    }

    public EmployeeCriteria setQuery(String query) {
        this.query = query;
        return this;
    }
}
