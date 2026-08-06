// src/test/java/com/golzstore/springstore/users/UserServiceTest.java

package com.golzstore.springstore.users;

import com.golzstore.springstore.auth.ChangePasswordRequest;
import com.golzstore.springstore.auth.RegisterUserRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.access.AccessDeniedException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // чистый unit test, Spring не поднимается
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper userMapper;
    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService; // реальный объект с моками внутри

    // ✅ Тест: registerUser — успешная регистрация
    @Test
    void registerUser_whenEmailIsNew_shouldSaveAndReturnDto() {
        var request = new RegisterUserRequest();
        request.setEmail("test@example.com");
        request.setPassword("secret");

        var user = new User();
        user.setEmail("test@example.com");
        user.setPassword("secret");

        var dto = UserDto.builder()
                .email("test@example.com")
                .build();

        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(userMapper.toEntity(request)).thenReturn(user);
        when(passwordEncoder.encode("secret")).thenReturn("hashed_secret");
        when(userMapper.toDto(user)).thenReturn(dto);

        var result = userService.registerUser(request);

        assertThat(result.getEmail()).isEqualTo("test@example.com");
        verify(userRepository).save(user); // проверяем что save был вызван
        assertThat(user.getPassword()).isEqualTo("hashed_secret"); // пароль захешировался
        assertThat(user.getRole()).isEqualTo(Role.USER);
    }

    // ❌ Тест: registerUser — email уже занят → исключение
    @Test
    void registerUser_whenEmailAlreadyExists_shouldThrowDuplicateUserException() {
        var request = new RegisterUserRequest();
        request.setEmail("exists@example.com");

        when(userRepository.existsByEmail("exists@example.com")).thenReturn(true);

        assertThatThrownBy(() -> userService.registerUser(request))
                .isInstanceOf(DuplicateUserException.class);

        verify(userRepository, never()).save(any()); // save НЕ должен вызываться
    }

    // ❌ Тест: getUser — пользователь не найден
    @Test
    void getUser_whenUserNotFound_shouldThrowUserNotFoundException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUser(99L))
                .isInstanceOf(UserNotFoundException.class);
    }

    // ✅ Тест: changePassword — неверный старый пароль
    @Test
    void changePassword_whenOldPasswordWrong_shouldThrowAccessDeniedException() {
        var user = new User();
        user.setPassword("correct_hash");

        var request = new ChangePasswordRequest();
        request.setOldPassword("wrong_password");
        request.setNewPassword("new_password");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong_password", "correct_hash")).thenReturn(false);

        assertThatThrownBy(() -> userService.changePassword(1L, request))
                .isInstanceOf(AccessDeniedException.class);
    }

    // ✅ Тест: changePassword — новый пароль сохраняется захешированным, а не в открытом виде
    @Test
    void changePassword_whenOldPasswordCorrect_shouldStoreHashedNewPassword() {
        var user = new User();
        user.setPassword("correct_hash");

        var request = new ChangePasswordRequest();
        request.setOldPassword("old_password");
        request.setNewPassword("new_password");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("old_password", "correct_hash")).thenReturn(true);
        when(passwordEncoder.encode("new_password")).thenReturn("new_hash");

        userService.changePassword(1L, request);

        assertThat(user.getPassword()).isEqualTo("new_hash"); // не "new_password"
        verify(userRepository).save(user);
    }
}
