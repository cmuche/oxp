package cmuche.oxp.query;

import cmuche.oxp.Oxp;
import cmuche.oxp.entities.*;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class QueryTest
{

  private Oxp oxp;

  @Before
  public void init()
  {
    oxp = new Oxp();
    Node n1 = new Node(new Id(ElementType.Node, "1"), new Coordinate(1, 2));
    Node n2 = new Node(new Id(ElementType.Node, "2"), new Coordinate(1, 2));
    Node n3 = new Node(new Id(ElementType.Node, "3"), new Coordinate(1, 2));
    oxp.getNodes().add(n1);
    oxp.getNodes().add(n2);
    oxp.getNodes().add(n3);

    Way w1 = new Way(new Id(ElementType.Way, "1"));
    w1.getNodes().add(n1);
    w1.getNodes().add(n2);
    oxp.getWays().add(w1);

    Way w2 = new Way(new Id(ElementType.Way, "2"));
    w1.getNodes().add(n2);
    w1.getNodes().add(n3);
    oxp.getWays().add(w2);
  }

  @Test
  public void testAllNodes()
  {
    Set res = oxp.query().nodes().go();
    Assert.assertTrue(CollectionUtils.isEqualCollection(res, oxp.getNodes()));
  }

  @Test
  public void testAllWays()
  {
    Set res = oxp.query().ways().go();
    Assert.assertTrue(CollectionUtils.isEqualCollection(res, oxp.getWays()));
  }
}
