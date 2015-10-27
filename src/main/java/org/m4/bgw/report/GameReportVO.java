package org.m4.bgw.report;

import org.m4.bgw.domain.Boardgame;

public class GameReportVO implements Comparable<GameReportVO> {

private final Boardgame boardgame;
private final int gamesPlayed;
private final int avgLength;

public GameReportVO(Boardgame boardgame, int gamesPlayed, int avgLength) {
    this.boardgame      = boardgame;
    this.gamesPlayed    = gamesPlayed;
    this.avgLength      = avgLength;
}

public Boardgame getBoardgame() {return boardgame; }
public int getGamesPlayed() {return gamesPlayed; }
public int getAvgLength() {return avgLength; }


@Override
public int compareTo(GameReportVO other) {
    return Integer.compare(other.getGamesPlayed(), gamesPlayed);
}
}
