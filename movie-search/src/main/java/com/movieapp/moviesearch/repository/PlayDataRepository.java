package com.movieapp.moviesearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.movieapp.moviesearch.model.PlayData;

@Repository
public interface PlayDataRepository extends ElasticsearchRepository<PlayData, Integer> {

}
