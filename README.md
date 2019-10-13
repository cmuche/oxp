# OpenStreetMap XML Parser
- Parses OSM XML files (or any other OSM XML input) and offers an easy to use interface for the basic element types (nodes, ways, relations).
- Stores the data efficiently in memory, no database required
- Offers a powerful API to search for specific OSM data types, tags or features

```
Osm osm = OxpParser.parseOsmFile(new File("map.osm"));
    
// Find all ways that create an area
Set<Way> allAreas = osm.find().ways().isArea().results();
    
// Find all nodes or ways which represent a building
Set<OsmElement> allBuildings = osm.find().tagValueIs("building", "yes").results();

// Find all trees which have their leaf type specified
Set<Node> allTrees = osm.find().nodes().tagValueIs("natural", "tree").hasTag("leaf_type").results();
```
