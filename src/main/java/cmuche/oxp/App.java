package cmuche.oxp;

import cmuche.oxp.parsing.SaxHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class App
{
  public static void main(String[] args) throws Exception
  {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    SAXParser saxParser = saxParserFactory.newSAXParser();
    SaxHandler handler = new SaxHandler();
    saxParser.parse(new File("map.osm"), handler);

    Osm osm = handler.getOsm();
  }
}
