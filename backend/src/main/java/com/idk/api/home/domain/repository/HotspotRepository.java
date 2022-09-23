package com.idk.api.home.domain.repository;

import com.idk.api.home.domain.entity.Hotspot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotspotRepository extends JpaRepository<Hotspot, Long> {
    Optional<Hotspot> findTopByOrderByCreatedAtDesc();
}
