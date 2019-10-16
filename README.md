# OpenStreetMap XML Parser
- Parses OSM XML files (or any other OSM XML input) and offers an easy to use interface for the basic element types (nodes, ways, relations).
- Stores the data efficiently in memory, no database required
- Offers a powerful API to search for specific OSM data types, tags or features

```
Oxp oxp = OxpParser.parseOsmFile(new File("map.osm"));
    
// Find all ways that create an area
Set<Way> allAreas = oxp.query()
        .ways()
        .isArea()
        .go();

// Find all nodes or ways which represent a building
Set<OsmElement> allBuildings = oxp.query()
        .tagsMatch(TagMatch.and(TagMatch.tagValueIs("building", "yes"), TagMatch.hasTag("leaf_type")))
        .go();

// Find all trees which have their leaf type specified
Set<Node> allTrees = oxp.query().nodes().tagsMatch(TagMatch.tagValueIs("natural", "tree")).go();

// Find all elements which are bus stops and are referenced in a relation which is a bus line and has the line number '36'
Set<OsmElement> stops = oxp.query()
        .relations()
        .tagsMatch(TagMatch.and(TagMatch.tagValueIs("route", "bus"), TagMatch.tagValueIs("ref", "36")))
        .members()
        .elements()
        .tagsMatch(TagMatch.tagValueIs("highway", "bus_stop")).go();

// Find all elements which have an address with an even housenumber (Custom Tag Condition)
Set<OsmElement> evenHousenumbers = oxp.query().tagsMatch(tagCollection ->
    {
      try
      {
        String hnStr = tagCollection.get("addr:housenumber");
        int hn = Integer.valueOf(hnStr);
        return hn % 2 == 0;
      }
      catch (Exception ex)
      {
        return false;
      }
    }).go();
```
