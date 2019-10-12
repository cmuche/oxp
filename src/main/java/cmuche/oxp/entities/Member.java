package cmuche.oxp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Member
{
  @Getter
  private MemberType type;

  @Getter
  private OsmElement element;
}
