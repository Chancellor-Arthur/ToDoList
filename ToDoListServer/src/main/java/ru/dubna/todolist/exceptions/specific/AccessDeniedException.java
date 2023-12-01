package ru.dubna.todolist.exceptions.specific;

import org.springframework.http.HttpStatus;

import ru.dubna.todolist.exceptions.ApplicationException;

public class AccessDeniedException extends ApplicationException {
	public AccessDeniedException(String reason) {
		super(HttpStatus.FORBIDDEN, reason);
	}
}
