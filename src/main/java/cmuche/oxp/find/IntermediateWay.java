package cmuche.oxp.find;

import cmuche.oxp.Osm;
import cmuche.oxp.entities.Way;

import java.util.stream.Stream;

public class IntermediateWay<T extends Way> extends FindIntermediate
{
  public IntermediateWay(Osm osm, Stream currentElements)
  {
    super(osm, currentElements);
  }

  @Override
  protected FindIntermediate makeInstance(Osm osm, Stream currentElements)
  {
    return new IntermediateWay(osm, currentElements);
  }

  public FindIntermediate<T> isArea()
  {
    Stream<T> stream = currentElements.filter(x -> ((Way)x).isArea());
    return makeInstance(osm, stream);
  }
}
