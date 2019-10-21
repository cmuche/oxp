package cmuche.oxp.entities;

import org.junit.Assert;
import org.junit.Test;

public class CoordinateTest
{
  @Test
  public void testDistance()
  {
    Coordinate c1 = Coordinate.at(1d, 1d);
    Coordinate c2 = Coordinate.at(1d, 1d);
    Coordinate c3 = Coordinate.at(2d, 5d);

    Assert.assertEquals(0, c1.distanceTo(c2), 0);

    Assert.assertEquals(458292.97f, c2.distanceTo(c3), 0);
    Assert.assertEquals(458292.97f, c3.distanceTo(c2), 0);
  }
}
