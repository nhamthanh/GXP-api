package com.example.demo.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entity.GPXEntity;

public interface GpxRespository extends PagingAndSortingRepository<GPXEntity, Long> {

    Page<GPXEntity> findAll(Pageable pageable);;

}
