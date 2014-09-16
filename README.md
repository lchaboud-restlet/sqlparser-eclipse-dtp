Results of the execution of Test.java :

```
SELECT *
  FROM TEST
```
=> SELECT expression is parsed correctly

```
DELETE FROM TEST
```
=> DELETE expression is parsed correctly

```
Unable to parse the input: "CREATE TABLE films (
    code        char(5) CONSTRAINT firstkey PRIMARY KEY,
    title       varchar(40) NOT NULL,
    did         integer NOT NULL,
    date_prod   date,
    kind        varchar(10),
    len         interval hour to minute
);".
```
=> CREATE TABLE is not parsed by Eclipse DTP.