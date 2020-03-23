package com.medsko.recipes.services;

import com.medsko.recipes.commands.UnitOfMeasureCommand;
import com.medsko.recipes.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.medsko.recipes.model.UnitOfMeasure;
import com.medsko.recipes.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UnitOfMeasureServiceImplTest {

	@Mock
	UnitOfMeasureRepository repository;

	@Spy
	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();

	@InjectMocks
	UnitOfMeasureServiceImpl service;

	@Test
	void listUnitOfMeasureCommands() {
		List<UnitOfMeasure> unitsOfMeasure = new ArrayList<>();
		unitsOfMeasure.add(new UnitOfMeasure());
		unitsOfMeasure.add(new UnitOfMeasure());

		when(repository.findAll()).thenReturn(unitsOfMeasure);

		List<UnitOfMeasureCommand> returnedUomCommands = service.listUnitOfMeasureCommands();

		assertEquals(unitsOfMeasure.size(), returnedUomCommands.size());
		verify(repository).findAll();
	}
}