package cmuche.oxp.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Relation extends OsmElement
{
  @Getter
  private List<Member> members;

  public Relation(Id id)
  {
    super(id);
    members = new ArrayList<>();
  }

  @Override
  public BoundingBox getBoundingBox()
  {
    Set<BoundingBox> bbox = members.stream().map(x -> x.getElement().getBoundingBox()).filter(x -> x != null).collect(Collectors.toSet());

    if (bbox.isEmpty())
      return null;

    double minLat = bbox.stream().map(x -> x.getLatMin()).min(Double::compareTo).get();
    double maxLat = bbox.stream().map(x -> x.getLatMax()).max(Double::compareTo).get();
    double minLon = bbox.stream().map(x -> x.getLonMin()).min(Double::compareTo).get();
    double maxLon = bbox.stream().map(x -> x.getLonMax()).max(Double::compareTo).get();
    return new BoundingBox(minLat, maxLat, minLon, maxLon);
  }
}
