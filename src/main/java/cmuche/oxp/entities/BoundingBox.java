package cmuche.oxp.entities;

import lombok.Data;
import lombok.Getter;

@Data(staticConstructor = "of")
public class BoundingBox
{
  @Getter
  private final double latMin;

  @Getter
  private final double latMax;

  @Getter
  private final double lonMin;

  @Getter
  private final double lonMax;

  public boolean intersects(BoundingBox bbox)
  {
    return latMax >= bbox.latMin && lonMax >= bbox.lonMin && bbox.latMax >= latMin && bbox.lonMax >= lonMin;
  }
}
