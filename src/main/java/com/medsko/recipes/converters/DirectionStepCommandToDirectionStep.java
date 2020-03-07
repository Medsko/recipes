package com.medsko.recipes.converters;

import com.medsko.recipes.commands.DirectionStepCommand;
import com.medsko.recipes.model.DirectionStep;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DirectionStepCommandToDirectionStep implements Converter<DirectionStepCommand, DirectionStep> {

	@Synchronized
	@Nullable
	@Override
	public DirectionStep convert(DirectionStepCommand source) {

		if (source == null) {
			return null;
		}

		DirectionStep directionStep = new DirectionStep();
		directionStep.setId(source.getId());
		directionStep.setStepNumber(source.getStepNumber());
		directionStep.setDescription(source.getDescription());

		return directionStep;
	}
}
