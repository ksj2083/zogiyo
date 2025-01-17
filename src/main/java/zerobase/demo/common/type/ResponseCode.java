package zerobase.demo.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
	ALREADY_REGISTERED_ID(Result.FAIL, "이미 사용중인 아이디입니다."),
	STATUS_INPUT_ERROR(Result.FAIL, "status는 user 혹은 owner만 가능합니다."),
	INTERNAL_SERVER_ERROR(Result.FAIL, "서버 내부적 오류입니다."),
	USER_NOT_FOUND(Result.FAIL, "해당 유저가 없습니다."),
	USER_NOT_EMAIL_AUTH(Result.FAIL, "이메일 인증 후 이용할 수 있습니다."),
	USER_IS_STOP(Result.FAIL, "정지된 유저입니다."),
	NOT_ADMIN_ROLL(Result.FAIL, "관리자 계정이 아닙니다."),

	CHANGE_USER_INFO_SUCCESS(Result.SUCCESS, "유저 정보를 변경하였습니다."),
	CREATE_USER_SUCCESS(Result.SUCCESS, "회원가입을 성공했습니다."),
	LOGIN_SUCCESS(Result.SUCCESS, "로그인 성공"),
	LOGIN_FAIL(Result.FAIL, "로그인 실패"),
	ORDER_NOT_FOUND(Result.FAIL,"해당 주문이 없습니다."),
	TOO_OFTEN_PASSWORD_CHANGE(Result.FAIL, "비밀번호를 변경한지 30일 경과 전입니다."),
	WRONG_PASSWORD(Result.FAIL, "비밀번호가 틀렸습니다."),
	UN_REGISTER_USER(Result.FAIL, "이미 탈퇴한 회원입니다."),
	NOT_LOGGED(Result.FAIL, "로그인 중이 아닙니다."),
	NOT_ORDERED_THIS_STORE(Result.FAIL, "이 식당에서 주문한 적이 없습니다."),
	ALREADY_ADDED_REVIEW(Result.FAIL, "이미 리뷰를 남기셨습니다."),
	DIFF_ORDER_ID(Result.FAIL, "해당 가게의 주문이 아닙니다."),
	TOO_OLD_AN_ORDER(Result.FAIL, "너무 오래된 주문입니다."),
	NOT_MY_ORDER(Result.FAIL, "해당 유저의 주문이 아닙니다."),

	NOT_OWNER(Result.FAIL, "해당 유저는 owner가 아닙니다."),
	CREATE_STORE_SUCCESS(Result.SUCCESS, "가게 등록을 성공했습니다."),
	OPEN_STORE_SUCCESS(Result.SUCCESS, "가게를 열었습니다."),
	CLOSE_STORE_SUCCESS(Result.SUCCESS, "가게를 닫았습니다."),
	UPDATE_STORE_SUCCESS(Result.SUCCESS, "가게 정보를 변경하였습니다."),
	SELECT_STORE_SUCCESS(Result.SUCCESS, "가게 조회를 성공했습니다."),
	ALREADY_OPEN(Result.FAIL, "가게가 이미 열려 있습니다."),
	ALREADY_CLOSE(Result.FAIL, "가게가 이미 닫혀 있습니다."),
	STORE_NOT_FOUND(Result.FAIL, "존재하지 않는 가게 입니다."),
	NOT_AUTHORIZED(Result.FAIL, "권한이 없습니다."),

	PASSWORD_CHANGE(Result.SUCCESS, "비밀번호를 성공적으로 변경했습니다."),
	ADD_REVIEW_SUCCESS(Result.SUCCESS, "리뷰를 저장하였습니다."),
	EMAIL_AUTH_SUCCESS(Result.SUCCESS, "이메일 인증에 성공하셨습니다."),
	LOGOUT_SUCCESS(Result.SUCCESS, "로그아웃에 성공했습니다."),
	USER_UNREGISTER(Result.SUCCESS,"회원 탈퇴에 성공하였습니다."),
	THERE_IS_NO_ORDER(Result.SUCCESS,"주문내역이 없습니다.");


	private final Result result;
	private final String description;
}