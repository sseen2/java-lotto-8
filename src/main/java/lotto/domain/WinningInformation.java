package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningInformation {

    private static final int MAP_DEFAULT_VALUE = 0;
    private static final int PERCENT_UNIT = 100;

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

    public double calculateRateOfProfit(int purchaseAmount) {
        return (double) getTotalPrize() / purchaseAmount * PERCENT_UNIT;
    }

    private long getTotalPrize() {
        return result.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }
}
