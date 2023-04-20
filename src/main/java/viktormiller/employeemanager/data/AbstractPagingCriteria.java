package viktormiller.employeemanager.data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class AbstractPagingCriteria {
    @NotNull
    private String sort = "id";

    @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String direction = "ASC";

    @Min(1)
    private int page = 1;

    @Min(1)
    @Max(100)
    private int perPage = 10;

    public String getSort() {
        return this.sort;
    }

    public AbstractPagingCriteria setSort(String sort) {
        this.sort = sort;
        return this;
    }

    public String getDirection() {
        return this.direction;
    }

    public AbstractPagingCriteria setDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public int getPage() {
        return this.page;
    }

    public AbstractPagingCriteria setPage(int page) {
        this.page = page;
        return this;
    }

    public int getPerPage() {
        return this.perPage;
    }

    public AbstractPagingCriteria setPerPage(int perPage) {
        this.perPage = perPage;
        return this;
    }

    public Pageable getPagable() {
        return PageRequest.of(this.page - 1, this.perPage,
                direction.equals("ASC") ? Sort.by(Sort.Direction.ASC, sort) : Sort.by(Sort.Direction.DESC, sort));
    }
}
