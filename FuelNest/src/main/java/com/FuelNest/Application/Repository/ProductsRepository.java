package com.FuelNest.Application.Repository;


import com.FuelNest.Application.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
    @Override
    Optional<Products> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);
}
