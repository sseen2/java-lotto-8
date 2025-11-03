package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    private Lotto winningNumbers;

    @BeforeEach
    void setUp() {
        winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
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
