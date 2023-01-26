package com.polytech.apishop.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.polytech.apishop.Entities.article;

@Repository
public interface articleRepository extends JpaRepository<article,Integer> {
    List<article> findAll();

    @Query("SELECT a.description FROM article a WHERE a.id_article = :id")
    String findDescription(@Param("id") Integer id_article);

}
