package ru.dubna.todolist.entities.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDto {
	private int id;

	private String username;
}
