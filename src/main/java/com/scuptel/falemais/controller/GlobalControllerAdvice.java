package com.scuptel.falemais.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulationController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView myError(Exception exception) {
        LOGGER.error("Unexpected error ocurred: {}", exception);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        return mv;
    }
}
