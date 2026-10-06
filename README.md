# Descripción del proyecto de aula.
Sistema web de gestión comercial para JLG Aislamientos Térmicos, Industriales y
hoteleros, Actualmente, JLG realiza parte importante del proceso comercial de
forma manual, especialmente la elaboración y seguimiento de cotizaciones y
cuentas de cobro. Esto genera consumo de tiempo, riesgo de errores, dificultad para
hacer seguimiento a las solicitudes y poca disponibilidad de información
consolidada para la toma de decisiones.
La propuesta consiste en desarrollar una plataforma web con Spring Boot y patrón
MVC, que permita digitalizar y centralizar el proceso desde la solicitud inicial del
cliente hasta la generación de la cuenta de cobro y, posteriormente, el pago
electrónico.
_______________________________________________
## ¿Qué estilo arquitectónico eligieron para su proyecto de aula y por qué?
Hemos tomado la arquitectura en capas para el sistema web de JLG Aislamientos
Térmicos e Industriales y hoteleros, debido a que actualmente la aplicación se
encuentra desarrollada con Spring Boot bajo el patrón MVC. 


<img width="300" height="498" alt="imagen" src="https://github.com/user-attachments/assets/964b0b75-fbe4-4b84-bc46-9f741a7bc3cd" />



## Historias de Usuario: Plataforma Web JLG Aislamientos
|ID de la Historia| Rol| Funcionalidad | Criterios de Evaluación|
|---|----|---|---|
|HU01| ADMIN/USER |Quiero iniciar sesión de forma segura usando mis credenciales o mi cuenta de Google,Para que pueda acceder a las funcionalidades permitidas para mi rol, garantizando la confidencialidad de la información de la empresa|- El sistema debe permitir el login con correo y contraseña encriptada (BCrypt). - El sistema debe permitir el login alternativo mediante OAuth 2.0 (Google). - El sistema debe redirigir al dashboard correspondiente según el rol (ADMIN o USER). - El sistema debe limitar a 1 sesión activa por usuario, expulsando la anterior si se inicia en otro dispositivo. - Al cerrar sesión, la sesión debe invalidarse completamente y redirigir al login con un mensaje de confirmación|
