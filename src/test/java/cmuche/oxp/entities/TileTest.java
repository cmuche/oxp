package cmuche.oxp.entities;

import org.junit.Assert;
import org.junit.Test;

public class TileTest
{
  @Test
  public void testCoordToTile()
  {
    Assert.assertEquals(Tile.at(18518, 15252, 15), Tile.fromCoordinate(Coordinate.at(12.34d, 23.45d), 15));
    Assert.assertEquals(Tile.at(296295, 244032, 19), Tile.fromCoordinate(Coordinate.at(12.34d, 23.45d), 19));
    Assert.assertEquals(Tile.at(1, 1, 1), Tile.fromCoordinate(Coordinate.at(0, 0), 1));
  }

  @Test
  public void testBoundingBox()
  {
    Tile tile = Tile.at(70406, 42987, 17);
    BoundingBox bbox = tile.getBoundingBox();
    BoundingBox expected = BoundingBox.of(52.516220864, 52.517892228, 13.375854492, 13.378601074);
    Assert.assertEquals(expected.getLatMin(), bbox.getLatMin(), 0.00001d);
    Assert.assertEquals(expected.getLatMax(), bbox.getLatMax(), 0.00001d);
    Assert.assertEquals(expected.getLonMin(), bbox.getLonMin(), 0.00001d);
    Assert.assertEquals(expected.getLonMax(), bbox.getLonMax(), 0.00001d);
  }
}
