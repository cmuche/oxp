package cmuche.oxp.entities;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Member
{
  @NotNull
  @Getter
  private MemberType type;

  @NotNull
  @Getter
  private OsmElement element;
}
