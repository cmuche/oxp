package cmuche.oxp.entities;

import lombok.Data;
import lombok.Getter;

@Data(staticConstructor = "at")
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

    return Tile.at(x, y, zoom);
  }

  @Getter
  private final int x;

  @Getter
  private final int y;

  @Getter
  private final int zoom;

  public BoundingBox getBoundingBox()
  {
    double minLat = Math.toDegrees(Math.atan(Math.sinh(Math.PI - (2.0 * Math.PI * (y + 1)) / Math.pow(2.0, zoom))));
    double maxLat = Math.toDegrees(Math.atan(Math.sinh(Math.PI - (2.0 * Math.PI * y) / Math.pow(2.0, zoom))));
    double minLon = x / Math.pow(2.0, zoom) * 360.0 - 180;
    double maxLon = (x + 1) / Math.pow(2.0, zoom) * 360.0 - 180;

    return BoundingBox.of(minLat, maxLat, minLon, maxLon);
  }
}
