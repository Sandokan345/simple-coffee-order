package com.ayc.simplecoffeeorder.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoffeeRecipe extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1289408704231273917L;

    @ManyToOne
    @JoinColumn(name = "coffee_id")
    Coffee coffee;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;

    Double quantity;
}
