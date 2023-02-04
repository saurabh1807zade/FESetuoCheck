package com.hackathon.javatemplate.auth.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paging {

    private Boolean hasMore = true;
    private Integer page;
    private Integer pageSize;
    private Integer totalCount;

   public Paging(Integer page, Integer pageSize, Integer totalCount){

        this.page = page;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        if ((page+1)*pageSize >= totalCount){
            this.hasMore = false;
        }

    }
}
