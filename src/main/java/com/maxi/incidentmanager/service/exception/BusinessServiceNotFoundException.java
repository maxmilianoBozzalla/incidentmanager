package com.maxi.incidentmanager.service.exception;

import com.maxi.incidentmanager.shared.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class BusinessServiceNotFoundException extends BusinessException {
    public BusinessServiceNotFoundException() {
        super("Service not found", HttpStatus.NOT_FOUND);
    }
}
