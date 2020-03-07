package com.medsko.recipes.converters;

import com.medsko.recipes.commands.LabelCommand;
import com.medsko.recipes.model.Label;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LabelToLabelCommand implements Converter<Label, LabelCommand> {

	@Synchronized
	@Nullable
	@Override
	public LabelCommand convert(Label source) {
		if (source == null) {
			return null;
		}

		LabelCommand command = new LabelCommand();
		command.setId(source.getId());
		command.setName(source.getName());

		return command;
	}
}
