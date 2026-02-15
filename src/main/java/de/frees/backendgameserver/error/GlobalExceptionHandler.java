package de.frees.backendgameserver.error;

import com.example.itemapi.model.ErrorDTO;
import de.frees.backendgameserver.exception.ItemNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final String CODE_NOT_FOUND = "NOT_FOUND";
  private static final String CODE_VALIDATION_ERROR = "VALIDATION_ERROR";
  private static final String CODE_INTERNAL_ERROR = "INTERNAL_ERROR";

  @ExceptionHandler(ItemNotFoundException.class)
  public ResponseEntity<ErrorDTO> handleItemNotFound(
      ItemNotFoundException ex, HttpServletRequest request) {
    return buildError(HttpStatus.NOT_FOUND, CODE_NOT_FOUND, ex.getMessage(), request, null);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDTO> handleValidation(
      MethodArgumentNotValidException ex, HttpServletRequest request) {
    List<String> details =
        ex.getBindingResult().getFieldErrors().stream()
            .map(this::formatFieldError)
            .toList();
    return buildError(
        HttpStatus.BAD_REQUEST, CODE_VALIDATION_ERROR, "Validation failed", request, details);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorDTO> handleTypeMismatch(
      MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
    String message = "Invalid value for parameter '" + ex.getName() + "'";
    List<String> details =
        ex.getRequiredType() == null
            ? List.of()
            : List.of("Expected type: " + ex.getRequiredType().getSimpleName());
    return buildError(
        HttpStatus.BAD_REQUEST, CODE_VALIDATION_ERROR, message, request, details);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorDTO> handleNotReadable(
      HttpMessageNotReadableException ex, HttpServletRequest request) {
    String detail = ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : "";
    List<String> details = detail.isBlank() ? List.of() : List.of(detail);
    return buildError(
        HttpStatus.BAD_REQUEST,
        CODE_VALIDATION_ERROR,
        "Malformed JSON request",
        request,
        details);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDTO> handleUnhandled(Exception ex, HttpServletRequest request) {
    log.error("Unhandled exception", ex);
    return buildError(
        HttpStatus.INTERNAL_SERVER_ERROR,
        CODE_INTERNAL_ERROR,
        "Internal server error",
        request,
        null);
  }

  private ResponseEntity<ErrorDTO> buildError(
      HttpStatus status,
      String code,
      String message,
      HttpServletRequest request,
      List<String> details) {
    ErrorDTO error = new ErrorDTO(code, message);
    error.setTimestamp(OffsetDateTime.now());
    error.setPath(request.getRequestURI());
    if (details != null && !details.isEmpty()) {
      error.setDetails(details);
    }
    String requestId = request.getHeader("X-Request-Id");
    if (requestId != null && !requestId.isBlank()) {
      try {
        error.setRequestId(UUID.fromString(requestId));
      } catch (IllegalArgumentException ignored) {
        // Ignore invalid request id format
      }
    }
    return ResponseEntity.status(status).body(error);
  }

  private String formatFieldError(FieldError error) {
    if (error.getDefaultMessage() == null) {
      return error.getField();
    }
    return error.getField() + ": " + error.getDefaultMessage();
  }
}
