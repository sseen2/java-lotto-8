package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_COUNT;
import static lotto.domain.LottoConstants.LOTTO_MAX_NUMBER;
import static lotto.domain.LottoConstants.LOTTO_MIN_NUMBER;

import java.util.Collections;
import java.util.List;
import lotto.view.message.ErrorMessage;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateNumbersSize(numbers);
        validateNumbersRange(numbers);
        validateNumbersDuplicated(numbers);
    }

    public Ranking getRanking(WinningLotto winningLotto) {
        int matchCount = (int) numbers.stream()
                .filter(winningLotto::isMatchNumber)
                .count();
        boolean matchBonusNumber = winningLotto.isMatchBonusNumber(numbers);

        return Ranking.result(matchCount, matchBonusNumber);
    }

    public List<Integer> toList() {
        return Collections.unmodifiableList(numbers);
    }

    private void validateNumbersSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_COUNT) {
            String errorMessage = String.format(ErrorMessage.WINNING_NUMBER_INVALID_COUNT.getMessage(), LOTTO_COUNT);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateNumbersRange(List<Integer> numbers) {
        numbers.forEach(number -> {
            if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
                String errorMessage = String.format(ErrorMessage.WINNING_NUMBER_OUT_OF_RANGE.getMessage(), LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER);
                throw new IllegalArgumentException(errorMessage);
            }
        });
    }

    private void validateNumbersDuplicated(List<Integer> numbers) {
        List<Integer> notDuplicatedNumbers = numbers.stream()
                .distinct()
                .toList();
        if (notDuplicatedNumbers.size() != LOTTO_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_NUMBER_NOT_DUPLICATE.getMessage());
        }
    }
}
