/**
 * 
 */
package com.cg.frs.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;



import com.cg.frs.exception.CustomGenericExeption;
import com.cg.frs.exception.FlightExceptions;

/**
 * @author NAVYA
 *
 */

@ControllerAdvice
public class GlobalExceptionController {

	// private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);
	
	@ExceptionHandler(FlightExceptions.class)
	public ModelAndView handleCustomException(FlightExceptions ex) {

		ModelAndView model = new ModelAndView("ErrorPage");
		
		model.addObject("errMsg", ex.getErrMsg());

		return model;

	}

	
	
	
}
