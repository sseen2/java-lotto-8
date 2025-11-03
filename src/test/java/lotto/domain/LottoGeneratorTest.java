package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {

    @DisplayName("로또 구매 금액만큼 로또 생성")
    @Test
    void createLottos() {
        int purchaseAmount = 5000;
        int lottoCount = 5;

        Lottos lottos = LottoGenerator.createLottos(purchaseAmount);

        List<Lotto> resultLottos = lottos.toList();

        assertThat(lottoCount).isEqualTo(resultLottos.size());
    }

    @DisplayName("로또가 오름차순으로 정렬")
    @Test
    void sortLotto() {
        int purchaseAmount = 1000;

        List<Lotto> lotto = LottoGenerator.createLottos(purchaseAmount)
                .toList();
        List<Integer> numbers = lotto.getFirst()
                .toList();

        List<Integer> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);

        assertThat(sorted).isEqualTo(numbers);
    }
}
