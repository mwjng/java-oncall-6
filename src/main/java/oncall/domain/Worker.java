package oncall.domain;

import java.util.Objects;

public class Worker {
    private static final String NAME_LENGTH_ERROR_MESSAGE = "닉네임은 %d글자 이상 %d글자 이하여야 합니다.";
    private static final String SPACE_INPUT_ERROR_MESSAGE = "닉네임에 공백을 포함할 수 없습니다.";

    private static final int MINIMUM_NAME_LENGTH = 2;
    private static final int MAXIMUM_NAME_LENGTH = 5;
    private static final String SPACE = " ";

    private final String nickname;

    private Worker(String nickname) {
        validateLength(nickname);
        validateNotContainsWhiteSpace(nickname);
        this.nickname = nickname;
    }

    public static Worker of(String nickname) {
        return new Worker(nickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Worker other = (Worker) o;
        return Objects.equals(nickname, other.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickname);
    }

    private void validateLength(String nickname) {
        if (isInvalidLength(nickname)) {
            throw new IllegalArgumentException(
                    NAME_LENGTH_ERROR_MESSAGE.formatted(MINIMUM_NAME_LENGTH, MAXIMUM_NAME_LENGTH));
        }
    }

    private boolean isInvalidLength(String nickname) {
        return nickname.length() < MINIMUM_NAME_LENGTH
                || nickname.length() > MAXIMUM_NAME_LENGTH;
    }

    private void validateNotContainsWhiteSpace(String nickname) {
        if (nickname.contains(SPACE)) {
            throw new IllegalArgumentException(SPACE_INPUT_ERROR_MESSAGE);
        }
    }
}
