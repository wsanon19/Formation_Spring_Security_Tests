package com.polytech.apishop.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polytech.apishop.Entities.categories;

@Repository
public interface categoriesRepository extends JpaRepository<categories,Integer>{
    
}
