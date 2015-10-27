package org.m4.bgw.report;

import org.m4.bgw.domain.Country;


public class PrestigeVO implements Comparable<PrestigeVO> {

private final Country country;
private final String username;
private final int points;

public PrestigeVO(Country country, String username, int points) {
    this.country = country;
    this.username = username;
    this.points = points;
}

public Country getCountry() {return country; }
public String getUsername() {return username; }
public int getPoints()      {return points; }

@Override
public int compareTo(PrestigeVO other) {
    return Integer.compare(points, other.getPoints());
}
}
