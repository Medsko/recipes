package com.medsko.recipes.converters;

import com.medsko.recipes.commands.DirectionStepCommand;
import com.medsko.recipes.model.DirectionStep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class DirectionStepCommandToDirectionStepTest {

	private final static Long ID = 1L;
	private final static Integer STEP_NUMBER = 1;
	private final static String DESCRIPTION = "Skin the cat";

	private DirectionStepCommandToDirectionStep directionStepCommandToDirectionStep;

	@BeforeEach
	void setUp() {
		directionStepCommandToDirectionStep = new DirectionStepCommandToDirectionStep();
	}

	@Test
	void testNull() {
		assertNull(directionStepCommandToDirectionStep.convert(null));
	}

	@Test
	void convert() {
		DirectionStepCommand command = new DirectionStepCommand();
		command.setId(ID);
		command.setDescription(DESCRIPTION);
		command.setStepNumber(STEP_NUMBER);

		DirectionStep directionStep = directionStepCommandToDirectionStep.convert(command);

		assertNotNull(directionStep);
		assertEquals(ID, directionStep.getId());
		assertEquals(STEP_NUMBER, directionStep.getStepNumber());
		assertEquals(DESCRIPTION, directionStep.getDescription());
	}

}