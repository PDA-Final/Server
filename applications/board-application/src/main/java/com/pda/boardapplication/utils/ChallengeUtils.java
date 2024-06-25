package com.pda.boardapplication.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ChallengeUtils {

    public static Map<Long, BoardChallengeInfo> BOARD_CHALLENGES = new HashMap<>() {{
        put(1L, new BoardChallengeInfo(1, 1));
        put(2L, new BoardChallengeInfo(2, 10));
        put(3L, new BoardChallengeInfo(3, 10));
        put(4L, new BoardChallengeInfo(4, 10));
        put(5L, new BoardChallengeInfo(5, 10));
    }};

    public static BoardChallengeInfo getBoardChallengeInfoById(long challengeId) {
        return BOARD_CHALLENGES.get(challengeId);
    }

    public static boolean checkIfBoardChallenge(long challengeId) {
        return BOARD_CHALLENGES.get(challengeId) != null;
    }

    @AllArgsConstructor
    @Getter
    public static class BoardChallengeInfo {
        private int type;
        private int successThreshold;
    }
}
