package cmuche.oxp.query;

import cmuche.oxp.Oxp;
import cmuche.oxp.entities.BoundingBox;
import cmuche.oxp.entities.Member;
import cmuche.oxp.entities.Relation;

import java.util.stream.Stream;

public class IntermediateRelation<T extends Relation> extends FindIntermediateOsm<T>
{
  public IntermediateRelation(Oxp oxp, Stream currentElements)
  {
    super(oxp, currentElements);
  }

  @Override
  public IntermediateRelation<T> tagValueIs(String key, String value)
  {
    getTagValueIs(key, value);
    return this;
  }

  @Override
  public IntermediateRelation<T> tagValueIsNot(String key, String value)
  {
    getTagValueIsNot(key, value);
    return this;
  }

  @Override
  public IntermediateRelation<T> hasTag(String key)
  {
    getHasTag(key);
    return this;
  }

  public IntermediateMember<Member> members()
  {
    Stream<Member> stream = currentElements.flatMap(x -> x.getMembers().stream());
    return new IntermediateMember<>(oxp, stream);
  }

  @Override
  public IntermediateRelation<T> inBounds(BoundingBox boundingBox)
  {
    getInBounds(boundingBox);
    return this;
  }
}
