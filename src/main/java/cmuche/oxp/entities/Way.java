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
    super(id);
    nodes = new ArrayList<>();
  }

  public boolean isArea()
  {
    if (nodes.size() < 2)
      return false;

    return nodes.get(0).equals(nodes.get(nodes.size() - 1));
  }
}
