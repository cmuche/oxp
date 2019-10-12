package cmuche.oxp;

import cmuche.oxp.entities.*;
import cmuche.oxp.parsing.OxpParser;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

public class ParserTest
{
  private static final String testXml1 = "<osm version='0.6'><node id='1' lat='1.111' lon='2.222'/></osm>";
  private static final String testXml2 = "<osm version='0.6'><node id='1' lat='1.111' lon='2.222'/><node id='2' lat='3.333' lon='4.444'/><way id='3'><nd ref='1'/><nd ref='2'/></way></osm>";
  private static final String testXml3 = "<osm version='0.6'><node id='1' lat='1.111' lon='2.222'/><way id='2'><nd ref='1'/></way><relation id='3'><member type='node' ref='1'/><member type='way' ref='2'/></relation></osm>";
  private static final String testXml4 = "<osm version='0.6'><node id='1' lat='1.111' lon='2.222'><tag k='foo' v='bar'/><tag k='foofoo' v='barbar'/></node></osm>";
  private static final String testXml5 = "<osm version='0.6'><node id='1' lat='1.111' lon='2.222'/><relation id='3'><member type='node' ref='1'/><member type='way' ref='2'/></relation></osm>";
  private static final String testXml6 = "<osm version='0.6'><relation id='3'><member type='node' ref='1'/><member type='way' ref='2'/></relation></osm>";

  @Test
  @SneakyThrows
  public void testParseNodes()
  {
    Osm osm = OxpParser.parseOsmXml(testXml1);

    Assert.assertEquals(1, osm.getNodes().size());

    Node n = osm.getNodes().stream().findFirst().get();
    Assert.assertEquals("1", n.getId());
    Assert.assertEquals(n.getCoordinate(), new Coordinate(1.111, 2.222));
  }

  @Test
  @SneakyThrows
  public void testParseWays()
  {
    Osm osm = OxpParser.parseOsmXml(testXml2);

    Assert.assertEquals(2, osm.getNodes().size());
    Assert.assertEquals(1, osm.getWays().size());

    Way w = osm.getWays().stream().findFirst().get();
    Assert.assertEquals("3", w.getId());
    Assert.assertEquals(2, w.getNodes().size());
    Assert.assertEquals(0, 0);

    Assert.assertTrue(CollectionUtils.isEqualCollection(w.getNodes(), osm.getNodes()));
  }

  @Test
  @SneakyThrows
  public void testParseRelations()
  {
    Osm osm = OxpParser.parseOsmXml(testXml3);

    Assert.assertEquals(1, osm.getRelations().size());

    Relation r = osm.getRelations().stream().findFirst().get();
    Assert.assertEquals(2, r.getMembers().size());

    Assert.assertEquals(MemberType.Node, r.getMembers().get(0).getType());
    Assert.assertEquals(osm.getNodes().stream().findFirst().get(), r.getMembers().get(0).getElement());
    Assert.assertEquals(MemberType.Way, r.getMembers().get(1).getType());
    Assert.assertEquals(osm.getWays().stream().findFirst().get(), r.getMembers().get(1).getElement());
  }

  @Test
  @SneakyThrows
  public void testParseTags()
  {
    Osm osm = OxpParser.parseOsmXml(testXml4);

    Node n = osm.getNodes().stream().findFirst().get();
    Assert.assertEquals(2, n.getTags().countTags());
    Assert.assertEquals("bar", n.getTags().get("foo"));
    Assert.assertEquals("barbar", n.getTags().get("foofoo"));
  }

  @Test
  @SneakyThrows
  public void testMissingRelationMembers()
  {
    Osm osm = OxpParser.parseOsmXml(testXml5);

    Relation r = osm.getRelations().stream().findFirst().get();
    Assert.assertEquals(1, r.getMembers().size());
  }

  @Test
  @SneakyThrows
  public void testEmptyRelations()
  {
    Osm osm = OxpParser.parseOsmXml(testXml6);

    Assert.assertTrue(osm.getRelations().isEmpty());
  }
}