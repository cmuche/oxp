package cmuche.oxp.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Relation extends OsmElement
{
  @Getter
  private List<Member> members;

  public Relation(String id)
  {
    super(id);
    members = new ArrayList<>();
  }
}
