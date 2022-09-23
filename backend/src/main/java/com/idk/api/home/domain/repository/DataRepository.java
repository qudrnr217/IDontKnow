package com.idk.api.home.domain.repository;

import com.idk.api.home.domain.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long>, DataRepositoryCustom {
}
