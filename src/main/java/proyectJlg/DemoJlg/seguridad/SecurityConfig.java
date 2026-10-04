package proyectJlg.DemoJlg.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

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
    public SessionRegistry sessionRegistry() {
       return new SessionRegistryImpl();
}

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
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
             // NUEVO: Manejo de acceso denegado (Error 403)
            .exceptionHandling(exception -> exception
            .accessDeniedPage("/login?accesoDenegado")
            )
            // Configuración de la sesión
            .sessionManagement(session -> session
            .invalidSessionUrl("/login?invalid")  // Sesión caducó por tiempo
            .maximumSessions(1)                   // Solo 1 sesión activa por usuario
            .maxSessionsPreventsLogin(false)      // Si entra en otro lado, expulsa al anterior
            .expiredUrl("/login?expired")         // Sesión expulsada por otro login
            .sessionRegistry(sessionRegistry())   // Registro de sesiones activas
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