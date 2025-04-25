package vn.scrip.middle.service;
import vn.scrip.middle.entity.User;
import vn.scrip.middle.exception.BadRequestException;
import vn.scrip.middle.mapper.UserMapper;
import vn.scrip.middle.model.request.LoginRequest;
import vn.scrip.middle.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession session;

    public void login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Tài khoản hoặc mật khẩu không chính xác"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Tài khoản hoặc mật khẩu không chính xác");
        }

        // Luu lai: session, cookie, database, redis, ...
        session.setAttribute("currentUser", UserMapper.toDTO(user));
    }

    public void logout() {
        session.removeAttribute("currentUser");
    }
}