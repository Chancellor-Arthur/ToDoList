package ru.dubna.todolist.entities.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dubna.todolist.entities.auth.dtos.UserInputDto;
import ru.dubna.todolist.exceptions.specific.NotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public User create(UserInputDto userInputDto) {
		User user = new User(userInputDto.getUsername(), passwordEncoder.encode(userInputDto.getPassword()));
        return userRepository.save(user);
	}

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getByUsername(username);

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority("ALL")));
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User getByUsername(String username) {
		return findByUsername(username)
				.orElseThrow(() -> new NotFoundException(String.format("Пользователь '%s' не найден", username)));
	}
}
