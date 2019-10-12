package cmuche.oxp;

import cmuche.oxp.entities.Coordinate;
import cmuche.oxp.entities.Node;
import cmuche.oxp.entities.Way;
import cmuche.oxp.exceptions.OxpException;
import cmuche.oxp.parsing.OxpParser;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

public class ParserTest
{
  private static final String testXml1 = "<osm version='0.6'><node id='1' lat='1.111' lon='2.222'/></osm>";
  private static final String testXml2 = "<osm version='0.6'><node id='1' lat='1.111' lon='2.222'/><node id='2' lat='3.333' lon='4.444'/><way id='3'><nd ref='1'/><nd ref='2'/></way></osm>";

  @Test
  public void testParseNodes() throws OxpException
  {
    Osm osm = OxpParser.parseOsmXml(testXml1);

    Assert.assertEquals(1, osm.getNodes().size());

    Node n = osm.getNodes().stream().findFirst().get();
    Assert.assertEquals("1", n.getId());
    Assert.assertEquals(n.getCoordinate(), new Coordinate(1.111, 2.222));
  }

  @Test
  public void testParseWays() throws OxpException
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
}