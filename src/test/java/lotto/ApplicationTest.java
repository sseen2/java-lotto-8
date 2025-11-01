package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {

    private static final String ERROR_MESSAGE = "[ERROR] ";

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
            @DisplayName("로또 금액이 숫자가 아닌 경우")
            @Test
            void purchaseAmountNotNumber() {
                assertSimpleTest(() -> {
                    runException("1000j");
                    assertThat(output()).contains(ERROR_MESSAGE + ErrorMessage.PURCHASE_AMOUNT_INVALID_FORMAT);
                });
            }

            @DisplayName("로또 금액이 0인 경우")
            @Test
            void purchaseAmountZero() {
                assertSimpleTest(() -> {
                    runException("0");
                    assertThat(output()).contains(ERROR_MESSAGE + ErrorMessage.PURCHASE_AMOUNT_NOT_ZERO);
                });
            }

            @DisplayName("로또 금액이 음수인 경우")
            @Test
            void purchaseAmountNegative() {
                assertSimpleTest(() -> {
                    runException("-1");
                    assertThat(output()).contains(ERROR_MESSAGE + ErrorMessage.PURCHASE_AMOUNT_NOT_NEGATIVE);
                });
            }

            @DisplayName("로또 금액이 나누어 떨어지지 않는 경우")
            @Test
            void purchaseAmountNotDivideUp() {
                assertSimpleTest(() -> {
                    runException("1500");
                    assertThat(output()).contains(ERROR_MESSAGE + ErrorMessage.PURCHASE_AMOUNT_NOT_DIVIDE_UP);
                });
            }
        }

        @DisplayName("당첨 번호")
        @Nested
        class winningNumberTest {

            @DisplayName("당첨 번호가 숫자가 아닌 경우")
            @Test
            void winningNumberNotNumber() {
                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5,육");
                    assertThat(output()).contains(ERROR_MESSAGE + ErrorMessage.WINNING_NUMBER_INVALID_FORMAT);
                });
            }

            @DisplayName("당첨 번호의 개수가 6개보다 작은 경우")
            @Test
            void winningNumberTooFewCount() {
                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5");
                    assertThat(output()).contains(ERROR_MESSAGE + ErrorMessage.WINNING_NUMBER_INVALID_COUNT);
                });
            }

            @DisplayName("당첨 번호의 개수가 6개보다 많은 경우")
            @Test
            void winningNumberTooManyCount() {
                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5,6,7");
                    assertThat(output()).contains(ERROR_MESSAGE + ErrorMessage.WINNING_NUMBER_INVALID_COUNT);
                });
            }

            @DisplayName("당첨 번호가 1보다 작은 경우")
            @Test
            void winningNumberTooSmall() {
                assertSimpleTest(() -> {
                    runException("1000", "0,1,2,3,4,5");
                    assertThat(output()).contains(ERROR_MESSAGE + ErrorMessage.WINNING_NUMBER_OUT_OF_RANGE);
                });
            }

            @DisplayName("당첨 번호가 45보다 큰 경우")
            @Test
            void winningNumberTooBig() {
                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5,46");
                    assertThat(output()).contains(ERROR_MESSAGE + ErrorMessage.WINNING_NUMBER_OUT_OF_RANGE);
                });
            }

            @DisplayName("당첨 번호가 중복되는 경우")
            @Test
            void winningNumberDuplicate() {
                assertSimpleTest(() -> {
                    runException("1000", "1,1,2,3,4,5");
                    assertThat(output()).contains(ERROR_MESSAGE + ErrorMessage.WINNING_NUMBER_NOT_DUPLICATE);
                });
            }
        }

        @DisplayName("보너스 번호")
        @Nested
        class bonusNumberTest {

            @DisplayName("보너스 번호가 숫자가 아닌 경우")
            @Test
            void bonusNumberNotNumber() {
                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5,6", "칠");
                    assertThat(output()).contains(ERROR_MESSAGE + ERROR_MESSAGE.BOUNS_NUMBER_INVALID_FORMAT);
                });
            }

            @DisplayName("보너스 번호가 당첨 번호랑 같은 경우")
            @Test
            void bonusNumberEqualWinningNumber() {
                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5,6", "6");
                    assertThat(output()).contains(ERROR_MESSAGE + ERROR_MESSAGE.BOUNS_NUMBER_NOT_DUPLICATE);
                });
            }

            @DisplayName("보너스 번호가 1보다 작은 경우")
            @Test
            void bonusNumberTooSmall() {
                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5,6", "0");
                    assertThat(output()).contains(ERROR_MESSAGE + ERROR_MESSAGE.BOUNS_NUMBER_TOO_SMALL);
                });
            }

            @DisplayName("보너스 번호가 45보다 큰 경우")
            @Test
            void bonusNumberTooBig() {
                assertSimpleTest(() -> {
                    runException("1000", "1,2,3,4,5,6", "46");
                    assertThat(output()).contains(ERROR_MESSAGE + ERROR_MESSAGE.BOUNS_NUMBER_TOO_BIG);
                });
            }
        }
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
