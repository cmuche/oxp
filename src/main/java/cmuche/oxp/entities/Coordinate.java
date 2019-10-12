package cmuche.oxp.entities;

import lombok.Getter;

public class Coordinate
{
  public Coordinate(double lat, double lon)
  {
    this.lat = lat;
    this.lon = lon;
  }

  @Getter
  private double lat;

  @Getter
  private double lon;
}
