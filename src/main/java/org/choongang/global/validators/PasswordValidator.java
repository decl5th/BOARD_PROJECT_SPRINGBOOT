package org.choongang.global.validators;

public interface PasswordValidator {
    /**
     * 알파벳 복잡성 체크
     * @param password
     * @param caseInsensitive - false : 대소문자 각각 1개씩 이상 포함, true - 대소문자 구분 x
     * @return
     */

    default boolean alphaCheck(String password, boolean caseInsensitive) {
        return false;
    }
    /**
     * 숫자 복잡성 체크
     * @param password
     * @return
     */
    default boolean numberCheck(String password) {
        return false;
    }

    /**
     * 특수문자 복합성 체크
     * @param password
     * @return
     */
    default boolean specialCheck(String password) {
        return false;
    }
}
