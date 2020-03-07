package com.medsko.recipes.converters;

import com.medsko.recipes.TestValues;
import com.medsko.recipes.commands.DirectionStepCommand;
import com.medsko.recipes.model.DirectionStep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class DirectionStepToDirectionStepCommandTest {

	private DirectionStepToDirectionStepCommand directionStepToDirectionStepCommand;

	@BeforeEach
	void setUp() {
		directionStepToDirectionStepCommand = new DirectionStepToDirectionStepCommand();
	}

	@Test
	void testNull() {
		assertNull(directionStepToDirectionStepCommand.convert(null));
	}

	@Test
	void testConvert() {
		DirectionStep directionStep = new DirectionStep();
		directionStep.setId(TestValues.DIRECTION_STEP_ID);
		directionStep.setStepNumber(TestValues.STEP_NUMBER);
		directionStep.setDescription(TestValues.DESCRIPTION);

		DirectionStepCommand command = directionStepToDirectionStepCommand.convert(directionStep);

		assertNotNull(command);
		assertEquals(TestValues.DIRECTION_STEP_ID, command.getId());
		assertEquals(TestValues.STEP_NUMBER, command.getStepNumber());
		assertEquals(TestValues.DESCRIPTION, command.getDescription());
	}
}