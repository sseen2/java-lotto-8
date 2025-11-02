package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.view.message.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static lotto.domain.LottoConstants.LOTTO_COUNT;
import static lotto.domain.LottoConstants.LOTTO_MAX_NUMBER;
import static lotto.domain.LottoConstants.LOTTO_MIN_NUMBER;
import static lotto.domain.LottoConstants.LOTTO_PRIZE;
import static org.assertj.core.api.Assertions.assertThat;

class LottoApplicationTest extends NsTest {

    @DisplayName("기능 테스트")
    @Nested
    class functionTest {

        @DisplayName("입력이 올바른 경우 실행 성공")
        @Test
        void successTest() {
            assertRandomUniqueNumbersInRangeTest(
                    () -> {
                        run("8000", "1,2,3,4,5,6", "7");
                        assertThat(output()).contains(
                                "8개를 구매했습니다.",
                                "[8, 21, 23, 41, 42, 43]",
                                "[3, 5, 11, 16, 32, 38]",
                                "[7, 11, 16, 35, 36, 44]",
                                "[1, 8, 11, 31, 41, 42]",
                                "[13, 14, 16, 38, 42, 45]",
                                "[7, 11, 30, 40, 42, 43]",
                                "[2, 13, 22, 32, 38, 45]",
                                "[1, 3, 5, 14, 22, 45]",
                                "3개 일치 (5,000원) - 1개",
                                "4개 일치 (50,000원) - 0개",
                                "5개 일치 (1,500,000원) - 0개",
                                "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                                "6개 일치 (2,000,000,000원) - 0개",
                                "총 수익률은 62.5%입니다."
                        );
                    },
                    List.of(8, 21, 23, 41, 42, 43),
                    List.of(3, 5, 11, 16, 32, 38),
                    List.of(7, 11, 16, 35, 36, 44),
                    List.of(1, 8, 11, 31, 41, 42),
                    List.of(13, 14, 16, 38, 42, 45),
                    List.of(7, 11, 30, 40, 42, 43),
                    List.of(2, 13, 22, 32, 38, 45),
                    List.of(1, 3, 5, 14, 22, 45)
            );
        }
    }

    @DisplayName("입력 예외 테스트")
    @Nested
    class exceptionTest {

        @DisplayName("로또 구입 금액")
        @Nested
        class purchaseAmountTest {
            @DisplayName("숫자가 아닌 경우")
            @Test
            void purchaseAmountNotNumber() {
                assertSimpleTest(() -> {
                    runException("1000j");
                    assertThat(output()).contains(ErrorMessage.INPUT_INVALID_FORMAT.getMessage());
                });
            }

            @DisplayName("로또 금액보다 작은 경우")
            @Test
            void purchaseAmountZero() {
                String errorMessage = String.format(ErrorMessage.PURCHASE_AMOUNT_INVALID_AMOUNT.getMessage(), LOTTO_PRIZE);

                assertSimpleTest(() -> {
                    runException("0");
                    assertThat(output()).contains(errorMessage);
                });
            }

            @DisplayName("로또 금액으로 나누어 떨어지지 않는 경우")
            @Test
            void purchaseAmountNotDivideUp() {
                String errorMessage = String.format(ErrorMessage.PURCHASE_AMOUNT_NOT_DIVIDE_UP.getMessage(), LOTTO_PRIZE);

                assertSimpleTest(() -> {
                    runException("1500");
                    assertThat(output()).contains(errorMessage);
                });
            }
        }

        @DisplayName("당첨 번호")
        @Nested
        class winningNumberTest {

            @DisplayName("숫자가 아닌 경우")
            @Test
            void winningNumberNotNumber() {
                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5,육");
                    assertThat(output()).contains(ErrorMessage.INPUT_INVALID_FORMAT.getMessage());
                });
            }

            @DisplayName("개수가 6개보다 작은 경우")
            @Test
            void winningNumberTooFewCount() {
                String errorMessage = String.format(ErrorMessage.WINNING_NUMBER_INVALID_COUNT.getMessage(), LOTTO_COUNT);

                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5");
                    assertThat(output()).contains(errorMessage);
                });
            }

            @DisplayName("개수가 6개보다 많은 경우")
            @Test
            void winningNumberTooManyCount() {
                String errorMessage = String.format(ErrorMessage.WINNING_NUMBER_INVALID_COUNT.getMessage(), LOTTO_COUNT);

                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5,6,7");
                    assertThat(output()).contains(errorMessage);
                });
            }

            @DisplayName("1보다 작은 경우")
            @Test
            void winningNumberTooSmall() {
                String errorMessage = String.format(ErrorMessage.WINNING_NUMBER_OUT_OF_RANGE.getMessage(), LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER);

                assertSimpleTest(() -> {
                    runException("1000", "0,1,2,3,4,5");
                    assertThat(output()).contains(errorMessage);
                });
            }

            @DisplayName("45보다 큰 경우")
            @Test
            void winningNumberTooBig() {
                String errorMessage = String.format(ErrorMessage.WINNING_NUMBER_OUT_OF_RANGE.getMessage(), LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER);

                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5,46");
                    assertThat(output()).contains(errorMessage);
                });
            }

            @DisplayName("중복되는 경우")
            @Test
            void winningNumberDuplicate() {
                assertSimpleTest(() -> {
                    runException("1000", "1,1,2,3,4,5");
                    assertThat(output()).contains(ErrorMessage.WINNING_NUMBER_NOT_DUPLICATE.getMessage());
                });
            }
        }

        @DisplayName("보너스 번호")
        @Nested
        class bonusNumberTest {

            @DisplayName("숫자가 아닌 경우")
            @Test
            void bonusNumberNotNumber() {
                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5,6", "칠");
                    assertThat(output()).contains(ErrorMessage.INPUT_INVALID_FORMAT.getMessage());
                });
            }

            @DisplayName("당첨 번호랑 같은 경우")
            @Test
            void bonusNumberEqualWinningNumber() {
                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5,6", "6");
                    assertThat(output()).contains(ErrorMessage.BONUS_NUMBER_NOT_DUPLICATE.getMessage());
                });
            }

            @DisplayName("1보다 작은 경우")
            @Test
            void bonusNumberTooSmall() {
                String errorMessage = String.format(ErrorMessage.BONUS_NUMBER_OUT_OF_RANGE.getMessage(), LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER);

                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5,6", "0");
                    assertThat(output()).contains(errorMessage);
                });
            }

            @DisplayName("45보다 큰 경우")
            @Test
            void bonusNumberTooBig() {
                String errorMessage = String.format(ErrorMessage.BONUS_NUMBER_OUT_OF_RANGE.getMessage(), LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER);

                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5,6", "46");
                    assertThat(output()).contains(errorMessage);
                });
            }
        }
    }

    @Override
    public void runMain() {
        LottoApplication.main(new String[]{});
    }
}
