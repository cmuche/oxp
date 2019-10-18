package cmuche.oxp.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TagCollectionTest
{
  private TagCollection tagCollection;

  @Before
  public void init()
  {
    tagCollection = new TagCollection();
    tagCollection.set("s1", "Foo");
    tagCollection.set("s2", "Bar");
    tagCollection.set("i1", "42");
    tagCollection.set("f1", "13.37");
  }

  @Test
  public void testHasAndGet()
  {
    Assert.assertTrue(tagCollection.hasKey("s1"));
    Assert.assertTrue(tagCollection.hasKey("s2"));
    Assert.assertTrue(tagCollection.hasKey("i1"));
    Assert.assertTrue(tagCollection.hasKey("f1"));
    Assert.assertFalse(tagCollection.hasKey("o1"));
    Assert.assertEquals("Foo", tagCollection.get("s1"));
    Assert.assertEquals("Bar", tagCollection.get("s2"));
  }

  @Test
  public void testTypes()
  {
    Assert.assertEquals(42, (int) tagCollection.getInt("i1").get());
    Assert.assertEquals(13.37, tagCollection.getFloat("f1").get(), 0.0001f);
    Assert.assertEquals(42f, tagCollection.getFloat("i1").get(), 0.0001f);
    Assert.assertFalse(tagCollection.getInt("s1").isPresent());
    Assert.assertFalse(tagCollection.getInt("f1").isPresent());
    Assert.assertFalse(tagCollection.getInt("xx").isPresent());
    Assert.assertFalse(tagCollection.getFloat("s1").isPresent());
    Assert.assertFalse(tagCollection.getFloat("xx").isPresent());
  }
}
