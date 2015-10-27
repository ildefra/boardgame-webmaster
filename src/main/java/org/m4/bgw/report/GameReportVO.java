package org.m4.bgw.report;

import org.m4.bgw.domain.Boardgame;

public class GameReportVO implements Comparable<GameReportVO> {

private final Boardgame boardgame;
private final int gamesPlayed;
private final int avgLength;
private final int realAvgLength;

public GameReportVO(Boardgame boardgame, int gamesPlayed, int avgLength, int realAvgLength) {
    this.boardgame      = boardgame;
    this.gamesPlayed    = gamesPlayed;
    this.avgLength      = avgLength;
    this.realAvgLength  = realAvgLength;
}

public Boardgame getBoardgame() {return boardgame; }
public int getGamesPlayed() {return gamesPlayed; }
public int getAvgLength() {return avgLength; }
public int getRealAvgLength() {return realAvgLength; }


@Override
public int compareTo(GameReportVO other) {
    return Integer.compare(other.getGamesPlayed(), gamesPlayed);
}
}
