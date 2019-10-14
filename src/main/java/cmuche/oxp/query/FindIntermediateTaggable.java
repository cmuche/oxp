package cmuche.oxp.query;

import cmuche.oxp.Oxp;
import cmuche.oxp.entities.BoundingBox;
import cmuche.oxp.entities.OsmElement;

import java.util.stream.Stream;

public abstract class FindIntermediateTaggable<T extends OsmElement> extends FindIntermediate<T>
{
  public FindIntermediateTaggable(Oxp oxp, Stream currentElements)
  {
    super(oxp, currentElements);
  }

  public abstract FindIntermediate<T> tagValueIs(String key, String value);

  public abstract FindIntermediate<T> tagValueIsNot(String key, String value);

  public abstract FindIntermediate<T> hasTag(String key);

  public abstract FindIntermediate<T> inBounds(BoundingBox bbox);

  protected void getTagValueIs(String key, String value)
  {
    currentElements = currentElements.filter(x -> x.getTags().hasKey(key) && x.getTags().get(key).equals(value));
  }

  protected void getTagValueIsNot(String key, String value)
  {
    currentElements = currentElements.filter(x -> x.getTags().hasKey(key) && !x.getTags().get(key).equals(value));
  }

  protected void getHasTag(String key)
  {
    currentElements = currentElements.filter(x -> x.getTags().hasKey(key));
  }

  protected void getInBounds(BoundingBox bbox)
  {
    currentElements = currentElements.filter(x -> x.getBoundingBox() != null && bbox.intersects(x.getBoundingBox()));
  }
}
