package com.peerlender.lendingengine.application.service;

import com.peerlender.lendingengine.domain.model.User;

public interface TokenValidationService {
    User ValidateTokenAndGetUser(final String token);

}
