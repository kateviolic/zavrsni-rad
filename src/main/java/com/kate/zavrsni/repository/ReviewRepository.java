package com.kate.zavrsni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.kate.zavrsni.model.Review;

@Repository
@Component
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
