package cmuche.oxp.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Way extends OsmElement
{
  @Getter
  private List<Node> nodes;

  public Way(String id)
  {
    this.id = id;
    nodes = new ArrayList<>();
  }
}
