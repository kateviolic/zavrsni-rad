package com.kate.zavrsni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.kate.zavrsni.model.Wine;

@Repository
@Component
public interface WineRepository extends JpaRepository<Wine, Integer>{
	
	Wine findById(int id);
	
	//List<Wine> findBy
}
