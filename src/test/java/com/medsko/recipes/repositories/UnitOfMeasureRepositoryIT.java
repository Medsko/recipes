package com.medsko.recipes.repositories;

import com.medsko.recipes.model.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository repository;

	@Test
	void findByDescription() {
		String description = "teaspoon";
		Optional<UnitOfMeasure> optionalUnitOfMeasure = repository.findByDescription(description);
		assertTrue(optionalUnitOfMeasure.isPresent());
		assertEquals(description, optionalUnitOfMeasure.get().getDescription());
	}

}