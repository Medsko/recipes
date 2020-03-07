package com.medsko.recipes.converters;

import com.medsko.recipes.TestValues;
import com.medsko.recipes.commands.LabelCommand;
import com.medsko.recipes.model.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class LabelToLabelCommandTest {

	private LabelToLabelCommand labelToLabelCommand;

	@BeforeEach
	void setUp() {
		labelToLabelCommand = new LabelToLabelCommand();
	}

	@Test
	void testNull() {
		assertNull(labelToLabelCommand.convert(null));
	}

	@Test
	void convert() {
		Label label = new Label();
		label.setId(TestValues.LABEL_ID);
		label.setName(TestValues.NAME);

		LabelCommand command = labelToLabelCommand.convert(label);

		assertNotNull(command);
		assertEquals(TestValues.LABEL_ID, command.getId());
		assertEquals(TestValues.NAME, command.getName());
	}
}