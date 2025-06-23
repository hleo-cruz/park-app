package com.br.park.util;

import com.br.park.error.CustomException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;

public class LogUtil {

    public static void logError(Logger log, CustomException exception) {

        final String stackTrace = ExceptionUtils.getStackTrace(exception);
        final String message = ExceptionUtils.getMessage(exception);
        final String detailMessage = exception.getMessage();
        final String rootCauseMessage = ExceptionUtils.getRootCauseMessage(exception);

        log.error("message: {}", message);
        log.error("detailMessage: {}", detailMessage);
        log.error("rootCauseMessage: {}", rootCauseMessage);
    }
}
