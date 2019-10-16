package cmuche.oxp.query;

import cmuche.oxp.Oxp;
import cmuche.oxp.entities.BoundingBox;
import cmuche.oxp.entities.Member;
import cmuche.oxp.entities.Relation;
import cmuche.oxp.tagmatch.TagCondition;

import java.util.stream.Stream;

public class IntermediateRelation<T extends Relation> extends FindIntermediateOsm<T>
{
  public IntermediateRelation(Oxp oxp, Stream currentElements)
  {
    super(oxp, currentElements);
  }

  @Override
  public IntermediateRelation<T> tagsMatch(TagCondition condition)
  {
    getTagsMatch(condition);
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
