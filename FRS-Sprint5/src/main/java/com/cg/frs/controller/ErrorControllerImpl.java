/**
 * 
 */
package com.cg.frs.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author DEVANG
 *
 */

@Controller
public class ErrorControllerImpl implements ErrorController{

	@GetMapping("/error")
    public String handleError() {
        return "ErrorPage";
    }
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
	
}
