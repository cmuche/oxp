package cmuche.oxp.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class Coordinate
{
  @Getter
  private double lat;

  @Getter
  private double lon;

  public float distanceTo(Coordinate coord)
  {
    double theta = lon - coord.getLon();
    double dist = Math.sin(deg2rad(lat)) * Math.sin(deg2rad(coord.getLat())) + Math.cos(deg2rad(lat)) * Math.cos(deg2rad(coord.getLat())) * Math.cos(deg2rad(theta));
    dist = Math.acos(dist) * 180d / Math.PI;
    dist = dist * 60d * 1.1515d * 1.609344d * 1000d;
    return (float) dist;
  }

  private static double deg2rad(double deg)
  {
    return (deg * Math.PI / 180d);
  }

}
