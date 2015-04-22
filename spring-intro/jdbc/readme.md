# Spring Data Access

 - Spring has good support for data access and data manipulation.

## Understanding common data access support

 - The offerings Spring provides are similar across different products it supports; there are:
  - **Template objects** makes data objects simple, reduces the code to one liner.
  - **Persistence exception translation** groups exceptions in common hierarchy, no matter which persistence technology you use if the exception is a well known common exception than you get a exception type rather than code. All the data access exceptions that Spring provide are rooted from DataAccessException. *DataAccessException* is an unchecked exception, you don't have to catch it but if you wish you can.
  - Transaction Management
  - Repository (Spring provide the idea of a repository, a repository is an object that know how to read from a dataSource and knows how to handle data access functionality.)
  - Object Mapping and Serialization
  
### Templating Data Access
 - Spring provides templates for data access. This uses the template pattern (also known as Behavioural pattern). **Template Pattern defines the skeleton of an algorithm in a method, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithms structure.** So basically some of the steps are always fixed like opening connection, closing connection and some aren't like the query you want to execute. So a template method defines a skeleton of a process. 
 
 - Spring separates the fixed and variable parts of the data-access process into two distinct classes: *templates* and *callbacks*. Templates manage fixed part of the process whereas your custom data-access code is handled in callbacks.
 
 - Spring comes with several data-access templates, each suitable for a different persistent mechanism. Some of these include JdbcTemplate, NamedParameterJdbcTemplate, HibernateTemplate, JpaTemplate.
 
## Configuring a DataSource
