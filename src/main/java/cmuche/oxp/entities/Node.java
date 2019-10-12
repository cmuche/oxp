package cmuche.oxp.entities;

import com.sun.istack.internal.NotNull;
import lombok.Getter;

public class Node extends OsmElement
{
  @NotNull
  @Getter
  private Coordinate coordinate;

  public Node(String id, Coordinate coordinate)
  {
    super(id);
    this.coordinate = coordinate;
  }
}
