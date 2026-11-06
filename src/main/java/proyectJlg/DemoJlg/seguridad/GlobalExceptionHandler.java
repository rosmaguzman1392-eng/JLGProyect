package proyectJlg.DemoJlg.seguridad;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ERROR 404: Página no encontrada
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(Exception ex, Model model, HttpServletRequest request) {
        model.addAttribute("error", "404");
        model.addAttribute("message", "La página que buscas no existe en JLG Aislamientos.");
        model.addAttribute("path", request.getRequestURI());
        return "error/404";
    }

    // ERROR 500: Cualquier otro error interno del servidor
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle500(Exception ex, Model model) {
        model.addAttribute("error", "500");
        model.addAttribute("message", "Ocurrió un error inesperado en nuestro servidor.");
        model.addAttribute("exception", ex.getMessage());
        return "error/500";
    }
}