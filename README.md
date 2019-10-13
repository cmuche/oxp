# OpenStreetMap XML Parser
- Parses OSM XML files (or any other OSM XML input) and offers an easy to use interface for the basic element types (nodes, ways, relations).
- Stores the data efficiently in memory, no database required
- Offers a powerful API to search for specific OSM data types, tags or features

```
Osm oxp = OxpParser.parseOsmFile(new File("map.oxp"));
    
// Find all ways that create an area
Set<Way> allAreas = oxp.query().ways().isArea().go();
    
// Find all nodes or ways which represent a building
Set<OsmElement> allBuildings = oxp.query().tagValueIs("building", "yes").go();

// Find all trees which have their leaf type specified
Set<Node> allTrees = oxp.query().nodes().tagValueIs("natural", "tree").hasTag("leaf_type").go();

// Find all elements which are bus stops and are referenced in a relation which is a bus line and has the line number '36'
Set<OsmElement> stops = oxp.query().relations().tagValueIs("route", "bus").tagValueIs("ref", "36").members().elements().tagValueIs("highway", "bus_stop").go();
```
