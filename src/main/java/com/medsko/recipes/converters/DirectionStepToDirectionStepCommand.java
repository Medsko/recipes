package com.medsko.recipes.converters;

import com.medsko.recipes.commands.DirectionStepCommand;
import com.medsko.recipes.model.DirectionStep;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DirectionStepToDirectionStepCommand implements Converter<DirectionStep, DirectionStepCommand> {

	@Synchronized
	@Nullable
	@Override
	public DirectionStepCommand convert(DirectionStep source) {
		if (source == null) {
			return null;
		}

		DirectionStepCommand command = new DirectionStepCommand();
		command.setId(source.getId());
		command.setStepNumber(source.getStepNumber());
		command.setDescription(source.getDescription());

		return command;
	}
}
