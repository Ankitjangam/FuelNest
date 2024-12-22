package com.FuelNest.Application.Repository;

import com.FuelNest.Application.Model.DeliveryMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryManRepository extends JpaRepository<DeliveryMan,Long> {
    Optional<DeliveryMan> findByEmail(String email);

    boolean existsByEmail(String email);
}
