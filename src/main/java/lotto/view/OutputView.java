package lotto.view;

import lotto.view.message.InputMessage;

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
}
