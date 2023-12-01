package ru.dubna.todolist.exceptions.specific;

import org.springframework.http.HttpStatus;

import ru.dubna.todolist.exceptions.ApplicationException;

public class NotFoundException extends ApplicationException {
	public NotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
}
