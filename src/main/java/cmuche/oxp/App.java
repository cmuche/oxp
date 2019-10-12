package cmuche.oxp;

import cmuche.oxp.parsing.OxpParser;

import java.io.File;

public class App
{
  public static void main(String[] args) throws Exception
  {
    Osm osm = OxpParser.parseOsmFile(new File("map.osm"));
  }
}
