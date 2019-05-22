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
