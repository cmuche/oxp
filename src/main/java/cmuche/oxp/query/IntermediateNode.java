package cmuche.oxp.query;


import cmuche.oxp.Oxp;
import cmuche.oxp.entities.Node;

import java.util.stream.Stream;

public class IntermediateNode<T extends Node> extends FindIntermediateTaggable<T>
{
  public IntermediateNode(Oxp oxp, Stream currentElements)
  {
    super(oxp, currentElements);
  }

  @Override
  public IntermediateNode<T> tagValueIs(String key, String value)
  {
    getTagValueIs(key, value);
    return this;
  }

  @Override
  public IntermediateNode<T> tagValueIsNot(String key, String value)
  {
    getTagValueIsNot(key, value);
    return this;
  }

  @Override
  public IntermediateNode<T> hasTag(String key)
  {
    getHasTag(key);
    return this;
  }

}
