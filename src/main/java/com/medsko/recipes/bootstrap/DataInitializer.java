package com.medsko.recipes.bootstrap;

import com.medsko.recipes.model.Difficulty;
import com.medsko.recipes.model.Label;
import com.medsko.recipes.model.Recipe;
import com.medsko.recipes.model.UnitOfMeasure;
import com.medsko.recipes.model.builders.RecipeBuilder;
import com.medsko.recipes.repositories.LabelRepository;
import com.medsko.recipes.repositories.RecipeRepository;
import com.medsko.recipes.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@Slf4j
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

	private final LabelRepository labelRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final RecipeRepository recipeRepository;

	public DataInitializer(LabelRepository labelRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
		this.labelRepository = labelRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.recipeRepository = recipeRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initializeSpicyGrilledChicken();
		initializeGuacamole();
	}

	private void initializeSpicyGrilledChicken() {

		RecipeBuilder builder = new RecipeBuilder();

		Recipe spicyGrilledChickenTacos = builder.magicFill("Spicy grilled chicken tacos", Difficulty.MODERATE,
				20, 15, "simplyrecipes.com",
				"https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/")
			.withIngredient(new BigDecimal(2), createOrRetrieveUnitOfMeasure("tablespoon"), "ancho chili powder")
			.withIngredient(new BigDecimal(1), createOrRetrieveUnitOfMeasure("teaspoon"), "dried oregano")
			.withIngredient(new BigDecimal(1), createOrRetrieveUnitOfMeasure("teaspoon"), "dried cumin")
			.withIngredient(new BigDecimal(1), createOrRetrieveUnitOfMeasure("teaspoon"), "sugar")
			.withIngredient(new BigDecimal(0.5), createOrRetrieveUnitOfMeasure("teaspoon"), "salt")
			.withIngredient(new BigDecimal(1), createOrRetrieveUnitOfMeasure("clove of"), "garlic")
			.withIngredient(new BigDecimal(1), createOrRetrieveUnitOfMeasure("tablespoon"), "finely grated orange zest")
			.withIngredient(new BigDecimal(2), createOrRetrieveUnitOfMeasure("tablespoon"), "fresh-squeezed orange juice")
			.withIngredient(new BigDecimal(2), createOrRetrieveUnitOfMeasure("tablespoon"), "olive oil")
			.withIngredient(new BigDecimal(5), null, "skinless, boneless chicken thighs")
			.withIngredient(new BigDecimal(8), null, "small corn tortillas")
			.withIngredient(new BigDecimal(3), createOrRetrieveUnitOfMeasure("cups"), "packed baby arugula")
			.withIngredient(new BigDecimal(2), null, "medium ripe avocados, sliced")
			.withIngredient(new BigDecimal(4), null, "radishes, thinly sliced")
			.withIngredient(new BigDecimal(0.5), createOrRetrieveUnitOfMeasure("pint"), "cherry tomatoes, halved")
			.withIngredient(new BigDecimal(0.25), null, "red onion, thinly sliced")
			.withIngredient(null, null, "Roughly chopped cilantro")
			.withIngredient(new BigDecimal(0.5), createOrRetrieveUnitOfMeasure("cup"), "sour cream")
			.withIngredient(new BigDecimal(0.25), createOrRetrieveUnitOfMeasure("cup"), "milk")
			.withIngredient(new BigDecimal(1), null, "lime, cut into wedges")
			.withDirectionStep("Prepare a gas or charcoal grill for medium-high, direct heat.")
			.withDirectionStep("Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, " +
					"oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make " +
					"a loose paste. Add the chicken to the bowl and toss to coat all over.")
			.withDirectionStep("Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer " +
					"inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.")
			.withDirectionStep("Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over " +
					"medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it " +
					"with tongs and heat for a few seconds on the other side.\n\n" +
					"Wrap warmed tortillas in a tea towel to keep them warm until serving.")
			.withDirectionStep("Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small " +
					"handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. " +
					"Drizzle with the sour cream (thinned with the milk). Serve with lime wedges.")
			.build();

		String[] spicyGrilledChickenTacosLabels = new String[] {"Mexican"};

		Stream.of(spicyGrilledChickenTacosLabels)
				.map(this::createOrRetrieveLabel)
				.forEach(spicyGrilledChickenTacos::addLabel);

		recipeRepository.save(spicyGrilledChickenTacos);

		log.info("Initialized the spicy grilled chicken recipe!");
	}

	private void initializeGuacamole() {

		RecipeBuilder builder = new RecipeBuilder();

		final Recipe guac = builder.magicFill("Perfect guacamole", Difficulty.EASY, 10, null, "simplyrecipes.com",
				"https://www.simplyrecipes.com/recipes/perfect_guacamole/")
		.withIngredient(new BigDecimal(2), createOrRetrieveUnitOfMeasure(""), "ripe avocados")
		.withIngredient(new BigDecimal(0.25), createOrRetrieveUnitOfMeasure("teaspoon"), "salt")
		.withIngredient(new BigDecimal(1), createOrRetrieveUnitOfMeasure("tablespoon"), "fresh lime or lemon juice")
		.withIngredient(new BigDecimal(2), createOrRetrieveUnitOfMeasure("tablespoon"), "minced red onion")
		.withIngredient(new BigDecimal(2), createOrRetrieveUnitOfMeasure(""), "serrano chiles, stems and seeds removed, minced")
		.withIngredient(new BigDecimal(2), createOrRetrieveUnitOfMeasure("tablespoon"), "cilantro (leaves and tender stems), finely chopped")
		.withIngredient(null, createOrRetrieveUnitOfMeasure("dash"), "freshly grated black pepper")
		.withIngredient(new BigDecimal(0.5), createOrRetrieveUnitOfMeasure(""), "ripe tomato, seeds and pulp removed, chopped")
		.withDirectionStep("Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. " +
				"Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. " +
				"(See How to Cut and Peel an Avocado.) Place in a bowl.")
		.withDirectionStep("Mash with a fork: Using a fork, roughly mash the avocado. " +
				"(Don't overdo it! The guacamole should be a little chunky.)")
		.withDirectionStep("Add salt, lime juice, and the rest: Sprinkle with salt and lime " +
				"(or lemon) juice. The acid in the lime juice will provide some balance to the richness of the " +
				"avocado and will help delay the avocados from turning brown.\n\n" +
				"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in " +
				"their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired " +
				"degree of hotness.\n\n" +
				"Remember that much of this is done to taste because of the variability in the fresh ingredients. " +
				"Start with this recipe and adjust to your taste.\n\n" +
				"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, " +
				"add it just before serving.")
		.withDirectionStep("Serve: Serve immediately, or if making a few hours ahead, place " +
				"plastic wrap on the surface of the guacamole and press down to cover it and to prevent air " +
				"reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) " +
				"Refrigerate until ready to serve.")
		.build();

		String[] guacLabels = new String[] {"Side dish", "Dip", "Mexican"};
		Stream.of(guacLabels)
				.map(this::createOrRetrieveLabel)
				.forEach(label -> guac.getLabels().add(label));

		recipeRepository.save(guac);

		log.info("Initialized the guacamole recipe!");
	}

	private Label createOrRetrieveLabel(String name) {
		// First check if the label already exists, and if so, return the existing entity.
		Optional<Label> optionalLabel = labelRepository.findByName(name);
		if (optionalLabel.isPresent()) return optionalLabel.get();
		Label label = new Label();
		label.setName(name);
		log.debug("Label " + label.getName() + " will be saved as new label.");
		return labelRepository.save(label);
	}

	private UnitOfMeasure createOrRetrieveUnitOfMeasure(String description) {
		Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByDescription(description);
		if (optionalUnitOfMeasure.isPresent()) {
			return optionalUnitOfMeasure.get();
		}
		UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setDescription(description);
		log.debug("Unit of measure " + unitOfMeasure.getDescription() + " will be saved as new unit of measure.");
		return unitOfMeasureRepository.save(unitOfMeasure);
	}

}
