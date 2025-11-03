package lotto.domain;

public enum Ranking {

    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean matchBonusNumber;
    private final int prize;

    Ranking(int matchCount, boolean matchBonusNumber, int prize) {
        this.matchCount = matchCount;
        this.matchBonusNumber = matchBonusNumber;
        this.prize = prize;
    }

    public boolean isSecond() {
        return this == Ranking.SECOND;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isMatchBonusNumber() {
        return matchBonusNumber;
    }

    public int getPrize() {
        return prize;
    }
}
