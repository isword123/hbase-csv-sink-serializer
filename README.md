## HBase CSV Sink Serializer

### When it comes out
- When we try to use the RegexHbaseEventSerializer to sink CSV data to hbase, if the columns is large, the regex compile progress is very slow
- So we need to simplify the progress to get columns 

### Feature
- Use mostly the same as [RegexHbaseEventSerializer](https://github.com/apache/flume/blob/flume-1.9/flume-ng-sinks/flume-ng-hbase-sink/src/main/java/org/apache/flume/sink/hbase/RegexHbaseEventSerializer.java)
- Add new config `.splitRegex`, the default value is `,`
- Remove config
    - `regexIgnoreCase`
    - `regex`

### Speed test
- Test results
- one row:
    - 19 columns with (.*) regex match pattern: 295ms
    - 19 columns with regex splitter: 0.69 ms
    
### Usage
```properties
pubg.sinks.matchstart.channel=matchstart
pubg.sinks.matchstart.type=hbase
pubg.sinks.matchstart.table=matchstart
pubg.sinks.matchstart.columnFamily=d
pubg.sinks.matchstart.zookeeperQuorum=10.0.0.2:2181
pubg.sinks.matchstart.znodeParent=/hbase
pubg.sinks.matchstart.batchSize=2
pubg.sinks.matchstart.serializer=xia.isword.bigdata.flume.sink.hbase.CSVSerializer
pubg.sinks.matchstart.serializer.splitRegex=,(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)
pubg.sinks.matchstart.serializer.rowKeyIndex=0
pubg.sinks.matchstart.serializer.colNames=ROW_KEY,MatchId,players_count,weatherId,mapName,cameraViewBehaviour,teamSize,isCustomGame,isEventMode,log_time
```
