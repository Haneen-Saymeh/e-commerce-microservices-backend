package com.haninz.microservices.walletservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haninz.microservices.walletservice.models.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {

}
