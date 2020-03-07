package com.medsko.recipes.converters;

import com.medsko.recipes.commands.NotesCommand;
import com.medsko.recipes.model.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class NotesCommandToNotesTest {

	private final static Long ID = 1L;
	private final static String RECIPE_NOTES = "Just like grandma made 'em";

	private NotesCommandToNotes notesCommandToNotes;

	@BeforeEach
	void setUp() {
		notesCommandToNotes = new NotesCommandToNotes();
	}

	@Test
	void testNull() {
		assertNull(notesCommandToNotes.convert(null));
	}

	@Test
	void convert() {
		NotesCommand command = new NotesCommand();
		command.setId(ID);
		command.setRecipeNotes(RECIPE_NOTES);

		Notes notes = notesCommandToNotes.convert(command);

		assertNotNull(notes);
		assertEquals(ID, notes.getId());
		assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
	}
}