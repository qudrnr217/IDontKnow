package com.idk.api.main.domain.repository;

import com.idk.api.main.domain.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long>, DataRepositoryCustom {
}
