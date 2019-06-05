# QuantilChallenge

The QuantilChallenge comprises of two Java applications. One to generate logs files in a directory specifying the CPU usage at a specific time stamp. The other Java application is a query application which loads a specific directory and simulate a shell prompt that accepts queries which return results within the desired timestamp.

## Built With 
* [Java](https://www.java.com/en/)

## Compilation

In "src" folder
```
javac *.java
```

## Usage 

### Simulator APP

```
java Simulator [path]
```

For example, I used the "/log" directory to store all my generated logs in there

```
java Simulator log
```

### Query APP

```
java Query [path]
```

For example, I want to read all my logs in the "/log" directory

```
java Query log
```

If the directory exists, the shell will start asking for a query. 

```
QUERY> [IP ADDR] [CPU-ID] [DATE OF LOG CREATION] [HH:MM] [DATE OF LOG CREATION] [HH:MM]
```

For example, 

```
QUERY> 192.168.1.12 1 2019-06-04 00:00 2019-06-04 24:00
```

will return an output like this

```
CPU1 usage on 192.169.1.12:
(2019-06-04 00:00, 40%), (2019-06-04 00:01, 34%), (2019-06-04 00:02, 62%), (2019-06-04 00:03, 64%),
(2019-06-04 00:04, 57%), ..., (2019-06-04 00:05, 51%)
```
