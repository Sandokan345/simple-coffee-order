package com.ayc.simplecoffeeorder.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
@Table(name = "coffee")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Coffee extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -5763473152179891139L;

    String name;

    Double price;
}
