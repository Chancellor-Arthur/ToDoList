package ru.dubna.todolist.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ApplicationException extends ResponseStatusException {
	public ApplicationException(HttpStatusCode status, String reason) {
		super(status, reason);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
