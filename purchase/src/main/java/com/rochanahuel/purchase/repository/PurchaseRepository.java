package com.rochanahuel.purchase.repository;

import com.rochanahuel.purchase.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

    List<Purchase> findByUserName(String userName);
}
