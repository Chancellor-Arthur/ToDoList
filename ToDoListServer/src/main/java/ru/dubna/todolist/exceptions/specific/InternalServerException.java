package ru.dubna.todolist.exceptions.specific;

import org.springframework.http.HttpStatus;

import ru.dubna.todolist.exceptions.ApplicationException;

public class InternalServerException extends ApplicationException {
	public InternalServerException(String message) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}
}
