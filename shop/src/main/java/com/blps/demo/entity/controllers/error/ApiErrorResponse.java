package com.blps.demo.entity.controllers.error;

import java.util.List;

public record ApiErrorResponse(String description, String code, String exceptionName, String exceptionMessage){
}