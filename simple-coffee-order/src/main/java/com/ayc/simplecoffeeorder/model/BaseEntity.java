package com.ayc.simplecoffeeorder.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "createdAt", "updatedAt"})
@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreatedDate
    @Column(updatable = false)
    OffsetDateTime createdAt;

    @LastModifiedDate
    OffsetDateTime updatedAt;

    @PrePersist
    void onCreate() {
        setCreatedAt(OffsetDateTime.now());
    }

    @PreUpdate
    void onPersist() {
        setUpdatedAt(OffsetDateTime.now());
    }
}
