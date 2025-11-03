package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_MAX_NUMBER;
import static lotto.domain.LottoConstants.LOTTO_MIN_NUMBER;

import lotto.view.message.ErrorMessage;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(Lotto winningNumbers, int bonusNumber) {
        validateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto winningNumbers, int bonusNumber) {
        validateDuplicated(winningNumbers, bonusNumber);
        validateRange(bonusNumber);
    }

    private void validateDuplicated(Lotto winningNumbers, int bonusNumber) {
        if (winningNumbers.toList().contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_NOT_DUPLICATE.getMessage());
        }
    }

    private void validateRange(int bonusNumber) {
        if (bonusNumber < LOTTO_MIN_NUMBER || bonusNumber > LOTTO_MAX_NUMBER) {
            String errorMessage = String.format(ErrorMessage.BONUS_NUMBER_OUT_OF_RANGE.getMessage(), LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
