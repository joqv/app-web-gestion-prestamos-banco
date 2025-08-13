package com.cibertec.mesaverde.presentation.seguridad.dto;

import jakarta.validation.constraints.NotBlank;

public class RefreshTokenRequestDto {
    @NotBlank(message = "El refresh token es obligatorio")
    private String refreshToken;
}
