package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @DisplayName("6개 번호가 맞는 경우 1등 반환")
    @Test
    void getRankingFirstTest() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto winningNumber = new Lotto(winningNumbers);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        Ranking result = lotto.getRanking(winningLotto);

        assertThat(result).isEqualTo(Ranking.FIRST);
    }

    @DisplayName("5개와 보너스 번호가 맞는 경우 2등 반환")
    @Test
    void getRankingSecondTest() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto winningNumber = new Lotto(winningNumbers);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 7);
        Lotto lotto = new Lotto(numbers);

        Ranking result = lotto.getRanking(winningLotto);

        assertThat(result).isEqualTo(Ranking.SECOND);
    }

    @DisplayName("5개 번호가 맞는 경우 3등 반환")
    @Test
    void getRankingThirdTest() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto winningNumber = new Lotto(winningNumbers);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 8);
        Lotto lotto = new Lotto(numbers);

        Ranking result = lotto.getRanking(winningLotto);

        assertThat(result).isEqualTo(Ranking.THIRD);
    }

    @DisplayName("4개 번호가 맞는 경우 4등 반환")
    @Test
    void getRankingFourthTest() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto winningNumber = new Lotto(winningNumbers);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        List<Integer> numbers = List.of(1, 2, 3, 4, 8, 9);
        Lotto lotto = new Lotto(numbers);

        Ranking result = lotto.getRanking(winningLotto);

        assertThat(result).isEqualTo(Ranking.FOURTH);
    }

    @DisplayName("3개 번호가 맞는 경우 5등 반환")
    @Test
    void getRankingFifthTest() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto winningNumber = new Lotto(winningNumbers);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        List<Integer> numbers = List.of(1, 2, 3, 8, 9, 10);
        Lotto lotto = new Lotto(numbers);

        Ranking result = lotto.getRanking(winningLotto);

        assertThat(result).isEqualTo(Ranking.FIFTH);
    }

    @DisplayName("아무 번호도 맞지 않는 경우 미당첨 반환")
    @Test
    void getRankingNoneTest() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto winningNumber = new Lotto(winningNumbers);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        List<Integer> numbers = List.of(8, 9, 10, 11, 12, 13);
        Lotto lotto = new Lotto(numbers);

        Ranking result = lotto.getRanking(winningLotto);

        assertThat(result).isEqualTo(Ranking.NONE);
    }

    @DisplayName("2개와 보너스 번호가 맞는 경우 미당첨 반환")
    @Test
    void getRankingNoneBonusTest() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto winningNumber = new Lotto(winningNumbers);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        List<Integer> numbers = List.of(1, 2, 7, 8, 9, 10);
        Lotto lotto = new Lotto(numbers);

        Ranking result = lotto.getRanking(winningLotto);

        assertThat(result).isEqualTo(Ranking.NONE);
    }

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 개수가 6개보다 적은 경우 예외 발생")
    @Test
    void lottoNumberTooFew() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1보다 작은 경우 예외 발생")
    @Test
    void lottoNumberTooSmall() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 45보다 큰 경우 예외 발생")
    @Test
    void lottoNumberTooBig() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
