package api.model;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.ToString;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.PageRequest;

@ParameterObject
@Getter
@ToString
public class Pagenation {
    @Min(1)
    @Parameter(description = "페이지에 노출되는 목록 개수")
    private Integer size;
    @Min(1)
    @Parameter(description = "현재 페이지 (웹용)")
    private Integer page;

    public Pagenation(Integer size, Integer page) {
        this.page = (page != null) ? page : 1;
        this.size = (size != null) ? size : 0;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
    public PageRequest generatePageable() {
        return PageRequest.of(this.page-1, this.size);
    }
}
