package com.application.shared.infrastructure.http;                                                                 
                                                                                                                        
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.application.shared.domain.ResourceNotFoundException; 

@RestControllerAdvice 
public class GlobalExceptionHandler {
// 404 - Recurso não encontrado (ex: UserNotFoundException)                                                     
        @ExceptionHandler(ResourceNotFoundException.class)                                                              
        public ProblemDetail handleNotFound(ResourceNotFoundException ex) {                                             
            ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());            
            problem.setTitle("Recurso não encontrado");                                                                 
            return problem;                                                                                             
        }                                                                                                               
                                                                                                                        
        // 400 - Falha no @Valid (telefone, email, nome...)                                                             
        @ExceptionHandler(MethodArgumentNotValidException.class)                                                        
        public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {                                     
            ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Erro de validação nos campos enviados");                                                                                          
            problem.setTitle("Dados inválidos");                                                                        
                                                                                                                        
            Map<String, String> fieldErrors = new HashMap<>();                                                          
            ex.getBindingResult().getFieldErrors()                                                                      
                    .forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));                          
            problem.setProperty("errors", fieldErrors);                                                                 
            return problem;                                                                                             
        }                                                                                                               
                                                                                                                        
        // 400 - Regra de domínio violada (ex: new Phone(...) ou UUID.fromString(...) inválido)                         
        @ExceptionHandler(IllegalArgumentException.class)                                                               
        public ProblemDetail handleIllegalArgument(IllegalArgumentException ex) {                                       
            ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());          
            problem.setTitle("Requisição inválida");                                                                    
            return problem;                                                                                             
        }                                                                                                               
                                                                                                                        
        // 400 - Parâmetro na URL com tipo errado (ex: UUID malformado ou Category inexistente)                         
        @ExceptionHandler(MethodArgumentTypeMismatchException.class)                                                    
        public ProblemDetail handleTypeMismatch(MethodArgumentTypeMismatchException ex) {                               
            String detail = "Valor inválido para o parâmetro '" + ex.getName() + "': " + ex.getValue();                 
            ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);                   
            problem.setTitle("Parâmetro inválido");                                                                     
            return problem;                                                                                             
        }                                                                                                               
                                                                                                                        
        // 409 - Violação de UNIQUE no banco (ex: telefone já cadastrado)                                               
        @ExceptionHandler(DataIntegrityViolationException.class)                                                        
        public ProblemDetail handleConflict(DataIntegrityViolationException ex) {                                       
            ProblemDetail problem = ProblemDetail.forStatusAndDetail(                                                   
                    HttpStatus.CONFLICT,                                                                                
                    "Já existe um registro com os dados únicos informados (ex: telefone já cadastrado)"                 
            );                                                                                                          
            problem.setTitle("Conflito de dados");                                                                      
            return problem;                                                                                             
        } 
}
