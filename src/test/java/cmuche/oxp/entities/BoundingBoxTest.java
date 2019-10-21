package cmuche.oxp.entities;

import org.junit.Assert;
import org.junit.Test;

public class BoundingBoxTest
{
  @Test
  public void testWayBoundingBox()
  {
    Node n1 = new Node(new Id(ElementType.Node, "1"), Coordinate.at(1d, 2d));
    Node n2 = new Node(new Id(ElementType.Node, "2"), Coordinate.at(3d, 4d));
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
    Assert.assertFalse(BoundingBox.of(1, 2, 1, 2).intersects(BoundingBox.of(3, 4, 1, 2)));
    Assert.assertFalse(BoundingBox.of(3, 4, 1, 2).intersects(BoundingBox.of(1, 2, 1, 2)));

    Assert.assertFalse(BoundingBox.of(1, 2, 1, 2).intersects(BoundingBox.of(3, 4, 3, 4)));
    Assert.assertFalse(BoundingBox.of(3, 4, 3, 4).intersects(BoundingBox.of(1, 2, 1, 2)));

    Assert.assertTrue(BoundingBox.of(1, 4, 1, 4).intersects(BoundingBox.of(2, 3, 2, 3)));
    Assert.assertTrue(BoundingBox.of(2, 3, 2, 3).intersects(BoundingBox.of(1, 4, 1, 4)));

    Assert.assertTrue(BoundingBox.of(1, 4, 1, 4).intersects(BoundingBox.of(2, 5, 2, 3)));
    Assert.assertTrue(BoundingBox.of(2, 5, 2, 3).intersects(BoundingBox.of(1, 4, 1, 4)));

    Assert.assertTrue(BoundingBox.of(1, 4, 2, 3).intersects(BoundingBox.of(2, 3, 1, 4)));
    Assert.assertTrue(BoundingBox.of(2, 3, 1, 4).intersects(BoundingBox.of(1, 4, 2, 3)));
  }
}
