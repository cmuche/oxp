package cmuche.oxp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public abstract class OsmElement
{
  @Getter
  private String id;

  @Getter
  @EqualsAndHashCode.Exclude
  protected TagCollection tags;

  public OsmElement(String id)
  {
    this.id = id;
    this.tags = new TagCollection();
  }
}
