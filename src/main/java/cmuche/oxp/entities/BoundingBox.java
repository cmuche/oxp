package cmuche.oxp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class BoundingBox
{
  @Getter
  private double latMin;

  @Getter
  private double latMax;

  @Getter
  private double lonMin;

  @Getter
  private double lonMax;

  public boolean intersects(BoundingBox bbox)
  {
    return latMax >= bbox.latMin && lonMax >= bbox.lonMin && bbox.latMax >= latMin && bbox.lonMax >= lonMin;
  }
}
