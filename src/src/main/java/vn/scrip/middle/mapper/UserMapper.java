package vn.scrip.middle.mapper;

import vn.scrip.middle.entity.User;
import vn.scrip.middle.model.dto.UserDTO;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .displayName(user.getDisplayName())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }
}