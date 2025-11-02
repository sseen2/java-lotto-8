package lotto.view;

import static lotto.domain.LottoConstants.LOTTO_PRIZE;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.stream.Stream;
import lotto.view.message.ErrorMessage;

public class InputView {

    private static final String WINNING_NUMBER_SPLITTER = ",";

    private InputView() {
    }

    private static String input() {
        return Console.readLine().trim();
    }

    private static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_INVALID_FORMAT.getMessage());
        }
    }

    public static int inputPurchaseAmount() {
        int purchaseAmount = parseInt(input());
        validatePurchaseAmount(purchaseAmount);

        return purchaseAmount;
    }

    private static void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < LOTTO_PRIZE) {
            String errorMessage = String.format(ErrorMessage.PURCHASE_AMOUNT_INVALID_AMOUNT.getMessage(), LOTTO_PRIZE);
            throw new IllegalArgumentException(errorMessage);
        }

        if (purchaseAmount % LOTTO_PRIZE != 0) {
            String errorMessage = String.format(ErrorMessage.PURCHASE_AMOUNT_NOT_DIVIDE_UP.getMessage(), LOTTO_PRIZE);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static List<Integer> inputWinningNumbers() {
        return Stream.of(input().split(WINNING_NUMBER_SPLITTER))
                .map(InputView::parseInt)
                .toList();
    }

    public static int inputBonusNumber() {
        return parseInt(input());
    }
}
