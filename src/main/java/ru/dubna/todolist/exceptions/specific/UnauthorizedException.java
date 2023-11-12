package ru.dubna.todolist.exceptions.specific;

import org.springframework.http.HttpStatus;
import ru.dubna.todolist.exceptions.ApplicationException;

public class UnauthorizedException extends ApplicationException {
	public UnauthorizedException(String message) {
		super(HttpStatus.UNAUTHORIZED, message);
	}
}
