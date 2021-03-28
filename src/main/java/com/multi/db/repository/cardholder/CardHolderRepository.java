package com.multi.db.repository.cardholder;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.db.model.cardholder.CardHolder;

public interface CardHolderRepository extends JpaRepository<CardHolder, Long> {
}
