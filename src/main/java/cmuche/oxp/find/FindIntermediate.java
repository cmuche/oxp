package cmuche.oxp.find;

import cmuche.oxp.Osm;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class FindIntermediate<T extends Object>
{
  protected Osm osm;
  protected Stream<T> currentElements;

  public FindIntermediate(Osm osm, Stream<T> currentElements)
  {
    this.osm = osm;
    this.currentElements = currentElements;
  }

  public Set<T> go()
  {
    return currentElements.collect(Collectors.toSet());
  }
}
