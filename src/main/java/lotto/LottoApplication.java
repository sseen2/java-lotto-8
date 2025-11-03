package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        int purchaseAmount = getPurchaseAmount();
        OutputView.printBlank();

        OutputView.printPurchaseCount(purchaseAmount);
        Lottos lottos = LottoGenerator.createLottos(purchaseAmount);
        OutputView.printLottos(lottos.toList());

        Lotto winningNumbers = getWinningNumbers();
        OutputView.printBlank();

        WinningLotto winningLotto = getWinningLotto(winningNumbers);
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

    private static WinningLotto getWinningLotto(Lotto winningNumbers) {
        while(true) {
            try {
                OutputView.printInputBonusNumber();
                int bonusNumber = InputView.inputBonusNumber();
                return new WinningLotto(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
