package org.example.pro2projekt.controller;

import com.vaadin.flow.server.HandlerHelper;
import com.vaadin.flow.shared.ApplicationConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.websocket.AuthenticationType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.logging.Handler;
import java.util.stream.Stream;

public class SecurityUtils {
    private SecurityUtils(){

    }
    static boolean isFrameworkInternalRequest(HttpServletRequest request){
        final String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
        return parameterValue!=null && Stream.of(HandlerHelper.RequestType.values()).
                anyMatch(r -> r.getIdentifier().equals(parameterValue));
    }
    static boolean isUserLoggedIn(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken)&& authentication.isAuthenticated();
    }
}
