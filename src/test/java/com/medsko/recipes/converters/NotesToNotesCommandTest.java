package com.medsko.recipes.converters;

import com.medsko.recipes.TestValues;
import com.medsko.recipes.commands.NotesCommand;
import com.medsko.recipes.model.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class NotesToNotesCommandTest {

	private NotesToNotesCommand notesToNotesCommand;

	@BeforeEach
	void setUp() {
		notesToNotesCommand = new NotesToNotesCommand();
	}

	@Test
	void testNull() {
		assertNull(notesToNotesCommand.convert(null));
	}

	@Test
	void convert() {
		Notes notes = new Notes();
		notes.setId(TestValues.NOTES_ID);
		notes.setRecipeNotes(TestValues.RECIPE_NOTES);

		NotesCommand command = notesToNotesCommand.convert(notes);
		assertNotNull(command);
		assertEquals(TestValues.NOTES_ID, command.getId());
		assertEquals(TestValues.RECIPE_NOTES, command.getRecipeNotes());
	}
}