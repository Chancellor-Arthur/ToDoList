package ru.dubna.todolist.config.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected int id;

	@CreatedDate
	@Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false, updatable = false)
	protected OffsetDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	protected OffsetDateTime updatedAt;

	@PrePersist
	protected void prePersist() {
		if (createdAt == null)
			createdAt = OffsetDateTime.now();
		if (updatedAt == null)
			updatedAt = OffsetDateTime.now();
	}

	@PreUpdate
	protected void preUpdate() {
		updatedAt = OffsetDateTime.now();
	}
}
