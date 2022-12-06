package zerobase.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import zerobase.demo.dto.UserDto;
import zerobase.demo.entity.User;
import zerobase.demo.exception.UserException;
import zerobase.demo.repository.UserRepository;
import zerobase.demo.type.ResponseCode;
import zerobase.demo.type.UserStatus;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	public boolean createUser(String userId,
		String password,
		String userName,
		String phone,
		String userAddr,
		String status) {

		Optional<User> optionalMember = userRepository.findByUserId(userId);
		if (optionalMember.isPresent()) {
			throw new UserException(ResponseCode.ALREADY_REGISTERED_ID);
		}

		if (!status.equals("user") && !status.equals("owner")) {
			throw new UserException(ResponseCode.STATUS_INPUT_ERROR);
		}
		UserStatus userStatus;

		if (status.equals("user")) {
			userStatus = UserStatus.user;
		} else {
			userStatus = UserStatus.owner;
		}

		String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		String uuid = UUID.randomUUID().toString();

		User user = User.builder()
			.userId(userId)
			.password(encPassword)
			.userName(userName)
			.phone(phone)
			.userAddr(userAddr)
			.emailAuthKey(uuid)
			.emailAuth(false)
			.status(userStatus)
			.passwordChangeTime(null)
			.build();

		userRepository.save(user);

		return true;
	}

	public boolean adminUpdateUser(String userId,
		String userName,
		String phone,
		String userAddr,
		String status,
		boolean emailAuth,
		String myId
	) {
		Optional<User> optionalAdmin = userRepository.findByUserId(myId);
		if (!optionalAdmin.isPresent()) {
			throw new UserException(ResponseCode.USER_NOT_FIND);
		}

		if (!optionalAdmin.get().getStatus().name().equals("admin")) {
			throw new UserException(ResponseCode.NOT_ADMIN_ROLL);
		}

		Optional<User> optionalMember = userRepository.findByUserId(userId);
		if (!optionalMember.isPresent()) {
			throw new UserException(ResponseCode.USER_NOT_FIND);
		}

		User user = optionalMember.get();
		user.setUserAddr(userAddr);
		user.setUserName(userName);
		user.setPhone(phone);
		user.setStatus(status);
		user.setEmailAuth(emailAuth);

		userRepository.save(user);

		return true;
	}

//	public User readMyInfo(String myId) {
//
//	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UserException {

		Optional<User> optionalMember = userRepository.findByUserId(userId);
		if (!optionalMember.isPresent()) {
			throw new UserException(ResponseCode.USER_NOT_FIND);
		}

		User user = optionalMember.get();

		if (!user.getEmailAuth()) {
			throw new UserException(ResponseCode.USER_NOT_EMAIL_AUTH);
		}

		if (user.getStatus().toString().equals("stop")) {
			throw new UserException(ResponseCode.USER_IS_STOP);
		}

//		if (Member.MEMBER_STATUS_WITHDRAW.equals(member.getUserStatus())) {
//			throw new MemberStopUserException("탈퇴된 회원 입니다.");
//		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		if (user.getStatus().toString().equals("admin")) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return new org.springframework.security.core.userdetails.User(user.getUserId(),
			user.getPassword(), grantedAuthorities);
	}
}
