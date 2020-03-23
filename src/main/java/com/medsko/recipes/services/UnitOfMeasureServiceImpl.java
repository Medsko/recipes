package com.medsko.recipes.services;

import com.medsko.recipes.commands.UnitOfMeasureCommand;
import com.medsko.recipes.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.medsko.recipes.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private final UnitOfMeasureRepository repository;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository repository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		this.repository = repository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}

	@Override
	public List<UnitOfMeasureCommand> listUnitOfMeasureCommands() {
		return StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(unitOfMeasureToUnitOfMeasureCommand::convert)
				.collect(Collectors.toList());
	}
}
