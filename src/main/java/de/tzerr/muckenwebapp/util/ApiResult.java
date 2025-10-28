package de.tzerr.muckenwebapp.util;

import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;

import java.util.Optional;
import java.util.function.Supplier;

public record ApiResult<Ok, Error>(@NonNull Optional<Ok> ok, @NonNull Optional<Error> error) {

  public static <Ok> ApiResult<Ok, String> fromLambda(Supplier<Ok> lambda) {
    try {
      return new ApiResult<>(Optional.of(lambda.get()), Optional.empty());
    } catch (ConstraintViolationException e) {
      throw e;
    } catch (Exception e) {
      return new ApiResult<>(Optional.empty(), Optional.of(e.getClass().getSimpleName()));
    }
  }
}
