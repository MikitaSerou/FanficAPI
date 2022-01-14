package com.example.fanficapi.service.impl;

import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.exception.UserException;
import com.example.fanficapi.model.Role;
import com.example.fanficapi.model.User;
import com.example.fanficapi.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private final User johnDoe = User
            .builder()
            .username("JohnDoe")
            .email("JohnDoe@gmail.com")
            .password("123456Y!")
            .birthDate(LocalDate.now())
            .roles(Set.of(new Role(RoleName.ROLE_USER))).build();

    private final User marieSue = User
            .builder()
            .username("MarieSue")
            .email("MarieSue@gmail.com")
            .password("1234589H!")
            .birthDate(LocalDate.now())
            .roles(Set.of(new Role(RoleName.ROLE_USER), new Role(RoleName.ROLE_ADMIN))).build();

    private final User bryceWayne = User
            .builder()
            .username("BryceWayne")
            .email("ImBatman@bat.gotham")
            .password("7987976546798178Uia88")
            .birthDate(LocalDate.now())
            .roles(Set.of(new Role(RoleName.ROLE_USER), new Role(RoleName.ROLE_ADMIN))).build();

    private final List<User> allUsers = List.of(johnDoe, marieSue, bryceWayne);
    private final User mockUser = mock(User.class);


    @DisplayName("Save new user")
    @Test
    void saveNewToDBTest() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(johnDoe)).thenReturn(johnDoe);
        userService.saveToDB(johnDoe);

        verify(userRepository, times(1)).save(johnDoe);
        verify(userRepository, times(1)).existsByUsername(johnDoe.getUsername());
        verify(userRepository, times(1)).existsByEmail(johnDoe.getEmail());
    }

    @DisplayName("Save new user with existing username")
    @Test
    void saveNewToDBWithExistenceUserNameTest() {
        when(userRepository.existsByUsername(johnDoe.getUsername())).thenReturn(true);
        when(userRepository.existsByEmail(johnDoe.getEmail())).thenReturn(false);
        when(userRepository.save(johnDoe)).thenReturn(johnDoe);
        assertThrows(UserException.class, () -> userService.saveToDB(johnDoe));

        verify(userRepository, times(0)).save(johnDoe);
        verify(userRepository, times(1)).existsByUsername(johnDoe.getUsername());
        verify(userRepository, times(0)).existsByEmail(johnDoe.getEmail());
    }

    @DisplayName("Save new user with existing email")
    @Test
    void saveNewToDBWithExistenceEmailTest() {
        when(userRepository.existsByUsername(johnDoe.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(johnDoe.getEmail())).thenReturn(true);
        when(userRepository.save(johnDoe)).thenReturn(johnDoe);
        assertThrows(UserException.class, () -> userService.saveToDB(johnDoe));

        verify(userRepository, times(0)).save(johnDoe);
        verify(userRepository, times(1)).existsByUsername(johnDoe.getUsername());
        verify(userRepository, times(1)).existsByEmail(johnDoe.getEmail());
    }

    @DisplayName("Save new user with existing username & email")
    @Test
    void saveNewToDBWithExistenceUsernameAndEmailTest() {
        when(userRepository.existsByUsername(johnDoe.getUsername())).thenReturn(true);
        when(userRepository.existsByEmail(johnDoe.getEmail())).thenReturn(true);
        when(userRepository.save(johnDoe)).thenReturn(johnDoe);
        assertThrows(UserException.class, () -> userService.saveToDB(johnDoe));

        verify(userRepository, times(0)).save(johnDoe);
        verify(userRepository, times(1)).existsByUsername(johnDoe.getUsername());
        verify(userRepository, times(0)).existsByEmail(johnDoe.getEmail());
    }

    @DisplayName("Find existence user by ID")
    @Test
    void findByIdTest() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(johnDoe));
        userService.findById(1L);

        verify(userRepository, times(1)).findById(1L);
    }

    @DisplayName("Find not existence user by ID")
    @Test
    void findById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserException.class, () -> userService.findById(1L));

        verify(userRepository, times(1)).findById(1L);
    }

    @DisplayName("Find user by username")
    @Test
    void findByUsernameTest() {
        when(userRepository.findByUsername("JohnDoe")).thenReturn(Optional.of(johnDoe));
        userService.findByUsername("JohnDoe");

        verify(userRepository, times(1)).findByUsername("JohnDoe");
    }

    @DisplayName("FindUser by not existence username")
    @Test
    void findByNotExistenceUsernameTest() {
        when(userRepository.findByUsername("JohnDoe")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userService.findByUsername("JohnDoe"));

        verify(userRepository, times(1)).findByUsername("JohnDoe");
    }

    @DisplayName("Find by email")
    @Test
    void findByEmailTest() {
        when(userRepository.findByEmail("JohnDoe@gmail.com")).thenReturn(Optional.of(johnDoe));
        userService.findByEmail("JohnDoe@gmail.com");

        verify(userRepository, times(1)).findByEmail(johnDoe.getEmail());
    }

    @DisplayName("Find by not existence email")
    @Test
    void findByNotExistenceEmailTest() {
        when(userRepository.findByEmail("JohnDoe@gmail.com")).thenReturn(Optional.empty());
        assertThrows(UserException.class, () -> userService.findByEmail(johnDoe.getEmail()));

        verify(userRepository, times(1)).findByEmail(johnDoe.getEmail());
    }

    @DisplayName("Update user")
    @Test
    void updateTest() {
        when(mockUser.getId()).thenReturn(1L);
        when(userRepository.existsById(mockUser.getId())).thenReturn(true);
        when(userRepository.save(mockUser)).thenReturn(mockUser);
        userService.update(mockUser);

        verify(userRepository, times(1)).save(mockUser);
        verify(userRepository, atLeast(1)).existsById(mockUser.getId());
        verify(mockUser, atLeast(1)).getId();
    }

    @DisplayName("Update user without ID)")
    @Test
    void updateUserWithNullIdTest() {
        when(mockUser.getId()).thenReturn(null);
        when(userRepository.save(mockUser)).thenReturn(mockUser);
        assertThrows(UserException.class, () -> userService.update(mockUser));

        verify(userRepository, times(0)).save(mockUser);
        verify(userRepository, times(0)).existsById(mockUser.getId());
        verify(mockUser, atLeast(1)).getId();
    }

    @DisplayName("Update user with ID whois not exist in DB")
    @Test
    void updateUserWithIdWhichNotExistInDbTest() {
        when(mockUser.getId()).thenReturn(457L);
        when(userRepository.save(mockUser)).thenReturn(mockUser);
        when(userRepository.existsById(457L)).thenReturn(false);
        assertThrows(UserException.class, () -> userService.update(mockUser));

        verify(userRepository, times(0)).save(mockUser);
        verify(userRepository, times(1)).existsById(anyLong());
        verify(mockUser, atLeast(1)).getId();
    }

    @DisplayName("Delete user by ID")
    @Test
    void deleteByIdTest() {
        when(mockUser.getId()).thenReturn(1L);
        userService.deleteById(mockUser.getId());

        verify(mockUser, atLeast(1)).getId();
        verify(userRepository, times(1)).deleteById(mockUser.getId());
    }

    @DisplayName("Find all users")
    @Test
    void findAllTest() {
        when(userRepository.findAll()).thenReturn(allUsers);
        assertThat(userService.findAll(), hasSize(3));
        assertThat(userService.findAll(), is(allUsers));

        verify(userRepository, times(2)).findAll();
    }

    @DisplayName("Check user existence by username")
    @Test
    void existsByUsernameTest() {
        when(userRepository.existsByUsername("JohnDoe")).thenReturn(true);
        Assertions.assertTrue(userService.existsByUsername("JohnDoe"));

        verify(userRepository, times(1)).existsByUsername("JohnDoe");
    }

    @DisplayName("Check user existence by username (not found)")
    @Test
    void existsByNotExistenceUsernameTest() {
        when(userRepository.existsByUsername("JohnDoe")).thenReturn(false);
        Assertions.assertFalse(userService.existsByUsername("JohnDoe"));

        verify(userRepository, times(1)).existsByUsername("JohnDoe");
    }

    @DisplayName("Check user existence by email")
    @Test
    void existsByEmailTest() {
        when(userRepository.existsByEmail("ImBatman@bat.gotham")).thenReturn(true);
        Assertions.assertTrue(userService.existsByEmail("ImBatman@bat.gotham"));

        verify(userRepository, times(1)).existsByEmail("ImBatman@bat.gotham");
    }

    @DisplayName("Check user existence by email (not found)")
    @Test
    void existsByNotExistenceEmailTest() {
        when(userRepository.existsByEmail("ImBatman@bat.gotham")).thenReturn(false);
        Assertions.assertFalse(userService.existsByEmail("ImBatman@bat.gotham"));

        verify(userRepository, times(1)).existsByEmail("ImBatman@bat.gotham");
    }
}
