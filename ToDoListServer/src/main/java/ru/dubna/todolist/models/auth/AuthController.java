package ru.dubna.todolist.models.auth;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Stream;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.dubna.todolist.config.security.CookieAuthenticationFilter;
import ru.dubna.todolist.exceptions.dtos.BadRequestExceptionPayload;
import ru.dubna.todolist.exceptions.dtos.DefaultExceptionPayload;
import ru.dubna.todolist.models.auth.dtos.CookieInfoDto;
import ru.dubna.todolist.models.auth.dtos.CredentialsDto;
import ru.dubna.todolist.models.auth.dtos.UserInputDto;
import ru.dubna.todolist.models.user.dtos.UserOutputDto;
import ru.dubna.todolist.utils.CookieUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Авторизация/Регистрация", description = "Позволяет авторизоваться/зарегистрироваться пользователю в системе")
@ApiResponse(responseCode = "400", content = {
		@Content(schema = @Schema(implementation = BadRequestExceptionPayload.class)) })
public class AuthController {
	private final AuthService authService;
	private final CookieUtils cookieUtils;

	private void createCookie(CookieInfoDto cookieInfo, HttpServletResponse servletResponse) {
		Cookie authCookie = new Cookie(CookieAuthenticationFilter.cookieName, cookieUtils.createToken(cookieInfo));
		authCookie.setHttpOnly(true);
		authCookie.setSecure(true);
		authCookie.setMaxAge((int) Duration.of(1, ChronoUnit.DAYS).toSeconds());
		authCookie.setPath("/");

		servletResponse.addCookie(authCookie);
	}

	private void deleteCookie(HttpServletResponse servletResponse) {
		Cookie authCookie = new Cookie(CookieAuthenticationFilter.cookieName, "");
		authCookie.setHttpOnly(true);
		authCookie.setSecure(true);
		authCookie.setMaxAge(0);
		authCookie.setPath("/");

		servletResponse.addCookie(authCookie);
	}

	@PostMapping("/login")
	@Operation(summary = "Авторизация пользователя", description = "Позволяет пользователю авторизоваться в системе")
	@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CookieInfoDto.class)) })
	@ApiResponse(responseCode = "401", content = {
			@Content(schema = @Schema(implementation = DefaultExceptionPayload.class)) })
	public CookieInfoDto signIn(@Valid @RequestBody CredentialsDto credentials, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		Optional<Cookie> cookieAuth = Stream.of(Optional.ofNullable(servletRequest.getCookies()).orElse(new Cookie[0]))
				.filter(cookie -> CookieAuthenticationFilter.cookieName.equals(cookie.getName())).findFirst();

		CookieInfoDto cookieInfo = authService.signIn(credentials);
		if (cookieAuth.isEmpty()) {
			createCookie(cookieInfo, servletResponse);
		}
		return cookieInfo;
	}

	@PostMapping("/logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Выход пользователя из системы")
	@ApiResponse(responseCode = "204")
	public void signOut(HttpServletResponse servletResponse) {
		deleteCookie(servletResponse);
	}

	@PostMapping("/registration")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Регистрация пользователя", description = "Позволяет пользователю зарегистрироваться в системе")
	@ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = UserOutputDto.class)) })
	public UserOutputDto signUp(@Valid @RequestBody UserInputDto userInputDto) {
		return authService.signUp(userInputDto);
	}
}
