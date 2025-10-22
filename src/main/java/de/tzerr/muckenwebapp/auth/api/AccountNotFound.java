package de.tzerr.muckenwebapp.auth.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Account not found")
public class AccountNotFound extends RuntimeException {
}
