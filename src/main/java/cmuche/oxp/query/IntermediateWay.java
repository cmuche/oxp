package cmuche.oxp.query;

import cmuche.oxp.Oxp;
import cmuche.oxp.entities.BoundingBox;
import cmuche.oxp.entities.Way;
import cmuche.oxp.tagmatch.TagCondition;

import java.util.stream.Stream;

public class IntermediateWay<T extends Way> extends FindIntermediateOsm<T>
{
  public IntermediateWay(Oxp oxp, Stream currentElements)
  {
    super(oxp, currentElements);
  }

  @Override
  public IntermediateWay<T> tagsMatch(TagCondition condition)
  {
    getTagsMatch(condition);
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
