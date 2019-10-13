package cmuche.oxp.find;


import cmuche.oxp.Osm;
import cmuche.oxp.entities.Node;

import java.util.stream.Stream;

public class IntermediateNode<T extends Node> extends FindIntermediate
{
  public IntermediateNode(Osm osm, Stream currentElements)
  {
    super(osm, currentElements);
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
