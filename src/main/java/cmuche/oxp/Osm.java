package cmuche.oxp;

import cmuche.oxp.entities.Node;
import cmuche.oxp.entities.Relation;
import cmuche.oxp.entities.Way;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class Osm
{
  @Getter
  private Set<Node> nodes;

  @Getter
  private Set<Way> ways;

  @Getter
  private Set<Relation> relations;

  public Osm()
  {
    nodes = new HashSet<>();
    ways = new HashSet<>();
    relations = new HashSet<>();
  }
}
