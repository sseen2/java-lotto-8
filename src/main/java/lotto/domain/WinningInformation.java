package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningInformation {

    private static final int MAP_DEFAULT_VALUE = 0;

    private Map<Ranking, Integer> result;
    private WinningLotto winningLotto;

    public WinningInformation(WinningLotto winningLotto) {
        result = new HashMap<>();
        this.winningLotto = winningLotto;
    }

    public void calculateResult(List<Lotto> lottos) {
        lottos.forEach(lotto -> {
            Ranking ranking = lotto.getRanking(winningLotto);
            result.put(ranking, result.getOrDefault(ranking, MAP_DEFAULT_VALUE) + 1);
        });
    }

    public int getMatchCount(Ranking ranking) {
        return result.getOrDefault(ranking, MAP_DEFAULT_VALUE);
    }
}
