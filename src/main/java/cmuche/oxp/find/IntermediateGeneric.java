package cmuche.oxp.find;

import cmuche.oxp.Osm;
import cmuche.oxp.entities.OsmElement;
import cmuche.oxp.entities.Way;

import java.util.stream.Stream;

public class IntermediateGeneric<T extends OsmElement> extends FindIntermediate
{
  public IntermediateGeneric(Osm osm, Stream currentElements)
  {
    super(osm, currentElements);
  }

  @Override
  protected FindIntermediate makeInstance(Osm osm, Stream currentElements)
  {
    return new IntermediateGeneric(osm, currentElements);
  }

  public IntermediateWay<Way> ways()
  {
    Stream stream = currentElements.filter(x -> x instanceof Way);
    return new IntermediateWay<>(osm, stream);
  }
}
