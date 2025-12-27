package com.example.deploypracticeapi.model;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@ParameterObject
@Getter
@Setter
@ToString
public class Pagenation {
    @Min(1)
    private Integer size;
    @Min(1)
    private Integer page;

    private String sortField;
    private String sortDirection = "DESC";

    public Pagenation(Integer size, Integer page) {
        this.page = (page != null) ? page : 1;
        this.size = (size != null) ? size : 10;
    }

    public PageRequest generatePageable() {
        int safePage = (this.page == null || this.page < 1) ? 0 : this.page - 1;
        int safeSize = (this.size == null || this.size < 1) ? 10 : this.size;

        if (sortField == null || sortField.trim().isEmpty()) {
            return PageRequest.of(safePage, safeSize);
        }

        try {
            return PageRequest.of(safePage, safeSize,
                    Sort.by(Sort.Direction.fromString(sortDirection), sortField.trim()));
        } catch (Exception e) {
            return PageRequest.of(safePage, safeSize);
        }
    }
}
