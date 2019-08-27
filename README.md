# CIC-challenge-emissions
Backend development challenge for CIC (emissions)

The project was built developed in eclipse using maven.
To use it simply import the project as maven project.

## RESTInterface

When building the project locally, the Rest-Service is availible at:
http://localhost:8080/emission/RESTInterface

Calling the service without any parameters will return all data availible as a JSON Array (getAll).

### Data

The data is saved in 3-tuples containing 2 text and one number values.
* department: text, name of the department.
* commodity: text, type of energy source.
* emission: emissions accounted for in metric tons of carbon dioxide equivalents.

For more details about the data, see the full data set at 
https://data.sfgov.org/Energy-and-Environment/San-Francisco-Municipal-Greenhouse-Gas-Inventory/pxac-sadh.
All data used here is a subset of the data from this website.

### Filter Parameters

**Text values** can be filtered for a specific occurence. 
To use the filter, the parameter with the name as key and value simply needs to be added to the url:

*url*?*key*=*value*

To filter for the department "Port" the following url is used:

*url*?department=Port

**Number values** can be filtered with all common comparison operators (=,>,<,>=,<=).
To use the filter the parameter with the name of the key and value with preceeding operator simply needs to be added to the url:

*url*?*key*=*operatorvalue*

To filter for emissions higher than 1.5 the following url is used:
*url*?emission=>1.5

If no operator is added, the equals operator is used.

Filters can be combined with the & operator:

*url*?*key*=*"value"*&*key*=*"value"*

To filter for the department "Port" with emissions higher than 1.5 the following url is used:

*url*?department=Port&emission=>1.5

Only one filter can be used for every parameter key. The usage of two department parameters for exampe is not possible.

## RESTInterfaceAlternative

This REST-Service works the same as RESTInterface, but it uses the API provided by the website of the datasource instead of saving it locally.
Only the URL to access it changes:
http://localhost:8080/emission/RESTInterfaceAlternative


## Test of the Service

