package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum Ranking {

    NONE(0, false, 0),
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000);

    private final int count;
    private final boolean matchBonusNumber;
    private final long prize;

    Ranking(int count, boolean matchBonusNumber, long prize) {
        this.count = count;
        this.matchBonusNumber = matchBonusNumber;
        this.prize = prize;
    }

    public static Ranking result(int matchCount, boolean matchBonusNumber) {
        return Arrays.stream(values())
                .filter(ranking -> ranking.isMatch(matchCount, matchBonusNumber))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isMatch(int matchCount, boolean matchBonusNumber) {
        return this.count == matchCount && this.matchBonusNumber == matchBonusNumber;
    }

    public static List<Ranking> getRankings() {
        return Arrays.stream(values())
                .filter(Ranking::isNotNone)
                .sorted()
                .toList();
    }

    private boolean isNotNone() {
        return this != NONE;
    }

    public boolean isSecond() {
        return this == Ranking.SECOND;
    }

    public int getCount() {
        return count;
    }

    public boolean isMatchBonusNumber() {
        return matchBonusNumber;
    }

    public long getPrize() {
        return prize;
    }
}
