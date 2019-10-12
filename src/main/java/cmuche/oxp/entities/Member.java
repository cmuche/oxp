package cmuche.oxp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class Member
{
  @NonNull
  @Getter
  private MemberType type;

  @NonNull
  @Getter
  private OsmElement element;
}
