package cmuche.oxp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public abstract class OsmElement
{
  @Getter
  private Id id;

  @Getter
  @EqualsAndHashCode.Exclude
  protected TagCollection tags;

  public OsmElement(Id id)
  {
    this.id = id;
    this.tags = new TagCollection();
  }
}
