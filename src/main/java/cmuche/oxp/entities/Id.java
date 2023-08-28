package cmuche.oxp.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class Id
{
  @Getter
  private ElementType type;

  @Getter
  private String id;

  @Getter
  private int version;

  @Override
  public String toString()
  {
    return type.toString().toUpperCase() + "-" + id + "(" + version + ")";
  }
}
