package com.auth.jwt.security;

import com.auth.jwt.DTO.RequestDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "admin-paths")
public class RouteValidator {
    private List<RequestDTO> paths;

    public List<RequestDTO> getPaths() {
        return paths;
    }

    public void setPaths(List<RequestDTO> paths) {
        this.paths = paths;
    }

    public boolean isAdmin(RequestDTO requestDTO) {
        return paths.stream().anyMatch(p ->
            Pattern.matches(p.getUri(), requestDTO.getUri())
                    && p.getMethod().equals(requestDTO.getMethod()));
    }
}
