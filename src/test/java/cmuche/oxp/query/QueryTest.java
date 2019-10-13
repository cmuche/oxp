package cmuche.oxp.query;

import cmuche.oxp.Oxp;
import cmuche.oxp.entities.*;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueryTest
{

  private Oxp oxp;

  @Before
  public void init()
  {
    oxp = new Oxp();
    Node n1 = new Node(new Id(ElementType.Node, "1"), new Coordinate(1, 1));
    Node n2 = new Node(new Id(ElementType.Node, "2"), new Coordinate(2, 2));
    Node n3 = new Node(new Id(ElementType.Node, "3"), new Coordinate(3, 3));

    n1.getTags().set("n", "1");
    n2.getTags().set("n", "2");
    n3.getTags().set("n", "3");

    oxp.getNodes().add(n1);
    oxp.getNodes().add(n2);
    oxp.getNodes().add(n3);

    Way w1 = new Way(new Id(ElementType.Way, "1"));
    w1.getNodes().add(n1);
    w1.getNodes().add(n2);
    w1.getTags().set("w", "1");
    oxp.getWays().add(w1);

    Way w2 = new Way(new Id(ElementType.Way, "2"));
    w2.getNodes().add(n2);
    w2.getNodes().add(n3);
    w2.getTags().set("w", "2");
    oxp.getWays().add(w2);

    Relation r1 = new Relation(new Id(ElementType.Relation, "1"));
    r1.getMembers().add(new Member(ElementType.Node, n1, "foo"));
    r1.getMembers().add(new Member(ElementType.Way, w1, "bar"));
    r1.getTags().set("r", "1");
    oxp.getRelations().add(r1);
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

  @Test
  public void testAllRelations()
  {
    Set res = oxp.query().relations().go();
    Assert.assertTrue(CollectionUtils.isEqualCollection(res, oxp.getRelations()));
  }

  @Test
  public void testAllSpatials()
  {
    Set res = oxp.query().spatials().go();
    Assert.assertTrue(CollectionUtils.isEqualCollection(res, Stream.concat(oxp.getNodes().stream(), oxp.getWays().stream()).collect(Collectors.toSet())));
  }

  @Test
  public void testHasTag()
  {
    Assert.assertTrue(CollectionUtils.isEqualCollection(oxp.getNodes(), oxp.query().hasTag("n").go()));
    Assert.assertTrue(CollectionUtils.isEqualCollection(oxp.getWays(), oxp.query().hasTag("w").go()));
    Assert.assertTrue(CollectionUtils.isEqualCollection(oxp.getRelations(), oxp.query().hasTag("r").go()));
  }

  @Test
  public void testTagValue()
  {
    Set<OsmElement> resTrue = oxp.query().tagValueIs("n", "2").go();
    Set<OsmElement> resFalse = oxp.query().tagValueIsNot("n", "2").go();

    Assert.assertEquals(1, resTrue.size());
    Assert.assertEquals(oxp.getNodes().size() - 1, resFalse.size());

    Assert.assertTrue(CollectionUtils.isEqualCollection(oxp.getNodes(), Stream.concat(resTrue.stream(), resFalse.stream()).collect(Collectors.toSet())));
  }

  @Test
  public void testMembers()
  {
    Set<Member> members = oxp.query().relations().members().go();
    Assert.assertEquals(2, members.size());

    Set<Member> membersFoo = oxp.query().relations().members().roleIs("foo").go();
    Assert.assertEquals(1, membersFoo.size());

    Set<OsmElement> elements = oxp.query().relations().members().elements().go();
    Assert.assertEquals(2, elements.size());
  }

  @Test
  public void testInBounds()
  {
    Set<OsmElement> res = oxp.query().spatials().inBounds(new BoundingBox(0, 1.5d, 0, 1.5d)).go();
    Assert.assertEquals(2, res.size());
  }
}
