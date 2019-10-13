package cmuche.oxp.find;

import cmuche.oxp.Osm;
import cmuche.oxp.entities.Member;
import cmuche.oxp.entities.Relation;

import java.util.stream.Stream;

public class IntermediateRelation<T extends Relation> extends FindIntermediateTaggable<T>
{
  public IntermediateRelation(Osm osm, Stream currentElements)
  {
    super(osm, currentElements);
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
    return new IntermediateMember<>(osm, stream);
  }
}