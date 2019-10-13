/**
 * 
 */
package com.cg.frs.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author DEVANG
 *
 */

@Controller
public class ErrorControllerImpl implements ErrorController{

	@RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "ErrorPage";
    }
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
	
}
