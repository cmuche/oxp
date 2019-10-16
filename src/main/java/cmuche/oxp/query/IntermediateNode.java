package cmuche.oxp.query;


import cmuche.oxp.Oxp;
import cmuche.oxp.entities.BoundingBox;
import cmuche.oxp.entities.Node;
import cmuche.oxp.tagmatch.TagCondition;

import java.util.stream.Stream;

public class IntermediateNode<T extends Node> extends FindIntermediateOsm<T>
{
  public IntermediateNode(Oxp oxp, Stream currentElements)
  {
    super(oxp, currentElements);
  }

  @Override
  public IntermediateNode<T> tagsMatch(TagCondition condition)
  {
    getTagsMatch(condition);
    return this;
  }

  @Override
  public IntermediateNode<T> inBounds(BoundingBox boundingBox)
  {
    getInBounds(boundingBox);
    return this;
  }
}
