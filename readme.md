#KVB Rad Live

Dieser Service liest den aktuellen Standort der von den Kölner Verkehrsbetrieben betriebenen Fahrräder unter [Aktuelle Standorte Fahrraeder KVB-Rad](http://nextbike.net/maps/nextbike-live.xml?city=14) aus und deserialisisert sie in Java-Objekte. Hierfür wird die Bibliothek [FasterXML/jackson-dataformat-xml](https://github.com/FasterXML/jackson-dataformat-xml) verwendet. 

Zum Betrieb ist eine Postgres + Postgis notwendig. Wenn sich die Position eines Rades gegenüber dem letzten Aufruf geändert hat, wird das Ergebnis in die Datenbank geschrieben.

##Installation

mvn clean install

##Datenbank

Dieser Service unterstellt, dass die Datenbankverbindung per JNDI gesetzt ist. Für den Server bedeutet dies, dass der Container für die Definition der DB-Verbindung zurständig ist. Dafür muss z.B. im Tomcat die Datei context.xml angepasst werden. Folgende Einstellungen müssen eingetragen werden.

    <Resource 
      name="jdbc/kvbraeder" 
      auth="Container" 
      type="javax.sql.DataSource"
      username="username"
      password="password"
      driverClassName="org.postgresql.Driver"
      url="jdbc:postgresql://server:5432/dbname" 
      maxTotal="25" 
      maxIdle="10"
      validationQuery="select 1" />

Zur Zeit wird nur eine Tabelle verwendet; DDL:

	CREATE TABLE bike (
	    uid                          integer,
	    name         varchar(256),
	    bike         integer DEFAULT 0,
	    number       integer NOT NULL,
	    modtime      timestamp DEFAULT current_timestamp
	);
	SELECT AddGeometryColumn ('public','bike','geom',4326,'POINT',2);

Für das Logging der Abfragen wird folgende Tabelle benötigt:

    CREATE TABLE kvbradlive (
        numberofinsert      integer,
        modtime      timestamp DEFAULT current_timestamp
    );

## REST Endpoints

Folgende REST Endpoints stehen zur Verfügung

### /kvbradlive/service
### /kvbradlive/service/datatable
### /kvbradlive/service/bikesmap
### /kvbradlive/service/bike/&lt;number&gt;
### /kvbradlive/service/allbikeslatestposition/bikeslist

### /kvbradlive/service/put

Mit diesem REST-Endpoint wird das Auslesen der Schnittstelle ausgelöst und ggf. die Werte in die Datenbank eingefügt. Vorgehen:

1. AskForNextBikeLife
2. Get the Bikes
3. Insert Bikes to DB;
   check for existing bike before inserting

### /kvbradlive/service/delete

Mit diesem REST-Endpoint werden die Einträge der letzten 30 Tage gelöscht.

## Test

### Tests mit Datenbank

Da zur Zeit keine Integration Test Stage zur Verfügung steht, sind alle Tests, die eineDatenbank voraussetzt als main codiert. Um eine Datenbankverbindung hierfür zur Verfügung stellen zu können, muss die Datei src/test/resources/jndi.properties.template in src/test/resources/jndi.properties kopiert und die entsprechenden Parameter zur Datenbank gesetzt werden.