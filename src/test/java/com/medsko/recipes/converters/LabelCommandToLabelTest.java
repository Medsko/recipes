package com.medsko.recipes.converters;

import com.medsko.recipes.commands.LabelCommand;
import com.medsko.recipes.model.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class LabelCommandToLabelTest {

	private final static Long ID = 1L;
	private final static String NAME = "Mexican";

	private LabelCommandToLabel labelCommandToLabel;

	@BeforeEach
	void setUp() {
		labelCommandToLabel = new LabelCommandToLabel();
	}

	@Test
	void testNull() {
		assertNull(labelCommandToLabel.convert(null));
	}

	@Test
	void testConvert() {
		LabelCommand command = new LabelCommand();
		command.setId(ID);
		command.setName(NAME);

		Label label = labelCommandToLabel.convert(command);
		assertNotNull(label);
		assertEquals(ID, label.getId());
		assertEquals(NAME, label.getName());
	}
}