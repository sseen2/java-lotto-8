package lotto.view.message;

public enum ErrorMessage {

    ERROR_MESSAGE("[ERROR] "),
    PURCHASE_AMOUNT_INVALID_FORMAT("로또 금액은 숫자여야 합니다."),
    PURCHASE_AMOUNT_INVALID_AMOUNT("로또 금액은 %d원 이상이어야 합니다."),
    PURCHASE_AMOUNT_NOT_DIVIDE_UP("로또 금액은 %d원으로 나누어 떨어저야 합니다."),
    WINNING_NUMBER_INVALID_FORMAT("당첨 번호는 숫자여야 합니다."),
    WINNING_NUMBER_INVALID_COUNT("당첨 번호의 개수는 6개여야 합니다."),
    WINNING_NUMBER_OUT_OF_RANGE("당첨 번호는 1에서 45 사이 숫자여야 합니다."),
    WINNING_NUMBER_NOT_DUPLICATE("당첨 번호는 서로 중복되지 않아야 합니다."),
    BONUS_NUMBER_INVALID_FORMAT("보너스 번호는 숫자여야 합니다."),
    BONUS_NUMBER_NOT_DUPLICATE("보너스 번호는 당첨 번호와 중복되지 않아야 합니다."),
    BONUS_NUMBER_OUT_OF_RANGE("보너스 번호는 1에서 45 사이 숫자여야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_MESSAGE.message + message;
    }
}
