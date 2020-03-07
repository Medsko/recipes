package com.medsko.recipes.converters;

import com.medsko.recipes.commands.LabelCommand;
import com.medsko.recipes.model.Label;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LabelCommandToLabel implements Converter<LabelCommand, Label> {

	@Synchronized
	@Nullable
	@Override
	public Label convert(LabelCommand source) {
		if (source == null) {
			return null;
		}

		Label label = new Label();
		label.setId(source.getId());
		label.setName(source.getName());

		return label;
	}
}
