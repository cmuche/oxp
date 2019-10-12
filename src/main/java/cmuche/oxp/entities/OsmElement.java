package cmuche.oxp.entities;

import lombok.Getter;

public abstract class OsmElement
{
  @Getter
  private String id;

  @Getter
  protected TagCollection tags;

  public OsmElement(String id)
  {
    this.id = id;
    this.tags = new TagCollection();
  }
}
