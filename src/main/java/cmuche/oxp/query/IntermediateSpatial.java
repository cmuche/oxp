package cmuche.oxp.query;

import cmuche.oxp.Oxp;
import cmuche.oxp.entities.BoundingBox;
import cmuche.oxp.entities.OsmElement;
import cmuche.oxp.entities.Spatial;

import java.util.stream.Stream;

public class IntermediateSpatial<T extends OsmElement> extends FindIntermediateTaggable<OsmElement>
{
  public IntermediateSpatial(Oxp oxp, Stream currentElements)
  {
    super(oxp, currentElements);
  }

  @Override
  public IntermediateSpatial<T> tagValueIs(String key, String value)
  {
    getTagValueIs(key, value);
    return this;
  }

  @Override
  public IntermediateSpatial<T> tagValueIsNot(String key, String value)
  {
    getTagValueIsNot(key, value);
    return this;
  }

  @Override
  public IntermediateSpatial<T> hasTag(String key)
  {
    getHasTag(key);
    return this;
  }

  public IntermediateSpatial<T> inBounds(BoundingBox bbox)
  {
    currentElements = currentElements.filter(x -> bbox.intersects(((Spatial) x).getBoundingBox()));
    return this;
  }
}
