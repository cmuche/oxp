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
  protected FindIntermediate makeInstance(Osm osm, Stream stream)
  {
    return new IntermediateNode(osm, stream);
  }
}
