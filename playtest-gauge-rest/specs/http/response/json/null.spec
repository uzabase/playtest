# レスポンスのJSONのnullをアサートできる

## JSON Path
* レスポンスボディとしてシナリオデータストアに"{ \"key1\": null, \"key2\": [ null ] }"を保存する
* レスポンスのJSONの"$.key1"がnullである
* レスポンスのJSONの"$.key2[0]"がnullである
