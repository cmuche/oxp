package cmuche.oxp.query;

import cmuche.oxp.Oxp;
import cmuche.oxp.entities.*;

import java.util.stream.Stream;

public class IntermediateGeneric<T extends OsmElement> extends FindIntermediateTaggable<T>
{
  public IntermediateGeneric(Oxp oxp, Stream currentElements)
  {
    super(oxp, currentElements);
  }

  @Override
  public IntermediateGeneric<T> tagValueIs(String key, String value)
  {
    getTagValueIs(key, value);
    return this;
  }

  @Override
  public IntermediateGeneric<T> tagValueIsNot(String key, String value)
  {
    getTagValueIsNot(key, value);
    return this;
  }

  @Override
  public IntermediateGeneric<T> hasTag(String key)
  {
    getHasTag(key);
    return this;
  }

  public IntermediateSpatial<Spatial> spatials()
  {
    Stream stream = currentElements.filter(x -> x instanceof Spatial);
    return new IntermediateSpatial(oxp, stream);
  }

  public IntermediateRelation<Relation> relations()
  {
    Stream stream = currentElements.filter(x -> x instanceof Relation);
    return new IntermediateRelation(oxp, stream);
  }
}
