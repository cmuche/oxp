package cmuche.oxp.query;

import cmuche.oxp.Oxp;
import cmuche.oxp.entities.*;
import cmuche.oxp.tagmatch.TagMatch;
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
    Node n1 = new Node(new Id(ElementType.Node, "1", 1), Coordinate.at(1, 2));
    Node n2 = new Node(new Id(ElementType.Node, "2", 1), Coordinate.at(1, 2));
    Node n3 = new Node(new Id(ElementType.Node, "3", 1), Coordinate.at(1, 2));

    n1.getTags().set("n", "1");
    n2.getTags().set("n", "2");
    n3.getTags().set("n", "3");

    oxp.getNodes().add(n1);
    oxp.getNodes().add(n2);
    oxp.getNodes().add(n3);

    Way w1 = new Way(new Id(ElementType.Way, "1", 1));
    w1.getNodes().add(n1);
    w1.getNodes().add(n2);
    w1.getTags().set("w", "1");
    oxp.getWays().add(w1);

    Way w2 = new Way(new Id(ElementType.Way, "2", 1));
    w2.getNodes().add(n2);
    w2.getNodes().add(n3);
    w2.getTags().set("w", "2");
    oxp.getWays().add(w2);

    Relation r1 = new Relation(new Id(ElementType.Relation, "1", 1));
    r1.getMembers().add(Member.of(ElementType.Node, n1, "foo"));
    r1.getMembers().add(Member.of(ElementType.Way, w1, "bar"));
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
  public void testHasTag()
  {
    Assert.assertTrue(CollectionUtils.isEqualCollection(oxp.getNodes(), oxp.query().tagsMatch(TagMatch.hasTag("n")).go()));
    Assert.assertTrue(CollectionUtils.isEqualCollection(oxp.getWays(), oxp.query().tagsMatch(TagMatch.hasTag("w")).go()));
    Assert.assertTrue(CollectionUtils.isEqualCollection(oxp.getRelations(), oxp.query().tagsMatch(TagMatch.hasTag("r")).go()));
  }

  @Test
  public void testTagValue()
  {
    Set<Node> resTrue = oxp.query().nodes().tagsMatch(TagMatch.tagValueIs("n", "2")).go();
    Set<Node> resFalse = oxp.query().nodes().tagsMatch(TagMatch.tagValueIsNot("n", "2")).go();

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
}
