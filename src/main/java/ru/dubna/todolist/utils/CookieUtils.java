package ru.dubna.todolist.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ru.dubna.todolist.models.auth.dtos.CookieInfoDto;

@Component
public class CookieUtils {
	@Value("${auth.cookie.hmac-key}")
	private String secretKey;

	public String createToken(CookieInfoDto cookieInfo) {
		return cookieInfo.getId() + "&" + cookieInfo.getUsername() + "&" + calculateHmac(cookieInfo);
	}

	public String calculateHmac(CookieInfoDto cookieInfo) {
		byte[] secretKeyBytes = Objects.requireNonNull(secretKey).getBytes(StandardCharsets.UTF_8);
		byte[] valueBytes = (cookieInfo.getId() + "&" + cookieInfo.getUsername()).getBytes(StandardCharsets.UTF_8);

		try {
			Mac mac = Mac.getInstance("HmacSHA512");
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA512");
			mac.init(secretKeySpec);
			byte[] hmacBytes = mac.doFinal(valueBytes);

			return Base64.getEncoder().encodeToString(hmacBytes);
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new RuntimeException(e);
		}
	}
}
