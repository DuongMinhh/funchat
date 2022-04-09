package com.duongminh.funchat.core.model;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QueryParam {

    private Integer pageNumber = 0;
    private Integer pageSize = 25;
    
    private String sortBy = "id";
    private String dir = "DESC";
    
    public Pageable getPageable() {
        if (dir.toUpperCase().equals("DESC")) {
            return PageRequest.of(pageNumber, pageSize, Sort.by(this.sortBy).descending());
        } else {
            return PageRequest.of(pageNumber, pageSize, Sort.by(this.sortBy).ascending());
        }
    }
    
}
