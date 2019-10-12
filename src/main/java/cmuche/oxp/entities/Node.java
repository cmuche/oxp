package cmuche.oxp.entities;

import lombok.Getter;
import lombok.NonNull;

public class Node extends OsmElement
{
  @NonNull
  @Getter
  private Coordinate coordinate;

  public Node(String id, Coordinate coordinate)
  {
    super(id);
    this.coordinate = coordinate;
  }
}
