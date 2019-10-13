package cmuche.oxp;

import cmuche.oxp.entities.*;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class FindTest
{

  private Osm osm;

  @Before
  public void init()
  {
    osm = new Osm();
    Node n1 = new Node(new Id(ElementType.Node, "1"), new Coordinate(1, 2));
    Node n2 = new Node(new Id(ElementType.Node, "2"), new Coordinate(1, 2));
    Node n3 = new Node(new Id(ElementType.Node, "3"), new Coordinate(1, 2));
    osm.getNodes().add(n1);
    osm.getNodes().add(n2);
    osm.getNodes().add(n3);

    Way w1 = new Way(new Id(ElementType.Way, "1"));
    w1.getNodes().add(n1);
    w1.getNodes().add(n2);
    osm.getWays().add(w1);

    Way w2 = new Way(new Id(ElementType.Way, "2"));
    w1.getNodes().add(n2);
    w1.getNodes().add(n3);
    osm.getWays().add(w2);
  }

  @Test
  public void testAllNodes()
  {
    Set res = osm.find().nodes().results();
    Assert.assertTrue(CollectionUtils.isEqualCollection(res, osm.getNodes()));
  }

  @Test
  public void testAllWays()
  {
    Set res = osm.find().ways().results();
    Assert.assertTrue(CollectionUtils.isEqualCollection(res, osm.getWays()));
  }
}
