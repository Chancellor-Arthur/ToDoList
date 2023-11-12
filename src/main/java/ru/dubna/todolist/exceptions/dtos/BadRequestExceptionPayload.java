package ru.dubna.todolist.exceptions.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestExceptionPayload extends DefaultExceptionPayload {
	private List<Error> errors;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Error {
		private List<String> codes;

		private ArrayList<Object> arguments;

		private String defaultMessage;

		private String objectName;

		private String field;

		private String rejectedValue;

		private boolean bindingFailure;

		private String code;
	}
}
