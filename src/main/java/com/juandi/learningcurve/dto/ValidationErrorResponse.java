package com.juandi.learningcurve.dto;

import java.time.Instant;
import java.util.List;

public record ValidationErrorResponse(
    Instant timestamp,
    int status,
    String error,
    List<String> messages,
    String path
) {
}
