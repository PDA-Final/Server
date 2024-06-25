package com.pda.boardapplication.exceptions;

public class LockedBoardException extends RuntimeException {
    private int unlockedCount;
    private int likeCount;

    public LockedBoardException() {}

    /**
     * Exception with statistics data
     * @param unlockedCount how many users unlocked target item
     * @param likeCount how many users liked target item
     */
    public LockedBoardException(int unlockedCount, int likeCount) {
        this.unlockedCount = unlockedCount;
        this.likeCount = likeCount;
    }

    public int getUnlockedCount() {
        return this.unlockedCount;
    }

    public int getLikeCount() {
        return this.likeCount;
    }
}
