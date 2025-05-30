package dev.raul.demo_park_api.web.dto.mapper;

import dev.raul.demo_park_api.entity.Usuario;
import dev.raul.demo_park_api.web.dto.UsuarioCreateDto;
import dev.raul.demo_park_api.web.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UsuarioMapper {
    public static Usuario toUsuario(UsuarioCreateDto usuarioCreateDTO){
        return new ModelMapper().map(usuarioCreateDTO, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario){
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDto> propertyMap = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(propertyMap);
        return mapper.map(usuario, UsuarioResponseDto.class);
    }
}
