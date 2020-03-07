package com.medsko.recipes.converters;

import com.medsko.recipes.commands.UnitOfMeasureCommand;
import com.medsko.recipes.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnitOfMeasureCommandToUnitOfMeasureTest {

	private final static Long ID = 1L;
	private final static String DESCRIPTION = "teaspoon";

	private UnitOfMeasureCommandToUnitOfMeasure converter;

	@BeforeEach
	void setUp() {
		converter = new UnitOfMeasureCommandToUnitOfMeasure();
	}

	@Test
	void testNull() {
		assertNull(converter.convert(null));
	}

	@Test
	void testConvert() {
		UnitOfMeasureCommand command = new UnitOfMeasureCommand();
		command.setId(ID);
		command.setDescription(DESCRIPTION);

		UnitOfMeasure uom = converter.convert(command);
		assertNotNull(uom);
		assertEquals(ID, uom.getId());
		assertEquals(DESCRIPTION, uom.getDescription());
	}
}