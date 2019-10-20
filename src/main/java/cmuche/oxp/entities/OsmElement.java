package cmuche.oxp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public abstract class OsmElement
{
  @Getter
  private Id id;

  @Getter
  @EqualsAndHashCode.Exclude
  protected TagCollection tags;

  public OsmElement(Id id)
  {
    this.id = id;
    this.tags = new TagCollection();
  }

  public abstract BoundingBox getBoundingBox();

  public Coordinate getCenter()
  {
    BoundingBox bbox = getBoundingBox();
    double centerLat = (bbox.getLatMin() + bbox.getLatMax()) / 2d;
    double centerLon = (bbox.getLonMin() + bbox.getLonMax()) / 2d;
    return new Coordinate(centerLat, centerLon);
  }

  public float distanceTo(OsmElement other)
  {
    return getCenter().distanceTo(other.getCenter());
  }
}
