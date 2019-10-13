package cmuche.oxp.find;

import cmuche.oxp.Osm;
import cmuche.oxp.entities.OsmElement;

import java.util.stream.Stream;

public abstract class FindIntermediateTaggable<T extends OsmElement> extends FindIntermediate
{
  public FindIntermediateTaggable(Osm osm, Stream currentElements)
  {
    super(osm, currentElements);
  }

  public abstract FindIntermediate<T> tagValueIs(String key, String value);

  public abstract FindIntermediate<T> tagValueIsNot(String key, String value);

  public abstract FindIntermediate<T> hasTag(String key);

  protected void getTagValueIs(String key, String value)
  {
    currentElements = currentElements.filter(x -> ((OsmElement) x).getTags().hasKey(key) && ((OsmElement) x).getTags().get(key).equals(value));
  }

  protected void getTagValueIsNot(String key, String value)
  {
    currentElements = currentElements.filter(x -> ((OsmElement) x).getTags().hasKey(key) && !((OsmElement) x).getTags().get(key).equals(value));
  }

  public void getHasTag(String key)
  {
    currentElements = currentElements.filter(x -> ((OsmElement) x).getTags().hasKey(key));
  }
}
