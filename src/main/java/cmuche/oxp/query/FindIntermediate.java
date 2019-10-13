package cmuche.oxp.query;

import cmuche.oxp.Oxp;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class FindIntermediate<T extends Object>
{
  protected Oxp oxp;
  protected Stream<T> currentElements;

  public FindIntermediate(Oxp oxp, Stream<T> currentElements)
  {
    this.oxp = oxp;
    this.currentElements = currentElements;
  }

  public Set<T> go()
  {
    return currentElements.collect(Collectors.toSet());
  }
}
