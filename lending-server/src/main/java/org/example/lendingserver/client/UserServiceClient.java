package org.example.lendingserver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/api/v1/users/{id}")
    Map<String, Object> getUserById(@PathVariable String id);

    @GetMapping("/api/v1/users")
    List<Map<String, Object>> getAllUsers();
}
