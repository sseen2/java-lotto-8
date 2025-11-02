package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.WinningNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        int purchaseAmount = getPurchaseAmount();
        OutputView.printBlank();

        // TODO: 구입한 로또 번호 출력

        Lotto winningNumbers = getWinningNumbers();
        OutputView.printBlank();

        WinningNumber winningNumber = getWinningNumber(winningNumbers);
        OutputView.printBlank();

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

    private static Lotto getWinningNumbers() {
        while(true) {
            try {
                OutputView.printInputWinningNumbers();
                List<Integer> winningNumbers = InputView.inputWinningNumbers();
                return new Lotto(winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static WinningNumber getWinningNumber(Lotto winningNumbers) {
        while(true) {
            try {
                OutputView.printInputBonusNumber();
                int bonusNumber = InputView.inputBonusNumber();
                return new WinningNumber(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
