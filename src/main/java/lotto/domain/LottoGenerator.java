package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_COUNT;
import static lotto.domain.LottoConstants.LOTTO_MAX_NUMBER;
import static lotto.domain.LottoConstants.LOTTO_MIN_NUMBER;
import static lotto.domain.LottoConstants.LOTTO_PRIZE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.IntStream;

public class LottoGenerator {

    private LottoGenerator() {
    }

    public static Lottos createLottos(int purchaseAmount) {
        List<Lotto> lottos = IntStream.range(0, purchaseAmount / LOTTO_PRIZE)
                .mapToObj(i -> new Lotto(getRandomLottoNumbers().stream()
                        .sorted()
                        .toList()))
                .toList();

        return new Lottos(lottos);
    }

    private static List<Integer> getRandomLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_COUNT);
    }
}
