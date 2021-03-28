package com.multi.db.repository.card;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.db.model.card.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
