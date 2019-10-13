package cmuche.oxp.entities;

public abstract class Spatial extends OsmElement
{
  public Spatial(Id id)
  {
    super(id);
  }

  public abstract BoundingBox getBoundingBox();
}
