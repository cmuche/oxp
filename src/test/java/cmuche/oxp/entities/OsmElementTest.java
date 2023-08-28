package cmuche.oxp.entities;

import org.junit.Assert;
import org.junit.Test;

public class OsmElementTest
{
  @Test
  public void testCenter()
  {
    Node n1 = new Node(new Id(ElementType.Node, "1", 1), Coordinate.at(1d, 2d));
    Node n2 = new Node(new Id(ElementType.Node, "2", 1), Coordinate.at(3d, 4d));
    Way w1 = new Way(new Id(ElementType.Way, "1", 1));
    w1.getNodes().add(n1);
    w1.getNodes().add(n2);
    w1.recalculateBoundingBox();

    Way w = w1;

    Assert.assertEquals(Coordinate.at(2d, 3d), w.getCenter());
  }

}
