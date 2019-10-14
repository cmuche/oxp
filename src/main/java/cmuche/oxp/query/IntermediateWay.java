package cmuche.oxp.query;

import cmuche.oxp.Oxp;
import cmuche.oxp.entities.BoundingBox;
import cmuche.oxp.entities.Way;

import java.util.stream.Stream;

public class IntermediateWay<T extends Way> extends FindIntermediateTaggable<T>
{
  public IntermediateWay(Oxp oxp, Stream currentElements)
  {
    super(oxp, currentElements);
  }

  @Override
  public IntermediateWay<T> tagValueIs(String key, String value)
  {
    getTagValueIs(key, value);
    return this;
  }

  @Override
  public IntermediateWay<T> tagValueIsNot(String key, String value)
  {
    getTagValueIsNot(key, value);
    return this;
  }

  @Override
  public IntermediateWay<T> hasTag(String key)
  {
    getHasTag(key);
    return this;
  }

  public IntermediateWay<T> isArea()
  {
    currentElements = currentElements.filter(x -> x.isArea());
    return this;
  }

  @Override
  public IntermediateWay<T> inBounds(BoundingBox boundingBox)
  {
    getInBounds(boundingBox);
    return this;
  }
}
