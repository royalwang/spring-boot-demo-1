package com.bambrow.swagger.service;

import com.bambrow.swagger.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<Long, UserDTO> userMap = new HashMap<>();

    @PostConstruct
    public void init() {
        userMap.put(1L, new UserDTO(1L, "David", "sasquatch"));
    }

    public UserDTO get(Long id) {
        return userMap.getOrDefault(id, null);
    }

    public Collection<UserDTO> getAll() {
        return userMap.values();
    }

    public void set(Long id, UserDTO user) {
        userMap.put(id, user);
    }

    public boolean delete(Long id) {
        return userMap.remove(id) != null;
    }
}
