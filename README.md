# GigAdvisor

*Informazioni GigAdvisor mobile application*

Partendo dal presupposto che tale progetto è di una certa complessità, ma tutto è di facile comprensione se letto in un certo modo.

- Gli strumenti utilizzati sono il linguaggio XML per la gestione dell'interfaccia grafica, qualsiasi elemento dell'app è stato creato
utilizzando tale linguaggio. Ovviamente per realizzare alcune piccolezze sono state utilizzate delle librerie, ma non preoccuparti, sono 
già state implementate nel modulo "Gradle Scripts -> build.gradle(module:app)". 

- Per la persistenza dei dati ho utilizzato un database MySQL presente in rete.

- Il linguaggio Java è utilizzato per dar vita all'app e quindi definire tutti i comportamenti e le varie richieste/risposte del caso.

- Sul web server sono presenti gli script scritti in linguaggio PHP che comunicano col DB.

- Utilizzando la libreria Volley (implementata come detto in precedenza) ho creato un canale di comunicazione sicuro e facilmente gestibile,
tale libreria è già implementata. 

- Per la creazione dei grafici ho utilizzato la libreria MPAndroidCharts, da GitHub, essa perfettamente funzionante è già presente nell'app e 
ricca di tutorial. 

- L'utilizzo di phpBB è stato fondamentale per la creazione e gestione del forum.

- L'IDE per lo sviluppo è Android Studio.

- La parte che riguarda la georeferenziazione è stata realizzata mediante Geocoding API.

