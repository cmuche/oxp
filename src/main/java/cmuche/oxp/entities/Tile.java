package cmuche.oxp.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Tile
{
  public static Tile fromCoordinate(Coordinate coord, int zoom)
  {
    int x = (int) Math.floor((coord.getLon() + 180) / 360 * (1 << zoom));
    int y = (int) Math.floor((1 - Math.log(Math.tan(Math.toRadians(coord.getLat())) + 1 / Math.cos(Math.toRadians(coord.getLat()))) / Math.PI) / 2 * (1 << zoom));
    if (x < 0)
      x = 0;
    if (x >= (1 << zoom))
      x = ((1 << zoom) - 1);
    if (y < 0)
      y = 0;
    if (y >= (1 << zoom))
      y = ((1 << zoom) - 1);

    return new Tile(x, y, zoom);
  }

  @Getter
  private int x;

  @Getter
  private int y;

  @Getter
  private int zoom;

  public BoundingBox getBoundingBox()
  {
    double minLat = Math.toDegrees(Math.atan(Math.sinh(Math.PI - (2.0 * Math.PI * (y + 1)) / Math.pow(2.0, zoom))));
    double maxLat = Math.toDegrees(Math.atan(Math.sinh(Math.PI - (2.0 * Math.PI * y) / Math.pow(2.0, zoom))));
    double minLon = x / Math.pow(2.0, zoom) * 360.0 - 180;
    double maxLon = (x + 1) / Math.pow(2.0, zoom) * 360.0 - 180;

    return new BoundingBox(minLat, maxLat, minLon, maxLon);
  }
}
