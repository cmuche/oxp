package cmuche.oxp;

import cmuche.oxp.entities.Node;
import cmuche.oxp.entities.OsmElement;
import cmuche.oxp.entities.Relation;
import cmuche.oxp.entities.Way;
import cmuche.oxp.query.IntermediateGeneric;
import cmuche.oxp.query.IntermediateNode;
import cmuche.oxp.query.IntermediateRelation;
import cmuche.oxp.query.IntermediateWay;
import lombok.Getter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Oxp
{
  @Getter
  private Set<Node> nodes;

  @Getter
  private Set<Way> ways;

  @Getter
  private Set<Relation> relations;

  public Oxp()
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

  public IntermediateGeneric<OsmElement> queryFrom(Collection<OsmElement> scopeElements)
  {
    return new IntermediateGeneric<>(this, scopeElements.stream().parallel());
  }

  public IntermediateNode<Node> queryFromNodes(Collection<Node> scopeElements)
  {
    return new IntermediateNode<>(this, scopeElements.stream().parallel());
  }

  public IntermediateWay<Way> queryFromWays(Collection<Way> scopeElements)
  {
    return new IntermediateWay<>(this, scopeElements.stream().parallel());
  }

  public IntermediateRelation<Relation> queryFromRelations(Collection<Relation> scopeElements)
  {
    return new IntermediateRelation<>(this, scopeElements.stream().parallel());
  }

}
