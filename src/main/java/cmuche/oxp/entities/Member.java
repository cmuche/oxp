package cmuche.oxp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class Member
{
  @NonNull
  @Getter
  private ElementType type;

  @NonNull
  @Getter
  private OsmElement element;

  @Getter
  private String role;
}
