package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        int purchaseAmount = getPurchaseAmount();
        OutputView.printBlank();

        // TODO: 구입한 로또 번호 출력

        // TODO: 당첨 번호 입력

        // TODO: 당첨 번호 출력

        // TODO: 당첨 통계 출력

        // TODO: 총 수익률 출력
    }

    private static int getPurchaseAmount() {
        while(true) {
            try {
                OutputView.printInputPurchaseAmount();
                return InputView.inputPurchaseAmount();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
