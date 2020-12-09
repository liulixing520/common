package com.jt.lux.aop;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.jt.lux.enums.ResultEnum;
import com.jt.lux.exception.BizException;
import com.jt.lux.exception.ParamException;
import com.jt.lux.exception.ServiceException;
import com.jt.lux.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 描述：统一异常处理类
 * 类名称：GlobalDefultExceptionHandler.java
 * 版本：1.0
 * 修改： 2019/7/19 15:15
 * 创建日期： 2018/7/23 15:15
 */
@Slf4j
@ControllerAdvice
public class GlobalDefultExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity defultExcepitonHandler(HttpServletRequest request, Exception e) {
        StringBuilder result = new StringBuilder();
        if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            for (FieldError fieldErro : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
                result.append(fieldErro.getDefaultMessage() + "\n");
            }
            String resultStr = result.toString();
            log.error("MethodArgumentNotValidException：",e);
            return GenericResponse.ng(resultStr.substring(0, resultStr.length()-1));
        }else if( e instanceof ConstraintViolationException){
            ConstraintViolationException constraintViolationException = (ConstraintViolationException)e;
            for(ConstraintViolation constraintViolation:constraintViolationException.getConstraintViolations()){
                result.append(constraintViolation.getMessage()+"\n");
            }
            String resultStr = result.toString();
            log.error("ConstraintViolationException：",e);
            return GenericResponse.ng(resultStr.substring(0, resultStr.length()-1));
        }else if(e instanceof BizException ||e instanceof ParamException || e instanceof ServiceException){
            return GenericResponse.ng(e.getMessage());
        } else if (e instanceof HttpRequestMethodNotSupportedException){
            e.printStackTrace();
            log.error("HttpRequestMethodNotSupportedException：",e);
            return GenericResponse.ng(ResultEnum.REQUEST_METHOD_ERROR.getMessage());
        }else if (e instanceof JsonMappingException || e instanceof HttpMessageNotReadableException){
            e.printStackTrace();
            log.error("JsonMappingException：",e);
            return GenericResponse.ng(ResultEnum.PARAM_ERROR.getMessage());
        } else if(e instanceof MissingServletRequestParameterException){
            e.printStackTrace();
            log.error("MissingServletRequestParameterException：",e);
            return GenericResponse.ng(ResultEnum.PARAM_ERROR_LACK.getMessage());
        } else{
            e.printStackTrace();
            log.error("SERVER_ERROR："+e);
            return GenericResponse.ng(ResultEnum.SERVER_ERROR.getMessage());
        }
    }



}
