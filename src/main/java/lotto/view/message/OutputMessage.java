package lotto.view.message;

public enum OutputMessage {

    PURCHASE_COUNT("%d개를 구매했습니다."),
    WINNING_INFORMATION("당첨 통계\n---"),
    WINNING_INFORMATION_FORMAT("%d개 일치 (%,d원) - %d개"),
    WINNING_INFORMATION_SECOND_FORMAT("%d개 일치, 보너스 볼 일치 (%,d원) - %d개"),
    RATE_OF_PROFIT("총 수익률은 %.1f%%입니다.");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
