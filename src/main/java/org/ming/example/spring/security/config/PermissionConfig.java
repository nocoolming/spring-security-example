package org.ming.example.spring.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "security.permissions")
public class PermissionConfig {
    private List<PermissionRule> rules;

    public List<PermissionRule> getRules() {
        return rules;
    }

    public void setRules(List<PermissionRule> rules) {
        this.rules = rules;
    }

    public static class PermissionRule {
        private String path;
        private List<String> roles;

        // getters and setters


        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }
}