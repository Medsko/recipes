package com.medsko.recipes.converters;

import com.medsko.recipes.TestValues;
import com.medsko.recipes.commands.UnitOfMeasureCommand;
import com.medsko.recipes.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnitOfMeasureToUnitOfMeasureCommandTest {

	private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

	@BeforeEach
	void setUp() {
		unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
	}

	@Test
	void testNull() {
		assertNull(unitOfMeasureToUnitOfMeasureCommand.convert(null));
	}

	@Test
	void convert() {
		UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setId(TestValues.UNIT_OF_MEASURE_ID);
		unitOfMeasure.setDescription(TestValues.DESCRIPTION);

		UnitOfMeasureCommand command = unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure);

		assertNotNull(command);
		assertEquals(TestValues.UNIT_OF_MEASURE_ID, command.getId());
		assertEquals(TestValues.DESCRIPTION, command.getDescription());
	}
}