package cmuche.oxp.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
  public boolean equals(Object o)
  {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    Id id1 = (Id) o;

    return new EqualsBuilder().append(type, id1.type).append(id, id1.id).isEquals();
  }

  @Override
  public int hashCode()
  {
    return new HashCodeBuilder(17, 37).append(type).append(id).toHashCode();
  }

  @Override
  public String toString()
  {
    return type.toString().toUpperCase() + "-" + id + "(" + version + ")";
  }
}
