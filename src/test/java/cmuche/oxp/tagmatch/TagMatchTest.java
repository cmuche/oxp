package cmuche.oxp.tagmatch;

import cmuche.oxp.entities.TagCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TagMatchTest
{
  private TagCollection tags;

  @Before
  public void init()
  {
    tags = new TagCollection();
    tags.set("k1", "v1");
    tags.set("k2", "v2");
  }

  private void assertMatchTrue(TagCondition condition)
  {
    Assert.assertTrue(condition.matches(tags));
  }

  private void assertMatchFalse(TagCondition condition)
  {
    Assert.assertFalse(condition.matches(tags));
  }

  @Test
  public void testHasTag()
  {
    assertMatchTrue(TagMatch.hasTag("k1"));
    assertMatchTrue(TagMatch.hasTag("k2"));
    assertMatchFalse(TagMatch.hasTag("k3"));
    assertMatchTrue(TagMatch.hasNotTag("k3"));
  }

  @Test
  public void testTagValue()
  {
    assertMatchTrue(TagMatch.tagValueIs("k1", "v1"));
    assertMatchTrue(TagMatch.tagValueIs("k2", "v2"));
    assertMatchFalse(TagMatch.tagValueIs("k1", "xx"));
    assertMatchTrue(TagMatch.tagValueIsNot("k3", "v3"));
  }

  @Test
  public void testOr()
  {
    assertMatchTrue(TagMatch.or(TagMatch.hasTag("k1"), TagMatch.hasTag("k2")));
    assertMatchTrue(TagMatch.or(TagMatch.hasTag("xx"), TagMatch.hasTag("k2")));
    assertMatchTrue(TagMatch.or(TagMatch.hasTag("k1"), TagMatch.hasTag("xx")));
    assertMatchFalse(TagMatch.or(TagMatch.hasTag("xx"), TagMatch.hasTag("yy")));
  }

  @Test
  public void testAnd()
  {
    assertMatchTrue(TagMatch.and(TagMatch.hasTag("k1"), TagMatch.hasTag("k2")));
    assertMatchFalse(TagMatch.and(TagMatch.hasTag("xx"), TagMatch.hasTag("k2")));
    assertMatchFalse(TagMatch.and(TagMatch.hasTag("k1"), TagMatch.hasTag("xx")));
    assertMatchFalse(TagMatch.and(TagMatch.hasTag("xx"), TagMatch.hasTag("yy")));
  }

  @Test
  public void testAny()
  {
    assertMatchTrue(TagMatch.any());
    assertMatchTrue(TagMatch.any(TagMatch.hasTag("k1")));
    assertMatchTrue(TagMatch.any(TagMatch.hasTag("k1"), TagMatch.hasTag("k2")));
    assertMatchTrue(TagMatch.any(TagMatch.hasTag("k1"), TagMatch.hasTag("xx")));
    assertMatchFalse(TagMatch.any(TagMatch.hasTag("xx"), TagMatch.hasTag("yy")));
  }

  @Test
  public void testAll()
  {
    assertMatchTrue(TagMatch.all());
    assertMatchTrue(TagMatch.all(TagMatch.hasTag("k1")));
    assertMatchTrue(TagMatch.all(TagMatch.hasTag("k1"), TagMatch.hasTag("k2")));
    assertMatchFalse(TagMatch.all(TagMatch.hasTag("k1"), TagMatch.hasTag("xx")));
    assertMatchFalse(TagMatch.all(TagMatch.hasTag("xx"), TagMatch.hasTag("k1")));
    assertMatchFalse(TagMatch.all(TagMatch.hasTag("xx"), TagMatch.hasTag("yy")));
  }
}
