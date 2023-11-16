package com.haryo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haryo.model.Banner;

@Repository
public interface BannerRepository extends CrudRepository<Banner, Integer>{

}
