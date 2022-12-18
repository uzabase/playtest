# playtest-gauge-db

## SETUP

### step1

[gauge-javaのmavenプロジェクトを作成](https://github.com/getgauge/gauge-mvn-archetypes)する

### step2

pom.xmlに[playtest-gauge-db](https://mvnrepository.com/artifact/com.uzabase/playtest-gauge-db)を追加する

```xml
<dependency>
    <groupId>com.uzabase</groupId>
    <artifactId>playtest-gauge-db</artifactId>
    <version>0.2.7</version>
</dependency>
```

### step3

`src/test/resources/playtest-gauge-db.default.properties`を作成する
```text
# example
db.expert_db.driverClass=org.postgresql.Driver
db.expert_db.url=jdbc:postgresql://localhost:5432/test
db.expert_db.user=user
db.expert_db.password=password
db.expert_db.schema=default
```

お疲れ様でした！以上で準備は完了です:tada:  
楽しいテストライフを:wave:

最新のステップは[こちら](https://uzabase.github.io/playtest/)