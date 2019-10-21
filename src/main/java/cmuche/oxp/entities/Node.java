package cmuche.oxp.entities;

import lombok.Getter;
import lombok.NonNull;

public class Node extends OsmElement
{
  @NonNull
  @Getter
  private Coordinate coordinate;

  public Node(Id id, Coordinate coordinate)
  {
    super(id);
    this.coordinate = coordinate;
  }

  @Override
  public BoundingBox getBoundingBox()
  {
    return BoundingBox.of(coordinate.getLat(), coordinate.getLat(), coordinate.getLon(), coordinate.getLon());
  }
}
