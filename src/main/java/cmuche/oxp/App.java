package cmuche.oxp;

import cmuche.oxp.entities.Way;
import cmuche.oxp.parsing.OxpParser;

import java.io.File;
import java.util.Set;

public class App
{
  public static void main(String[] args) throws Exception
  {
    Osm osm = OxpParser.parseOsmFile(new File("map.osm"));
    Set<Way> areas = osm.find().ways().isArea().results();
  }
}
