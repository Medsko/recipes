package com.medsko.recipes.repositories;

import com.medsko.recipes.model.Label;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LabelRepository extends CrudRepository<Label, Long> {

	Optional<Label> findByName(String name);
}
