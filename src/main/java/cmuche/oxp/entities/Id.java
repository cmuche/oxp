package cmuche.oxp.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
public class Id
{
  private ElementType type;
  private String id;
  private int version;

  @Override
  public String toString()
  {
    return type.toString().toUpperCase() + "-" + id + "(" + version + ")";
  }
}
