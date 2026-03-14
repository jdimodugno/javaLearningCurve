package com.juandi.learningcurve.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class RequestHelper {
  private RequestHelper() {
  }

  public static String getPath() {
    final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    final HttpServletRequest requestFromContext = attr.getRequest();
    return requestFromContext.getRequestURI();
  }
}
