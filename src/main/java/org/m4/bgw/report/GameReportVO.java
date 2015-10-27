package org.m4.bgw.report;

import org.m4.bgw.domain.Boardgame;

public class GameReportVO implements Comparable<GameReportVO> {

private final Boardgame boardgame;
private final int gamesPlayed;

public GameReportVO(Boardgame boardgame, int gamesPlayed) {
    this.boardgame      = boardgame;
    this.gamesPlayed    = gamesPlayed;
}

public Boardgame getBoardgame() {return boardgame; }
public int getGamesPlayed() {return gamesPlayed; }


@Override
public int compareTo(GameReportVO other) {
    return Integer.compare(other.getGamesPlayed(), gamesPlayed);
}
}
