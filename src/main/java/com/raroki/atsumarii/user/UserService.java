package com.raroki.atsumarii.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    /**
     * Get all users
     */
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    /**
     * Get user by ID
     */
    @Transactional(readOnly = true)
    public Optional<UserDto> getUserById(Integer userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto);
    }

    /**
     * Create a new user
     */
    public UserDto createUser(UserDto userDto) {
        // Validate required fields
        if (userDto.getUsername() == null || userDto.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + userDto.getEmail());
        }

        // Check if username already exists
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + userDto.getUsername());
        }

        // Set creation timestamp
        userDto.setCreatedat(LocalDateTime.now());
        userDto.setUserid(null); // Ensure auto-generation

        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    /**
     * Update an existing user
     */
    public UserDto updateUser(Integer userId, UserDto userDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Validate email uniqueness (if changed)
        if (!existingUser.getEmail().equals(userDto.getEmail()) && 
            userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + userDto.getEmail());
        }

        // Validate username uniqueness (if changed)
        if (!existingUser.getUsername().equals(userDto.getUsername()) && 
            userRepository.existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + userDto.getUsername());
        }

        // Update fields
        userMapper.updateEntityFromDto(userDto, existingUser);
        existingUser.setUpdatedat(LocalDateTime.now());

        User savedUser = userRepository.save(existingUser);
        return userMapper.toDto(savedUser);
    }

    /**
     * Delete a user
     */
    public void deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }
}