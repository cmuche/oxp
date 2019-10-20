package cmuche.oxp.entities;

import org.junit.Assert;
import org.junit.Test;

public class BoundingBoxTest
{
  @Test
  public void testWayBoundingBox()
  {
    Node n1 = new Node(new Id(ElementType.Node, "1"), new Coordinate(1d, 2d));
    Node n2 = new Node(new Id(ElementType.Node, "2"), new Coordinate(3d, 4d));
    Way w1 = new Way(new Id(ElementType.Way, "1"));
    w1.getNodes().add(n1);
    w1.getNodes().add(n2);
    w1.recalculateBoundingBox();

    Way w = w1;

    BoundingBox bbox = w.getBoundingBox();
    Assert.assertEquals(1d, bbox.getLatMin(), 0);
    Assert.assertEquals(3d, bbox.getLatMax(), 0);
    Assert.assertEquals(2d, bbox.getLonMin(), 0);
    Assert.assertEquals(4d, bbox.getLonMax(), 0);
  }

  @Test
  public void testBoundingBoxes()
  {
    Assert.assertFalse(new BoundingBox(1, 2, 1, 2).intersects(new BoundingBox(3, 4, 1, 2)));
    Assert.assertFalse(new BoundingBox(3, 4, 1, 2).intersects(new BoundingBox(1, 2, 1, 2)));

    Assert.assertFalse(new BoundingBox(1, 2, 1, 2).intersects(new BoundingBox(3, 4, 3, 4)));
    Assert.assertFalse(new BoundingBox(3, 4, 3, 4).intersects(new BoundingBox(1, 2, 1, 2)));

    Assert.assertTrue(new BoundingBox(1, 4, 1, 4).intersects(new BoundingBox(2, 3, 2, 3)));
    Assert.assertTrue(new BoundingBox(2, 3, 2, 3).intersects(new BoundingBox(1, 4, 1, 4)));

    Assert.assertTrue(new BoundingBox(1, 4, 1, 4).intersects(new BoundingBox(2, 5, 2, 3)));
    Assert.assertTrue(new BoundingBox(2, 5, 2, 3).intersects(new BoundingBox(1, 4, 1, 4)));

    Assert.assertTrue(new BoundingBox(1, 4, 2, 3).intersects(new BoundingBox(2, 3, 1, 4)));
    Assert.assertTrue(new BoundingBox(2, 3, 1, 4).intersects(new BoundingBox(1, 4, 2, 3)));
  }
}
