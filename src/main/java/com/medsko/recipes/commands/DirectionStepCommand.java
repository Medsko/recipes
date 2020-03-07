package com.medsko.recipes.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DirectionStepCommand {
	private Long id;
	private Integer stepNumber;
	private String description;
}
