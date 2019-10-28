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
    Set<BoundingBox> bbox = members.stream().parallel().map(x -> x.getElement().getBoundingBox()).filter(x -> x != null).collect(Collectors.toSet());

    if (bbox.isEmpty())
      return null;

    double minLat = bbox.stream().parallel().map(x -> x.getLatMin()).min(Double::compareTo).get();
    double maxLat = bbox.stream().parallel().map(x -> x.getLatMax()).max(Double::compareTo).get();
    double minLon = bbox.stream().parallel().map(x -> x.getLonMin()).min(Double::compareTo).get();
    double maxLon = bbox.stream().parallel().map(x -> x.getLonMax()).max(Double::compareTo).get();
    return BoundingBox.of(minLat, maxLat, minLon, maxLon);
  }
}
