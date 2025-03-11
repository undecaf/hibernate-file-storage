## :warning: This repository is no longer being maintained and has been archived.

# Storing files as BLOBs with Spring, JPA and Hibernate ORM

Application servers may store uploaded files in the server's file system and persist
only the file paths in a database.
Obtaining a consistent backup of such a dataset may be difficult.

This sample project uses Spring, JPA and Hibernate ORM to store uploaded files as BLOBs in a database.
MySQL supports 4GB BLOBs, H2 at least 16GB BLOBs and PostgreSQL even 4TB BLOBs.
BLOBs are never materialized in memory.

Using BLOBs for file storage can be advantageous since this keeps the complete dataset
in a single place (the database);
this provides for consistent backups and synchronization in a database cluster.

Within this project, a few additional techniques are mentioned that might be useful. 

### Subjects

- Uploading files and storing them as
  [BLOBs](https://en.wikipedia.org/wiki/Binary_large_object) with
  [Spring](https://spring.io/),
  [JPA](https://en.wikipedia.org/wiki/Java_Persistence_API) 
  and [Hibernate ORM](https://hibernate.org/orm/)
- Serving and downloading uploaded files
- Hiding primary keys from clients and identifying file entities by
  [UUID](https://en.wikipedia.org/wiki/Universally_unique_identifier)
- Using a
  [mapped superclass](https://www.baeldung.com/hibernate-inheritance#mappedsuperclass)
  as base class for entity classes
- Applying
  [joined table inheritance](https://www.baeldung.com/hibernate-inheritance#joined-table)
  to derive specialized file entities (such as image entities)
- Generating image thumbnails with the
  [Imagick PHP extension](https://www.php.net/manual/en/class.imagick.php)
- Rendering a UI with the [JSP](https://en.wikipedia.org/wiki/JavaServer_Pages)
