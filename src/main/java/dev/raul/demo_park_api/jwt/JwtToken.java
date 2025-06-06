package dev.raul.demo_park_api.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtToken { //toda vez que recebermos um pedido de auth e com suce retorna resposta no corpo dela o token gerado pela aplicação, ele gerado retornamos o objetos jwttoke com o token gerado na variavel
    private String token;
}
