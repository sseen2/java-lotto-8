package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    private Lotto winningNumbers;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        int bonusNumber = 7;
        winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    }

    @DisplayName("당첨 번호 중 로또 번호가 있다면 true 반환")
    @Test
    void matchNumberTrue() {
        int number = 1;

        boolean isMatch = winningLotto.isMatchNumber(number);

        assertThat(isMatch).isTrue();
    }

    @DisplayName("당첨 번호 중 로또 번호가 없다면 false 반환")
    @Test
    void matchNumberFalse() {
        int number = 10;

        boolean isMatch = winningLotto.isMatchNumber(number);

        assertThat(isMatch).isFalse();
    }

    @DisplayName("로또 번호 중 보너스 번호가 있다면 true 반환")
    @Test
    void matchBonusNumberTrue() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 7);

        boolean isMatch = winningLotto.isMatchBonusNumber(numbers);

        assertThat(isMatch).isTrue();
    }

    @DisplayName("로또 번호 중 보너스 번호가 없다면 false 반환")
    @Test
    void matchBonusNumberFalse() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        boolean isMatch = winningLotto.isMatchBonusNumber(numbers);

        assertThat(isMatch).isFalse();
    }

    @DisplayName("당첨 번호와 보너스 번호가 같은 경우 예외 발생")
    @Test
    void winningNumberEqualBonusNumber() {
        int bonusNumber = 6;

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 1보다 작은 경우 예외 발생")
    @Test
    void bonusNumberTooSmall() {
        int bonusNumber = 0;

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 45보다 큰 경우 예외 발생")
    @Test
    void bonusNumberTooBig() {
        int bonusNumber = 46;

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
