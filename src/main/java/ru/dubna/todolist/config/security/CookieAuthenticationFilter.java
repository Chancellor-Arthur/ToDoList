package ru.dubna.todolist.config.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.dubna.todolist.utils.CookieUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class CookieAuthenticationFilter extends OncePerRequestFilter {
	public final static String cookieName = "JSESSIONID";

	private final CookieUtils cookieUtils;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		Optional<Cookie> cookieAuth = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
				.filter(cookie -> cookieName.equals(cookie.getName())).findFirst();

		if (cookieAuth.isPresent()) {
			String[] parts = cookieAuth.get().getValue().split("&");

			String username = parts[0];
			String hmac = parts[1];

			if (hmac.equals(cookieUtils.calculateHmac(username))
					&& SecurityContextHolder.getContext().getAuthentication() == null) {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null,
						Collections.singletonList(new SimpleGrantedAuthority("ALL")));
				SecurityContextHolder.getContext().setAuthentication(token);
			}
		}

		filterChain.doFilter(request, response);
	}
}
