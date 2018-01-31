<?xml version="1.0"?>
<recipe>
    <merge from="ApiMethods.java.sdw" to="api2/ApiMethods.java" />
    <merge from="ApiService.java.sdw" to="api2/ApiService.java" />
    <merge from="RequestClient.java.sdw" to="api2/RequestClient.java" />

    <merge from="DataSource.java.sdw" to="data/{{repository}}DataSource.java" />
    <merge from="LocalDataSource.java.sdw" to="data/local/{{repository}}LocalDataSource.java" />
    <merge from="RemoteDataSource.java.sdw" to="data/remote/{{repository}}RemoteDataSource.java" />
    <merge from="Repository.java.sdw" to="data/{{repository}}Repository.java" />

    <instantiate from="Params.java.sdw"
      to="api2/engine/params/{{params}}.java" />

    <instantiate from="Response.java.sdw"
      to="api2/response/{{response}}.java" />

</recipe>
