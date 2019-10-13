package cmuche.oxp;

import cmuche.oxp.entities.Node;
import cmuche.oxp.entities.OsmElement;
import cmuche.oxp.entities.Relation;
import cmuche.oxp.entities.Way;
import cmuche.oxp.find.IntermediateGeneric;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

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

  public IntermediateGeneric<OsmElement> query()
  {
    Stream<OsmElement> stream = Stream.concat(Stream.concat(nodes.stream(), ways.stream()), relations.stream());
    return new IntermediateGeneric<>(this, stream);
  }

}
