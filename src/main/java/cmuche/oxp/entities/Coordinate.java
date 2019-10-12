package cmuche.oxp.entities;

import lombok.Getter;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Coordinate that = (Coordinate) o;
    return Double.compare(that.lat, lat) == 0 && Double.compare(that.lon, lon) == 0;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(lat, lon);
  }
}
