package dev.raul.demo_park_api.web.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class dto {
    private String senhaAtual;
    private String novaSenha;
    private String confirmaSenha;
}
