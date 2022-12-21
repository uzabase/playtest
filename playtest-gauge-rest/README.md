# playtest-gauge-rest

## SETUP

### step1

[gauge-javaのmavenプロジェクトを作成](https://github.com/getgauge/gauge-mvn-archetypes)する

### step2

pom.xmlに[playtest-gauge-rest](https://mvnrepository.com/artifact/com.uzabase/playtest-gauge-rest)を追加する

```xml
<dependency>
    <groupId>com.uzabase</groupId>
    <artifactId>playtest-gauge-rest</artifactId>
    <version>0.2.9</version>
</dependency>
```

### step3

`src/test/resources/playtest-gauge-rest.default.properties`を作成する
```text
# example
rest.baseUrl=http://localhost:8080
rest.mockApi.baseUrl=http://localhost:9000
```

お疲れ様でした！以上で準備は完了です:tada:  
楽しいテストライフを:wave:

最新のステップは[こちら](https://uzabase.github.io/playtest/)