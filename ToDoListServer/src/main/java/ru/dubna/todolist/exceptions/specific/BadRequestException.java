package ru.dubna.todolist.exceptions.specific;

import org.springframework.http.HttpStatus;

import ru.dubna.todolist.exceptions.ApplicationException;

public class BadRequestException extends ApplicationException {
	public BadRequestException(String reason) {
		super(HttpStatus.BAD_REQUEST, reason);
	}
}
