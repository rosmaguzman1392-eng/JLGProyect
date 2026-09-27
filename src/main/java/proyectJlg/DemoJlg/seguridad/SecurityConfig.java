package proyectJlg.DemoJlg.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final RoleAuthenticationSuccessHandler successHandler;

    public SecurityConfig(RoleAuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .authorizeHttpRequests(auth -> auth

                // Páginas públicas
                .requestMatchers(
                    "/",
                    "/login",
                    "/registro",
                    "/css/**",
                    "/js/**",
                    "/img/**"
                ).permitAll()

                // Solo ADMIN
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")

                // Solo USER
                .requestMatchers("/user/**")
                .hasRole("USER")

                // Todo lo demás requiere autenticación
                .anyRequest()
                .authenticated()
            )

            // Configuración del login
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler(successHandler)
                .permitAll()
            )

            // Configuración del logout
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}