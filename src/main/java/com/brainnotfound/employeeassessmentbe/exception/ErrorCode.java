package com.brainnotfound.employeeassessmentbe.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    USERNAME_INVALID("Username must be at least 8 characters Long."),
    PASSWORD_INVALID("Password must be at least 5 characters Long."),
    INVALID_KEY("Invalid key."),
    USER_EXISTED("Username has already existed."),
    WRONG_PASSWORD("Password is not correct"),
    USER_NOT_EXISTED("User have not existed"),
    CRITERIA_NOT_EXISTED("Criteria have not existed"),
    ASSESSMENT_NOT_EXISTED("Assessment have not existed"),
    FAILED_GENERATE_TOKEN("failed when generating the token"),
    VERIFY_TOKEN_EXCEPTION("failed when verify token"),
    TOKEN_INVALID("Token is invalid."),
    ACCESS_DENY("You don't have permission to access this thing."),
    UNCATEGORIED_EXCEPTION("Uncategoried exception"),
    DELETE_FAILED("Delete failed."),
    YOU_ARE_NOT_PERMITTED("You are not permitted to do this."),
    ROLE_INVALID("Role is invalid."),
    FORBIDDEN("You are not allowed to do this."),
    CRITERIA_EXISTS("Criteria have already exists."),
    ASSESSMENT_SCORE_INVALID("Assessment's score is invalid.")
    ;
    private String message;

}
