package com.pda.userapplication.outadapters.jpa;

import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.userapplication.domains.User;
import com.pda.userapplication.domains.vo.TofinId;
import com.pda.userapplication.outadapters.jpa.mapper.UserEntityMapper;
import com.pda.userapplication.services.out.CreateUserOutputPort;
import com.pda.userapplication.services.out.ReadUserOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserJpaAdapter implements CreateUserOutputPort, ReadUserOutputPort {
    private final UserRepository userRepository;
    private final UserEntityMapper mapper;

    @Override
    public User create(final User user) {
        return mapper.toUser(
            userRepository.save(mapper.toUserEntity(user)));
    }

    @Override
    public boolean isExistsByTofinId(final TofinId tofinId) {
        return userRepository.existsByTofinId(tofinId.toString());
    }

    @Override
    public User getByTofinId(TofinId tofinId) {
        return findByTofinId(tofinId).orElseThrow(
            () -> new BadRequestException("해당 아이디의 유저를 찾을 수 없습니다."));
    }

    @Override
    public Optional<User> findByTofinId(TofinId tofinId) {
        return Optional.ofNullable(
            mapper.toUser(
                userRepository.findByTofinId(tofinId.toString())
                .orElse(null)));
    }
}
