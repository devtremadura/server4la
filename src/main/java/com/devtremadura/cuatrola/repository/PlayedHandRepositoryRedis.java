package com.devtremadura.cuatrola.repository;

import com.devtremadura.cuatrola.domain.PlayedHand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayedHandRepositoryRedis extends CrudRepository<PlayedHand, String> {


}
