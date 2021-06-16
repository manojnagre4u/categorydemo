package com.omnirio.category.Repository;

import com.omnirio.category.model.CategoryAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryAttributesRepository extends JpaRepository<CategoryAttributes, Long> {
}
