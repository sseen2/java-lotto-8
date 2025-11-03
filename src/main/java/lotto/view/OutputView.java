package lotto.view;

import static lotto.domain.LottoConstants.LOTTO_PRIZE;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.view.message.InputMessage;
import lotto.view.message.OutputMessage;

public class OutputView {

    private static final String BLANK_STRING = "";

    private OutputView() {
    }

    private static void print(String message) {
        System.out.println(message);
    }

    public static void printBlank() {
        print(BLANK_STRING);
    }

    public static void printInputPurchaseAmount() {
        print(InputMessage.PURCHASE_AMOUNT.getMessage());
    }

    public static void printPurchaseCount(int purchaseAmount) {
        print(String.format(OutputMessage.PURCHASE_COUNT.getMessage(), purchaseAmount / LOTTO_PRIZE));
    }

    public static void printInputWinningNumbers() {
        print(InputMessage.WINNING_NUMBERS.getMessage());
    }

    public static void printInputBonusNumber() {
        print(InputMessage.BONUS_NUMBER.getMessage());
    }

    public static void printLottos(List<Lotto> lottos) {
        lottos.forEach(lotto -> {
            print(lotto.toList().toString());
        });
        OutputView.printBlank();
    }
}
