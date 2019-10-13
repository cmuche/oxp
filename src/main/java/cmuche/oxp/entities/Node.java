package cmuche.oxp.entities;

import lombok.Getter;
import lombok.NonNull;

public class Node extends Spatial
{
  @NonNull
  @Getter
  private Coordinate coordinate;

  public Node(Id id, Coordinate coordinate)
  {
    super(id);
    this.coordinate = coordinate;
  }
}
