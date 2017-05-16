package com.xinguang.iot.exception;


import com.xinguang.iot.response.IotResponse;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by jinx on 2017/5/15.
 */
public abstract class IotBaseException extends Exception{

    public IotBaseException() {
    }

    public IotBaseException(Throwable cause) {
        super(cause);
    }

    public abstract IotResponse.ErrorCode getErrorCode();

    public static <D> IotResponse<D> genErrorCodeResponse(Exception e) {
        if (e instanceof IotBaseException) {
            return new IotResponse<D>(((IotBaseException) e).getErrorCode());
        } else if (e instanceof ConstraintViolationException) {
            return new IotResponse<D>(IotResponse.ErrorCode.ERROR_PARAM_ILLEGAL, genConstraintViolationDetail((ConstraintViolationException) e));
        } else {
            return new IotResponse<D>(IotResponse.ErrorCode.ERROR_SYSTEM_ERROR);
        }
    }

    public static String genConstraintViolationDetail(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> set = e.getConstraintViolations();

        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (ConstraintViolation<?> constraintViolation : set) {
            if (!first) {
                sb.append(",");
            } else {
                first = false;
            }
            sb.append("{").append(constraintViolation.getPropertyPath()).append(":").append(constraintViolation.getMessage()).append("}");

        }
        sb.append("]");
        return sb.toString();
    }

}
