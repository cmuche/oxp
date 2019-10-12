package cmuche.oxp.parsing;

import cmuche.oxp.Osm;
import cmuche.oxp.exceptions.OxpException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class OxpParser
{
  public static Osm parseOsmFile(File file) throws OxpException
  {
    try
    {
      String xml = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
      return parseOsmXml(xml);
    }
    catch (IOException ex)
    {
      throw new OxpException("Could not read file: " + ex.getMessage());
    }
  }

  public static Osm parseOsmXml(String xml) throws OxpException
  {
    InputStream stream = IOUtils.toInputStream(xml, StandardCharsets.UTF_8);
    return parseOsmStream(stream);
  }

  public static Osm parseOsmStream(InputStream stream) throws OxpException
  {
    try
    {
      SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
      SAXParser saxParser = saxParserFactory.newSAXParser();
      SaxHandler handler = new SaxHandler();
      saxParser.parse(stream, handler);

      Osm osm = handler.getOsm();
      return osm;
    }
    catch (Exception ex)
    {
      throw new OxpException("Could not parse OSM XML: " + ex.getMessage());
    }
  }
}
