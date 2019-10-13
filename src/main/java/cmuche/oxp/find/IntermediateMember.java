package cmuche.oxp.find;

import cmuche.oxp.Osm;
import cmuche.oxp.entities.Member;
import cmuche.oxp.entities.OsmElement;

import java.util.stream.Stream;

public class IntermediateMember<T extends Member> extends FindIntermediate<T>
{
  public IntermediateMember(Osm osm, Stream currentElements)
  {
    super(osm, currentElements);
  }

  public IntermediateMember<T> roleIs(String role)
  {
    currentElements = currentElements.filter(x -> x.getRole() != null && x.getRole().equals(role));
    return this;
  }

  public IntermediateGeneric<OsmElement> elements()
  {
    Stream<OsmElement> newStream = currentElements.map(member -> member.getElement());
    return new IntermediateGeneric<>(osm, newStream);
  }

}
