package com.FuelNest.Application.Repository;


import com.FuelNest.Application.Model.FuelStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FuelStationRepository extends JpaRepository<FuelStation, Long> {
//    boolean existsByEmail(String email);
        Optional<FuelStation> findByEmail(String email);

    boolean existsByEmail(String email);




}
