package cmuche.oxp.find;

import cmuche.oxp.Osm;
import cmuche.oxp.entities.Way;

import java.util.stream.Stream;

public class IntermediateWay<T extends Way> extends FindIntermediateTaggable
{
  public IntermediateWay(Osm osm, Stream currentElements)
  {
    super(osm, currentElements);
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

  public FindIntermediate<T> isArea()
  {
    currentElements = currentElements.filter(x -> ((Way) x).isArea());
    return this;
  }
}
