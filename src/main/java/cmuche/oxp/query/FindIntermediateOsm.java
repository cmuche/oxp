package cmuche.oxp.query;

import cmuche.oxp.Oxp;
import cmuche.oxp.entities.BoundingBox;
import cmuche.oxp.entities.Coordinate;
import cmuche.oxp.entities.OsmElement;
import cmuche.oxp.tagmatch.TagCondition;

import java.util.stream.Stream;

public abstract class FindIntermediateOsm<T extends OsmElement> extends FindIntermediate<T>
{
  public FindIntermediateOsm(Oxp oxp, Stream currentElements)
  {
    super(oxp, currentElements);
  }

  public abstract FindIntermediate<T> tagsMatch(TagCondition condition);

  public abstract FindIntermediate<T> inBounds(BoundingBox bbox);

  public abstract FindIntermediate<T> inRange(Coordinate coord, float range);

  protected void getTagsMatch(TagCondition condition)
  {
    currentElements = currentElements.parallel().filter(x -> condition.matches(x.getTags()));
  }

  protected void getInBounds(BoundingBox bbox)
  {
    currentElements = currentElements.parallel().filter(x -> x.getBoundingBox() != null && bbox.intersects(x.getBoundingBox()));
  }

  protected void getInRange(Coordinate coord, float range)
  {
    currentElements = currentElements.parallel().filter(x -> x.getCenter() != null && x.getCenter().distanceTo(coord) <= range);
  }
}
