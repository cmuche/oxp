package cmuche.oxp.find;

import cmuche.oxp.Osm;
import cmuche.oxp.entities.Node;
import cmuche.oxp.entities.OsmElement;
import cmuche.oxp.entities.Relation;
import cmuche.oxp.entities.Way;

import java.util.stream.Stream;

public class IntermediateGeneric<T extends OsmElement> extends FindIntermediateTaggable
{
  public IntermediateGeneric(Osm osm, Stream currentElements)
  {
    super(osm, currentElements);
  }

  @Override
  public IntermediateGeneric<T> tagValueIs(String key, String value)
  {
    getTagValueIs(key, value);
    return this;
  }

  @Override
  public IntermediateGeneric<T> tagValueIsNot(String key, String value)
  {
    getTagValueIsNot(key, value);
    return this;
  }

  @Override
  public IntermediateGeneric<T> hasTag(String key)
  {
    getHasTag(key);
    return this;
  }

  public IntermediateWay<Way> ways()
  {
    Stream stream = currentElements.filter(x -> x instanceof Way);
    return new IntermediateWay(osm, stream);
  }

  public IntermediateNode<Node> nodes()
  {
    Stream stream = currentElements.filter(x -> x instanceof Node);
    return new IntermediateNode(osm, stream);
  }

  public IntermediateRelation<Relation> relations()
  {
    Stream stream = currentElements.filter(x -> x instanceof Relation);
    return new IntermediateRelation(osm, stream);
  }
}
