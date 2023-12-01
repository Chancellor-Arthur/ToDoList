package ru.dubna.todolist.models.auth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CookieInfoDto {
	private int id;

	private String username;
}
