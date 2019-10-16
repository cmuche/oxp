package cmuche.oxp.query;

import cmuche.oxp.Oxp;
import cmuche.oxp.entities.*;
import cmuche.oxp.tagmatch.TagCondition;

import java.util.stream.Stream;

public class IntermediateGeneric<T extends OsmElement> extends FindIntermediateOsm<T>
{
  public IntermediateGeneric(Oxp oxp, Stream currentElements)
  {
    super(oxp, currentElements);
  }

  @Override
  public IntermediateGeneric<T> tagsMatch(TagCondition condition)
  {
    getTagsMatch(condition);
    return this;
  }

  public IntermediateWay<Way> ways()
  {
    Stream stream = currentElements.filter(x -> x instanceof Way);
    return new IntermediateWay(oxp, stream);
  }

  public IntermediateNode<Node> nodes()
  {
    Stream stream = currentElements.filter(x -> x instanceof Node);
    return new IntermediateNode(oxp, stream);
  }

  public IntermediateRelation<Relation> relations()
  {
    Stream stream = currentElements.filter(x -> x instanceof Relation);
    return new IntermediateRelation(oxp, stream);
  }

  @Override
  public IntermediateGeneric<T> inBounds(BoundingBox boundingBox)
  {
    getInBounds(boundingBox);
    return this;
  }
}
