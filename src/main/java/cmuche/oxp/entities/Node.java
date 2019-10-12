package cmuche.oxp.entities;

import lombok.Getter;

public class Node extends OsmElement
{
  @Getter
  private Coordinate coordinate;

  public Node(String id, Coordinate coordinate)
  {
    super(id);
    this.coordinate = coordinate;
  }
}
