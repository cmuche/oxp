package cmuche.oxp.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data(staticConstructor = "of")
public class Member
{
  @NonNull
  @Getter
  private final ElementType type;

  @NonNull
  @Getter
  private final OsmElement element;

  @Getter
  private final String role;
}
