package cmuche.oxp.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Way extends OsmElement
{
  @Getter
  private List<Node> nodes;

  private BoundingBox bbox;

  public Way(Id id)
  {
    super(id);
    nodes = new ArrayList<>();
  }

  @Override
  public BoundingBox getBoundingBox()
  {
    return bbox;
  }

  public void recalculateBoundingBox()
  {
    if (nodes.isEmpty())
      bbox = null;

    Set<Coordinate> coords = nodes.stream().map(x -> x.getCoordinate()).collect(Collectors.toSet());
    double minLat = coords.stream().map(x -> x.getLat()).min(Double::compareTo).get();
    double maxLat = coords.stream().map(x -> x.getLat()).max(Double::compareTo).get();
    double minLon = coords.stream().map(x -> x.getLon()).min(Double::compareTo).get();
    double maxLon = coords.stream().map(x -> x.getLon()).max(Double::compareTo).get();
    bbox = new BoundingBox(minLat, maxLat, minLon, maxLon);
  }

  public boolean isArea()
  {
    if (nodes.size() < 3)
      return false;

    return nodes.get(0).equals(nodes.get(nodes.size() - 1));
  }

  public float length()
  {
    if (nodes.size() < 2)
      return 0;

    float dist = 0;

    for (int i = 1; i < nodes.size(); i++)
      dist += nodes.get(i - 1).distanceTo(nodes.get(i));

    return dist;
  }
}
