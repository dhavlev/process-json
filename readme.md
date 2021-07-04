# Process Json
A tiny utility extracts value against supplied key

## How it works
1. A user supplies a json string and a key
2. Output is a value against the key

## Usage
```
usage: groovy Main.groovy [required options]
Options:
 -j,--json <json>   Input valid json string - {"a":{"b":{"c":"d"}}}
 -k,--key <key>     Input key - a/b/c
```


### Example
```
groovy Main.groovy -j '{"org":{"name":"lenovo","city":"london","employee":{"total":3030,"avgAge":35,"all":[{"name":"Viktor","age":35},{"name":"Nancy","age":35}]}}}' -k org/employee/all
Output --> [[name:Viktor, age:35], [name:Nancy, age:35]]
```

json:
```json
{
  "org": {
    "name": "lenovo",
    "city": "london",
    "employee": {
      "total": 3030,
      "avgAge": 35,
      "all": [
        {
          "name": "Viktor",
          "age": 35
        },
        {
          "name": "Nancy",
          "age": 35
        }
      ]
    }
  }
}
```
key:
```json
org/employee/all
```

output:
```
[[name:Viktor, age:35], [name:Nancy, age:35]]
```

